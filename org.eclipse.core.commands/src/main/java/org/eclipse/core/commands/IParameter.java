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
 ******************************************************************************/

package org.eclipse.core.commands;

/**
 * <p>
 * A parameter for a command. A parameter identifies a type of information that
 * the command might accept. For example, a "Show View" command might accept the
 * id of a view for display. This parameter also identifies possible values, for
 * display in the user interface.
 * </p>
 *
 * @since 3.1
 */
public interface IParameter {

	/**
	 * Returns the identifier for this parameter.
	 *
	 * @return The identifier; never <code>null</code>.
	 */
    String getId();

	/**
	 * Returns the human-readable name for this parameter.
	 *
	 * @return The parameter name; never <code>null</code>.
	 */
    String getName();

	/**
	 * Returns the values associated with this parameter.
	 *
	 * @return The values associated with this parameter. This must not be
	 *         <code>null</code>.
	 * @throws ParameterValuesException
	 *             If the values can't be retrieved for some reason.
	 */
    IParameterValues getValues() throws ParameterValuesException;

	/**
	 * Returns whether parameter is optional. Otherwise, it is required.
	 *
	 * @return <code>true</code> if the parameter is optional;
	 *         <code>false</code> if it is required.
	 */
    boolean isOptional();
}
