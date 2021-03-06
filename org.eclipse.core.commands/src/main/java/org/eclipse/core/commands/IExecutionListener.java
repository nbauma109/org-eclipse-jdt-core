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
 * A listener to the execution of commands. This listener will be notified if a
 * command is about to execute, and when that execution completes. It is not
 * possible for the listener to prevent the execution, only to respond to it in
 * some way.
 * </p>
 *
 * @since 3.1
 */
public interface IExecutionListener {

	/**
	 * Notifies the listener that an attempt was made to execute a command with
	 * no handler.
	 *
	 * @param commandId
	 *            The identifier of command that is not handled; never
	 *            <code>null</code>
	 * @param exception
	 *            The exception that occurred; never <code>null</code>.
	 */
    void notHandled(String commandId, NotHandledException exception);

	/**
	 * Notifies the listener that a command has failed to complete execution.
	 *
	 * @param commandId
	 *            The identifier of the command that has executed; never
	 *            <code>null</code>.
	 * @param exception
	 *            The exception that occurred; never <code>null</code>.
	 */
    void postExecuteFailure(String commandId,
                            ExecutionException exception);

	/**
	 * Notifies the listener that a command has completed execution
	 * successfully.
	 *
	 * @param commandId
	 *            The identifier of the command that has executed; never
	 *            <code>null</code>.
	 * @param returnValue
	 *            The return value from the command; may be <code>null</code>.
	 */
    void postExecuteSuccess(String commandId, Object returnValue);

	/**
	 * Notifies the listener that a command is about to execute.
	 *
	 * @param commandId
	 *            The identifier of the command that is about to execute, never
	 *            <code>null</code>.
	 * @param event
	 *            The event that will be passed to the <code>execute</code>
	 *            method; never <code>null</code>.
	 */
    void preExecute(String commandId, ExecutionEvent event);
}
