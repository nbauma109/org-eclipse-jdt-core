/*******************************************************************************
 * Copyright (c) 2004, 2015 IBM Corporation and others.
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
package org.eclipse.core.commands;

import org.eclipse.core.commands.common.AbstractNamedHandleEvent;

/**
 * An instance of this class describes changes to an instance of
 * <code>Category</code>.
 * <p>
 * This class is not intended to be extended by clients.
 * </p>
 *
 * @since 3.1
 * @see ICategoryListener#categoryChanged(CategoryEvent)
 */
public final class CategoryEvent extends AbstractNamedHandleEvent {

	/**
	 * The category that has changed; this value is never <code>null</code>.
	 */
	private final Category category;

	/**
	 * Creates a new instance of this class.
	 *
	 * @param category
	 *            the instance of the interface that changed.
	 * @param definedChanged
	 *            true, iff the defined property changed.
	 * @param descriptionChanged
	 *            true, iff the description property changed.
	 * @param nameChanged
	 *            true, iff the name property changed.
	 */
	public CategoryEvent(final Category category, final boolean definedChanged,
			final boolean descriptionChanged, final boolean nameChanged) {
		super(definedChanged, descriptionChanged, nameChanged);

		if (category == null) {
			throw new NullPointerException();
		}
		this.category = category;
	}

	/**
	 * Returns the instance of the interface that changed.
	 *
	 * @return the instance of the interface that changed. Guaranteed not to be
	 *         <code>null</code>.
	 */
	public Category getCategory() {
		return category;
	}
}
