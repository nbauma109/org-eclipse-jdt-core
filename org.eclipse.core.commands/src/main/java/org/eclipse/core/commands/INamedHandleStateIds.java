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
 * State identifiers that are understood by named handle objects that implement
 * {@link IObjectWithState}.
 * </p>
 * <p>
 * Clients may implement or extend this class.
 * </p>
 *
 * @since 3.2
 */
public interface INamedHandleStateIds {

	/**
	 * The state id used for overriding the description of a named handle
	 * object. This state's value must return a {@link String}.
	 */
    String DESCRIPTION = "DESCRIPTION"; //$NON-NLS-1$

	/**
	 * The state id used for overriding the name of a named handle object. This
	 * state's value must return a {@link String}.
	 */
    String NAME = "NAME"; //$NON-NLS-1$
}
