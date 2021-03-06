/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Anton Leherbauer <anton.leherbauer@windriver.com> - [projection] "Backspace" key deleting something else - http://bugs.eclipse.org/301023
 *******************************************************************************/
package org.eclipse.jface.text.projection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.AbstractDocument;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultLineTracker;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension;
import org.eclipse.jface.text.IDocumentInformationMapping;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ILineTracker;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextStore;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;


/**
 * A <code>ProjectionDocument</code> represents a projection of its master
 * document. The contents of a projection document is a sequence of fragments of
 * the master document, i.e. the projection document can be thought as being
 * constructed from the master document by not copying the whole master document
 * but omitting several ranges of the master document.
 * <p>
 * The projection document indirectly utilizes its master document as
 * <code>ITextStore</code> by means of a <code>ProjectionTextStore</code>.
 * <p>
 * The content of a projection document can be changed in two ways. Either by a
 * text replace applied to the master document or the projection document. Or by
 * changing the projection between the master document and the projection
 * document. For the latter the two methods <code>addMasterDocumentRange</code>
 * and <code>removeMasterDocumentRange</code> are provided. For any
 * manipulation, the projection document sends out a
 * {@link org.eclipse.jface.text.projection.ProjectionDocumentEvent} describing
 * the change.
 * <p>
 * Clients are not supposed to directly instantiate this class. In order to
 * obtain a projection document, a
 * {@link org.eclipse.jface.text.projection.ProjectionDocumentManager}should be
 * used. This class is not intended to be subclassed outside of its origin
 * package.</p>
 *
 * @since 3.0
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ProjectionDocument extends AbstractDocument {


	/**
	 * Prefix of the name of the position category used to keep track of the master
	 * document's fragments that correspond to the segments of the projection
	 * document.
	 */
	private final static String FRAGMENTS_CATEGORY_PREFIX= "__fragmentsCategory"; //$NON-NLS-1$

	/**
	 * Name of the position category used to keep track of the project
	 * document's segments that correspond to the fragments of the master
	 * document.
	 */
	private final static String SEGMENTS_CATEGORY= "__segmentsCategory"; //$NON-NLS-1$


	/** The master document */
	private IDocument fMasterDocument;
	/** The master document as document extension */
	private IDocumentExtension fMasterDocumentExtension;
	/** The fragments' position category */
	private String fFragmentsCategory;
	/** The segment's position category */
	private String fSegmentsCategory;
	/** The document event issued by the master document */
	private DocumentEvent fMasterEvent;
	/** The document event to be issued by the projection document */
	private ProjectionDocumentEvent fSlaveEvent;
	/** The original document event generated by a direct manipulation of this projection document */
	private DocumentEvent fOriginalEvent;
	/** Indicates whether the projection document initiated a master document update or not */
	private boolean fIsUpdating= false;
	/** Indicated whether the projection document is in auto expand mode nor not */
	private boolean fIsAutoExpanding= false;
	/** The position updater for the segments */
	private SegmentUpdater fSegmentUpdater;
	/** The position updater for the fragments */
	private FragmentUpdater fFragmentsUpdater;
	/** The projection mapping */
	private ProjectionMapping fMapping;

	/**
	 * Creates a projection document for the given master document.
	 *
	 * @param masterDocument the master document
	 */
	public ProjectionDocument(IDocument masterDocument) {
		fMasterDocument= masterDocument;
		if (fMasterDocument instanceof IDocumentExtension)
			fMasterDocumentExtension= (IDocumentExtension) fMasterDocument;

		fSegmentsCategory= SEGMENTS_CATEGORY;
		fFragmentsCategory= FRAGMENTS_CATEGORY_PREFIX + hashCode();
		fMasterDocument.addPositionCategory(fFragmentsCategory);
		fFragmentsUpdater= new FragmentUpdater(fFragmentsCategory);
		fMasterDocument.addPositionUpdater(fFragmentsUpdater);

		fMapping= new ProjectionMapping(masterDocument, fFragmentsCategory, this, fSegmentsCategory);

		ITextStore s= new ProjectionTextStore(masterDocument, fMapping);
		ILineTracker tracker= new DefaultLineTracker();

		setTextStore(s);
		setLineTracker(tracker);

		completeInitialization();

		initializeProjection();
		tracker.set(s.get(0, s.getLength()));
	}

	/**
	 * Disposes this projection document.
	 */
	public void dispose() {
		fMasterDocument.removePositionUpdater(fFragmentsUpdater);
		try {
			fMasterDocument.removePositionCategory(fFragmentsCategory);
		} catch (BadPositionCategoryException x) {
			// allow multiple dispose calls
		}
	}

	private void internalError() {
		throw new IllegalStateException();
	}

	/**
	 * Returns the fragments of the master documents.
	 *
	 * @return the fragment of the master document
	 */
	protected final Position[] getFragments() {
		try {
			return fMasterDocument.getPositions(fFragmentsCategory);
		} catch (BadPositionCategoryException e) {
			internalError();
		}
		// unreachable
		return null;
	}

	/**
	 * Returns the segments of this projection document.
	 *
	 * @return the segments of this projection document
	 */
	protected final Position[] getSegments() {
		try {
			return getPositions(fSegmentsCategory);
		} catch (BadPositionCategoryException e) {
			internalError();
		}
		// unreachable
		return null;
	}

	/**
	 * Returns the projection mapping used by this document.
	 *
	 * @return the projection mapping used by this document
	 * @deprecated As of 3.4, replaced by {@link #getDocumentInformationMapping()}
	 */
	@Deprecated
	public ProjectionMapping getProjectionMapping(){
		return fMapping;
	}

	/**
	 * Returns the projection mapping used by this document.
	 *
	 * @return the projection mapping used by this document
	 * @since 3.4
	 */
	public IDocumentInformationMapping getDocumentInformationMapping() {
		return fMapping;
	}

	/**
	 * Returns the master document of this projection document.
	 *
	 * @return the master document of this projection document
	 */
	public IDocument getMasterDocument() {
		return fMasterDocument;
	}

	@Override
	public String getDefaultLineDelimiter() {
		return TextUtilities.getDefaultLineDelimiter(fMasterDocument);
	}

	/**
	 * Initializes the projection document from the master document based on
	 * the master's fragments.
	 */
	private void initializeProjection() {

		try {

			addPositionCategory(fSegmentsCategory);
			fSegmentUpdater= new SegmentUpdater(fSegmentsCategory);
			addPositionUpdater(fSegmentUpdater);

			int offset= 0;
			Position[] fragments= getFragments();
			for (Position f : fragments) {
				Fragment fragment = (Fragment) f;
				Segment segment= new Segment(offset, fragment.getLength());
				segment.fragment= fragment;
				addPosition(fSegmentsCategory, segment);
				offset += fragment.length;
			}

		} catch (BadPositionCategoryException | BadLocationException x) {
			internalError();
		}
	}

	/**
	 * Creates a segment for the given fragment at the given position inside the list of segments.
	 *
	 * @param fragment the corresponding fragment
	 * @param index the index in the list of segments
	 * @return the created segment
	 * @throws BadLocationException in case the fragment is invalid
	 * @throws BadPositionCategoryException in case the segment category is invalid
	 */
	private Segment createSegmentFor(Fragment fragment, int index) throws BadLocationException, BadPositionCategoryException {

		int offset= 0;
		if (index > 0) {
			Position[] segments= getSegments();
			Segment segment= (Segment) segments[index - 1];
			offset= segment.getOffset() + segment.getLength();
		}

		Segment segment= new Segment(offset, 0);
		segment.fragment= fragment;
		fragment.segment= segment;
		addPosition(fSegmentsCategory, segment);
		return segment;
	}

	/**
	 * Adds the given range of the master document to this projection document.
	 *
	 * @param offsetInMaster offset of the master document range
	 * @param lengthInMaster length of the master document range
	 * @param masterDocumentEvent the master document event that causes this
	 *            projection change or <code>null</code> if none
	 * @throws BadLocationException if the given range is invalid in the master
	 *             document
	 */
	private void internalAddMasterDocumentRange(int offsetInMaster, int lengthInMaster, DocumentEvent masterDocumentEvent) throws BadLocationException {
		if (lengthInMaster == 0)
			return;

		try {

			Position[] fragments= getFragments();
			int index= fMasterDocument.computeIndexInCategory(fFragmentsCategory, offsetInMaster);

			Fragment left= null;
			Fragment right= null;

			if (index < fragments.length) {
				Fragment fragment= (Fragment) fragments[index];
				if (offsetInMaster == fragment.offset) {
                    if (fragment.length != 0) {
						throw new IllegalArgumentException("overlaps with existing fragment"); //$NON-NLS-1$
					}
                    left= fragment;
                }
				if (offsetInMaster + lengthInMaster == fragment.offset)
					right= fragment;
			}

			if (0 < index && index <= fragments.length) {
				Fragment fragment= (Fragment) fragments[index - 1];
				if (fragment.includes(offsetInMaster))
					throw new IllegalArgumentException("overlaps with existing fragment"); //$NON-NLS-1$
				if (fragment.getOffset() + fragment.getLength() == offsetInMaster)
					left= fragment;
			}

			int offsetInSlave= 0;
			if (index > 0) {
				Fragment fragment= (Fragment) fragments[index - 1];
				Segment segment= fragment.segment;
				offsetInSlave= segment.getOffset() + segment.getLength();
			}

			ProjectionDocumentEvent event= new ProjectionDocumentEvent(this, offsetInSlave, 0, fMasterDocument.get(offsetInMaster, lengthInMaster), offsetInMaster, lengthInMaster, masterDocumentEvent);
			super.fireDocumentAboutToBeChanged(event);

			// check for neighboring fragment
			if (left != null && right != null) {

				int endOffset= right.getOffset() + right.getLength();
				left.setLength(endOffset - left.getOffset());
				left.segment.setLength(left.segment.getLength() + right.segment.getLength());

				removePosition(fSegmentsCategory, right.segment);
				fMasterDocument.removePosition(fFragmentsCategory, right);

			} else if (left != null) {
				int endOffset= offsetInMaster +lengthInMaster;
				left.setLength(endOffset - left.getOffset());
				left.segment.markForStretch();

			} else if (right != null) {
				right.setOffset(right.getOffset() - lengthInMaster);
				right.setLength(right.getLength() + lengthInMaster);
				right.segment.markForStretch();

			} else {
				// create a new segment
				Fragment fragment= new Fragment(offsetInMaster, lengthInMaster);
				fMasterDocument.addPosition(fFragmentsCategory, fragment);
				Segment segment= createSegmentFor(fragment, index);
				segment.markForStretch();
			}

			getTracker().replace(event.getOffset(), event.getLength(), event.getText());
			super.fireDocumentChanged(event);

		} catch (BadPositionCategoryException x) {
			internalError();
		}
	}

	/**
	 * Finds the fragment of the master document that represents the given range.
	 *
	 * @param offsetInMaster the offset of the range in the master document
	 * @param lengthInMaster the length of the range in the master document
	 * @return the fragment representing the given master document range
	 */
	private Fragment findFragment(int offsetInMaster, int lengthInMaster) {
		Position[] fragments= getFragments();
		for (Position fragment : fragments) {
			Fragment f = (Fragment) fragment;
			if (f.getOffset() <= offsetInMaster && offsetInMaster + lengthInMaster <= f.getOffset() + f.getLength())
				return f;
		}
		return null;
	}

	/**
	 * Removes the given range of the master document from this projection
	 * document.
	 *
	 * @param offsetInMaster the offset of the range in the master document
	 * @param lengthInMaster the length of the range in the master document
	 *
	 * @throws BadLocationException if the given range is not valid in the
	 *             master document
	 * @throws IllegalArgumentException if the given range is not projected in
	 *             this projection document or is not completely comprised by
	 *             an existing fragment
	 */
	private void internalRemoveMasterDocumentRange(int offsetInMaster, int lengthInMaster) throws BadLocationException {
		try {

			IRegion imageRegion= fMapping.toExactImageRegion(new Region(offsetInMaster, lengthInMaster));
			if (imageRegion == null)
				throw new IllegalArgumentException();

			Fragment fragment= findFragment(offsetInMaster, lengthInMaster);
			if (fragment == null)
				throw new IllegalArgumentException();

			ProjectionDocumentEvent event= new ProjectionDocumentEvent(this, imageRegion.getOffset(), imageRegion.getLength(), "", offsetInMaster, lengthInMaster); //$NON-NLS-1$
			super.fireDocumentAboutToBeChanged(event);

			if (fragment.getOffset() == offsetInMaster) {
				fragment.setOffset(offsetInMaster + lengthInMaster);
				fragment.setLength(fragment.getLength() - lengthInMaster);
			} else {
				// split fragment into three fragments, let position updater remove it

				// add fragment for the region to be removed
				Fragment newFragment= new Fragment(offsetInMaster, lengthInMaster);
				Segment segment= new Segment(imageRegion.getOffset(), imageRegion.getLength());
				newFragment.segment= segment;
				segment.fragment= newFragment;
				fMasterDocument.addPosition(fFragmentsCategory, newFragment);
				addPosition(fSegmentsCategory, segment);

				// add fragment for the remainder right of the deleted range in the original fragment
				int offset= offsetInMaster + lengthInMaster;
				newFragment= new Fragment(offset, fragment.getOffset() + fragment.getLength() - offset);
				offset= imageRegion.getOffset() + imageRegion.getLength();
				segment= new Segment(offset, fragment.segment.getOffset() + fragment.segment.getLength() - offset);
				newFragment.segment= segment;
				segment.fragment= newFragment;
				fMasterDocument.addPosition(fFragmentsCategory, newFragment);
				addPosition(fSegmentsCategory, segment);

				// adjust length of initial fragment (the left one)
				fragment.setLength(offsetInMaster - fragment.getOffset());
				fragment.segment.setLength(imageRegion.getOffset() - fragment.segment.getOffset());
			}

			getTracker().replace(event.getOffset(), event.getLength(), event.getText());
			super.fireDocumentChanged(event);

		} catch (BadPositionCategoryException x) {
			internalError();
		}
	}

	/**
	 * Returns the sequence of all master document regions which are contained
	 * in the given master document range and which are not yet part of this
	 * projection document.
	 *
	 * @param offsetInMaster the range offset in the master document
	 * @param lengthInMaster the range length in the master document
	 * @return the sequence of regions which are not yet part of the projection
	 *         document
	 * @throws BadLocationException in case the given range is invalid in the
	 *         master document
	 */
	public final IRegion[] computeUnprojectedMasterRegions(int offsetInMaster, int lengthInMaster) throws BadLocationException {

		IRegion[] fragments= null;
		IRegion imageRegion= fMapping.toImageRegion(new Region(offsetInMaster, lengthInMaster));
		if (imageRegion != null)
			fragments= fMapping.toExactOriginRegions(imageRegion);

		if (fragments == null || fragments.length == 0)
			return new IRegion[] { new Region(offsetInMaster, lengthInMaster) };

		List<Region> gaps= new ArrayList<>();

		IRegion region= fragments[0];
		if (offsetInMaster < region.getOffset())
			gaps.add(new Region(offsetInMaster, region.getOffset() - offsetInMaster));

		for (int i= 0; i < fragments.length - 1; i++) {
			IRegion left= fragments[i];
			IRegion right= fragments[i + 1];
			int leftEnd= left.getOffset() + left.getLength();
			if (leftEnd < right.getOffset())
				gaps.add(new Region(leftEnd, right.getOffset() - leftEnd));
		}

		region= fragments[fragments.length - 1];
		int leftEnd= region.getOffset() + region.getLength();
		int rightEnd= offsetInMaster + lengthInMaster;
		if (leftEnd < rightEnd)
			gaps.add(new Region(leftEnd, rightEnd - leftEnd));

		IRegion[] result= new IRegion[gaps.size()];
		gaps.toArray(result);
		return result;
	}

	/**
	 * Returns the first master document region which is contained in the given
	 * master document range and which is not yet part of this projection
	 * document.
	 *
	 * @param offsetInMaster the range offset in the master document
	 * @param lengthInMaster the range length in the master document
	 * @return the first region that is not yet part of the projection document
	 * @throws BadLocationException in case the given range is invalid in the
	 *         master document
	 * @since 3.1
	 */
	private IRegion computeFirstUnprojectedMasterRegion(int offsetInMaster, int lengthInMaster) throws BadLocationException {

		IRegion[] fragments= null;
		IRegion imageRegion= fMapping.toImageRegion(new Region(offsetInMaster, lengthInMaster));
		if (imageRegion != null)
			fragments= fMapping.toExactOriginRegions(imageRegion);

		if (fragments == null || fragments.length == 0)
			return new Region(offsetInMaster, lengthInMaster);

		IRegion region= fragments[0];
		if (offsetInMaster < region.getOffset())
			return new Region(offsetInMaster, region.getOffset() - offsetInMaster);

		for (int i= 0; i < fragments.length - 1; i++) {
			IRegion left= fragments[i];
			IRegion right= fragments[i + 1];
			int leftEnd= left.getOffset() + left.getLength();
			if (leftEnd < right.getOffset())
				return new Region(leftEnd, right.getOffset() - leftEnd);
		}

		region= fragments[fragments.length - 1];
		int leftEnd= region.getOffset() + region.getLength();
		int rightEnd= offsetInMaster + lengthInMaster;
		if (leftEnd < rightEnd)
			return new Region(leftEnd, rightEnd - leftEnd);

		return null;
	}

	/**
	 * Ensures that the given range of the master document is part of this
	 * projection document.
	 *
	 * @param offsetInMaster the offset of the master document range
	 * @param lengthInMaster the length of the master document range
	 * @throws BadLocationException in case the master event is not valid
	 */
	public void addMasterDocumentRange(int offsetInMaster, int lengthInMaster) throws BadLocationException {
		addMasterDocumentRange(offsetInMaster, lengthInMaster, null);
	}

	/**
	 * Ensures that the given range of the master document is part of this
	 * projection document.
	 *
	 * @param offsetInMaster the offset of the master document range
	 * @param lengthInMaster the length of the master document range
	 * @param masterDocumentEvent the master document event which causes this
	 *            projection change, or <code>null</code> if none
	 * @throws BadLocationException in case the master event is not valid
	 */
	private void addMasterDocumentRange(int offsetInMaster, int lengthInMaster, DocumentEvent masterDocumentEvent) throws BadLocationException {
		/*
		 * Calling internalAddMasterDocumentRange may cause other master ranges
		 * to become unfolded, resulting in re-entrant calls to this method. In
		 * order to not add a region twice, we have to compute the next region
		 * to add in every iteration.
		 *
		 * To place an upper bound on the number of iterations, we use the number
		 * of fragments * 2 as the limit.
		 */
		int limit= Math.max(getFragments().length * 2, 20);
		while (true) {
			if (limit-- < 0)
				throw new IllegalArgumentException("safety loop termination"); //$NON-NLS-1$

			IRegion gap= computeFirstUnprojectedMasterRegion(offsetInMaster, lengthInMaster);
			if (gap == null)
				return;

			internalAddMasterDocumentRange(gap.getOffset(), gap.getLength(), masterDocumentEvent);
		}
	}

	/**
	 * Ensures that the given range of the master document is not part of this
	 * projection document.
	 *
	 * @param offsetInMaster the offset of the master document range
	 * @param lengthInMaster the length of the master document range
	 * @throws BadLocationException in case the master event is not valid
	 */
	public void removeMasterDocumentRange(int offsetInMaster, int lengthInMaster) throws BadLocationException {
		IRegion[] fragments= computeProjectedMasterRegions(offsetInMaster, lengthInMaster);
		if (fragments == null || fragments.length == 0)
			return;

		for (IRegion fragment : fragments) {
			internalRemoveMasterDocumentRange(fragment.getOffset(), fragment.getLength());
		}
	}

	/**
	 * Returns the sequence of all master document regions with are contained in the given master document
	 * range and which are part of this projection document. May return <code>null</code> if no such
	 * regions exist.
	 *
	 * @param offsetInMaster the range offset in the master document
	 * @param lengthInMaster the range length in the master document
	 * @return the sequence of regions which are part of the projection document or <code>null</code>
	 * @throws BadLocationException in case the given range is invalid in the master document
	 */
	public final IRegion[] computeProjectedMasterRegions(int offsetInMaster, int lengthInMaster) throws BadLocationException {
		IRegion imageRegion= fMapping.toImageRegion(new Region(offsetInMaster, lengthInMaster));
		return imageRegion != null ? fMapping.toExactOriginRegions(imageRegion) : null;
	}

	/**
	 * Returns whether this projection is being updated.
	 *
	 * @return <code>true</code> if the document is updating
	 */
	protected boolean isUpdating() {
		return fIsUpdating;
	}

	@Override
	public void replace(int offset, int length, String text) throws BadLocationException {
		try {
			fIsUpdating= true;
			if (fMasterDocumentExtension != null)
				fMasterDocumentExtension.stopPostNotificationProcessing();
			super.replace(offset, length, text);
		} finally {
			fIsUpdating= false;
			if (fMasterDocumentExtension != null)
				fMasterDocumentExtension.resumePostNotificationProcessing();
		}
	}

	@Override
	public void set(String text) {
		try {
			fIsUpdating= true;
			if (fMasterDocumentExtension != null)
				fMasterDocumentExtension.stopPostNotificationProcessing();

			super.set(text);

		} finally {
			fIsUpdating= false;
			if (fMasterDocumentExtension != null)
				fMasterDocumentExtension.resumePostNotificationProcessing();
		}
	}

	/**
	 * Transforms a document event of the master document into a projection
	 * document based document event.
	 *
	 * @param masterEvent the master document event
	 * @return the slave document event
	 * @throws BadLocationException in case the master event is not valid
	 */
	private ProjectionDocumentEvent normalize(DocumentEvent masterEvent) throws BadLocationException {
		if (!isUpdating()) {
			IRegion imageRegion= fMapping.toExactImageRegion(new Region(masterEvent.getOffset(), masterEvent.getLength()));
			if (imageRegion != null)
				return new ProjectionDocumentEvent(this, imageRegion.getOffset(), imageRegion.getLength(), masterEvent.getText(), masterEvent);
			return null;
		}

		ProjectionDocumentEvent event= new ProjectionDocumentEvent(this, fOriginalEvent.getOffset(), fOriginalEvent.getLength(), fOriginalEvent.getText(), masterEvent);
		fOriginalEvent= null;
		return event;
	}

	/**
	 * Ensures that when the master event affects this projection document, that the whole region described by the
	 * event is part of this projection document.
	 *
	 * @param masterEvent the master document event
	 * @return <code>true</code> if masterEvent affects this projection document
	 * @throws BadLocationException in case the master event is not valid
	 */
	protected final boolean adaptProjectionToMasterChange(DocumentEvent masterEvent) throws BadLocationException {
		if (!isUpdating() && fFragmentsUpdater.affectsPositions(masterEvent) || fIsAutoExpanding && masterEvent.getLength() > 0) {

			addMasterDocumentRange(masterEvent.getOffset(), masterEvent.getLength(), masterEvent);
			return true;

		}
        if (fMapping.getImageLength() == 0 && masterEvent.getLength() == 0) {

			Position[] fragments= getFragments();
			if (fragments.length == 0) {
				// there is no segment in this projection document, thus one must be created
				// need to bypass the usual infrastructure as the new segment/fragment would be of length 0 and thus the segmentation be not well formed
				try {
					Fragment fragment= new Fragment(0, 0);
					fMasterDocument.addPosition(fFragmentsCategory, fragment);
					createSegmentFor(fragment, 0);
				} catch (BadPositionCategoryException x) {
					internalError();
				}
			}
		}

		return isUpdating();
	}

	/**
	 * When called, this projection document is informed about a forthcoming
	 * change of its master document. This projection document checks whether
	 * the master document change affects it and if so informs all document
	 * listeners.
	 *
	 * @param masterEvent the master document event
	 */
	public void masterDocumentAboutToBeChanged(DocumentEvent masterEvent) {
		try {

			boolean assertNotNull= adaptProjectionToMasterChange(masterEvent);
			fSlaveEvent= normalize(masterEvent);
			if (assertNotNull && fSlaveEvent == null)
				internalError();

			fMasterEvent= masterEvent;
			if (fSlaveEvent != null)
				delayedFireDocumentAboutToBeChanged();

		} catch (BadLocationException e) {
			internalError();
		}
	}

	/**
	 * When called, this projection document is informed about a change of its
	 * master document. If this projection document is affected it informs all
	 * of its document listeners.
	 *
	 * @param masterEvent the master document event
	 */
	public void masterDocumentChanged(DocumentEvent masterEvent) {
		if ( !isUpdating() && masterEvent == fMasterEvent) {
			if (fSlaveEvent != null) {
				try {
					getTracker().replace(fSlaveEvent.getOffset(), fSlaveEvent.getLength(), fSlaveEvent.getText());
					fireDocumentChanged(fSlaveEvent);
				} catch (BadLocationException e) {
					internalError();
				}
			} else if (ensureWellFormedSegmentation(masterEvent.getOffset()))
				fMapping.projectionChanged();
		}
	}

	@Override
	protected void fireDocumentAboutToBeChanged(DocumentEvent event) {
		fOriginalEvent= event;
		// delay it until there is a notification from the master document
		// at this point, it is expensive to construct the master document information
	}

	/**
	 * Fires the slave document event as about-to-be-changed event to all registered listeners.
	 */
	private void delayedFireDocumentAboutToBeChanged() {
		super.fireDocumentAboutToBeChanged(fSlaveEvent);
	}

	/**
	 * Ignores the given event and sends the semantically equal slave document event instead.
	 *
	 * @param event the event to be ignored
	 */
	@Override
	protected void fireDocumentChanged(DocumentEvent event) {
		super.fireDocumentChanged(fSlaveEvent);
	}

	@Override
	protected void updateDocumentStructures(DocumentEvent event) {
		super.updateDocumentStructures(event);
		ensureWellFormedSegmentation(computeAnchor(event));
		fMapping.projectionChanged();
	}

	private int computeAnchor(DocumentEvent event) {
		if (event instanceof ProjectionDocumentEvent) {
			ProjectionDocumentEvent slave= (ProjectionDocumentEvent) event;
			Object changeType= slave.getChangeType();
			if (ProjectionDocumentEvent.CONTENT_CHANGE == changeType) {
				DocumentEvent master= slave.getMasterEvent();
				if (master != null)
					return master.getOffset();
			} else if (ProjectionDocumentEvent.PROJECTION_CHANGE == changeType) {
				return slave.getMasterOffset();
			}
		}
		return -1;
	}

	private boolean ensureWellFormedSegmentation(int anchorOffset) {
		boolean changed= false;
		Position[] segments= getSegments();
		for (int i= 0; i < segments.length; i++) {
			Segment segment= (Segment) segments[i];
			if (segment.isDeleted() || segment.getLength() == 0 && (i < segments.length - 1 || i > 0 && segments[i - 1].isDeleted())) {
				try {
					removePosition(fSegmentsCategory, segment);
					fMasterDocument.removePosition(fFragmentsCategory, segment.fragment);
					changed= true;
				} catch (BadPositionCategoryException e) {
					internalError();
				}
			} else if (i < segments.length - 1) {
				Segment next= (Segment) segments[i + 1];
				if (next.isDeleted() || next.getLength() == 0)
					continue;
				Fragment fragment= segment.fragment;
				if (fragment.getOffset() + fragment.getLength() == next.fragment.getOffset()) {
					// join fragments and their corresponding segments
					segment.setLength(segment.getLength() + next.getLength());
					fragment.setLength(fragment.getLength() + next.fragment.getLength());
					next.delete();
				}
			}
		}

		if (changed && anchorOffset != -1) {
			Position[] changedSegments= getSegments();
			if (changedSegments == null || changedSegments.length == 0) {
				Fragment fragment= new Fragment(anchorOffset, 0);
				try {
					fMasterDocument.addPosition(fFragmentsCategory, fragment);
					createSegmentFor(fragment, 0);
				} catch (BadLocationException | BadPositionCategoryException e) {
					internalError();
				}
			}
		}

		return changed;
	}

	@Override
	public void registerPostNotificationReplace(IDocumentListener owner, IDocumentExtension.IReplace replace) {
		if (!isUpdating())
			throw new UnsupportedOperationException();
		super.registerPostNotificationReplace(owner, replace);
	}

	/**
	 * Sets the auto expand mode for this document.
	 *
	 * @param autoExpandMode <code>true</code> if auto-expanding
	 */
	public void setAutoExpandMode(boolean autoExpandMode) {
		fIsAutoExpanding= autoExpandMode;
	}

	/**
	 * Replaces all master document ranges with the given master document range.
	 *
	 * @param offsetInMaster the offset in the master document
	 * @param lengthInMaster the length in the master document
	 * @throws BadLocationException if the given range of the master document is not valid
	 */
	public void replaceMasterDocumentRanges(int offsetInMaster, int lengthInMaster) throws BadLocationException {
		try {

			ProjectionDocumentEvent event= new ProjectionDocumentEvent(this, 0, fMapping.getImageLength(), fMasterDocument.get(offsetInMaster, lengthInMaster), offsetInMaster, lengthInMaster);
			super.fireDocumentAboutToBeChanged(event);

			Position[] fragments= getFragments();
			for (Position fragment1 : fragments) {
				Fragment fragment = (Fragment) fragment1;
				fMasterDocument.removePosition(fFragmentsCategory, fragment);
				removePosition(fSegmentsCategory, fragment.segment);
			}

			Fragment fragment= new Fragment(offsetInMaster, lengthInMaster);
			Segment segment= new Segment(0, 0);
			segment.fragment= fragment;
			fragment.segment= segment;
			fMasterDocument.addPosition(fFragmentsCategory, fragment);
			addPosition(fSegmentsCategory, segment);

			getTracker().set(fMasterDocument.get(offsetInMaster, lengthInMaster));
			super.fireDocumentChanged(event);

		} catch (BadPositionCategoryException x) {
			internalError();
		}
	}
}
