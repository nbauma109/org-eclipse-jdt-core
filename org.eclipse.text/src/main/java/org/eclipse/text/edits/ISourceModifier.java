/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.text.edits;

/**
 * A source modifier can be used to modify the source of
 * a move or copy edit before it gets inserted at the target
 * position. This is useful if the text to be  copied has to
 * be modified before it is inserted without changing the
 * original source.
 *
 * @since 3.0
 */
public interface ISourceModifier {
	/**
	 * Returns the modification to be done to the passed
	 * string in form of replace edits. The set of returned
	 * replace edits must modify disjoint text regions.
	 * Violating this requirement will result in a <code>
	 * BadLocationException</code> while executing the
	 * associated move or copy edit.
	 * <p>
	 * The caller of this method is responsible to apply
	 * the returned edits to the passed source.
	 *
	 * @param source the source to be copied or moved
	 * @return an array of <code>ReplaceEdits</code>
	 *  describing the modifications.
	 */
    ReplaceEdit[] getModifications(String source);

	/**
	 * Creates a copy of this source modifier object. The copy will
	 * be used in a different text edit object. So it should be
	 * created in a way that is doesn't conflict with other text edits
	 * referring to this source modifier.
	 *
	 * @return the copy of the source modifier
	 */
    ISourceModifier copy();
}
