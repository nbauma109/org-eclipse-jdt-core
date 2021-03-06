/*******************************************************************************
 * Copyright (c) 2005, 2015 IBM Corporation and others.
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

/**
 * <p>
 * A listener to changes in some state.
 * </p>
 * <p>
 * Clients may implement, but must not extend this interface.
 * </p>
 *
 * @since 3.2
 */
public interface IStateListener {

	/**
	 * Handles a change to the value in some state.
	 *
	 * @param state
	 *            The state that has changed; never <code>null</code>. The
	 *            value for this state has been updated to the new value.
	 * @param oldValue
	 *            The old value; may be anything.
	 */
    void handleStateChange(State state, Object oldValue);
}
