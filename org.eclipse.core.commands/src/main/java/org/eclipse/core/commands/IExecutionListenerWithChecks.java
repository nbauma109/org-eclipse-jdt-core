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

import org.eclipse.core.commands.common.NotDefinedException;

/**
 * <p>
 * A listener to the execution of commands. This listener will be notified if
 * someone tries to execute a command and it is not enabled or not defined. The
 * listener also be notified if a command is about to execute, and when that
 * execution completes. It is not possible for the listener to prevent the
 * execution, only to respond to it in some way.
 * </p>
 * <p>
 * Clients may implement, but must not extend.
 * </p>
 *
 * @since 3.2
 */
public interface IExecutionListenerWithChecks extends IExecutionListener {

	/**
	 * Notifies the listener that an attempt was made to execute a command that
	 * is not defined.
	 *
	 * @param commandId
	 *            The identifier of command that is not defined; never
	 *            <code>null</code>
	 * @param exception
	 *            The exception that occurred; never <code>null</code>.
	 */
    void notDefined(String commandId, NotDefinedException exception);

	/**
	 * Notifies the listener that an attempt was made to execute a command that
	 * is disabled.
	 *
	 * @param commandId
	 *            The identifier of command that is not enabled; never
	 *            <code>null</code>
	 * @param exception
	 *            The exception that occurred; never <code>null</code>.
	 */
    void notEnabled(String commandId, NotEnabledException exception);
}
