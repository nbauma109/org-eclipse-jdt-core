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

import org.eclipse.core.commands.common.AbstractHandleObjectEvent;

/**
 * An instance of this class describes changes to an instance of
 * {@link ParameterType}.
 * <p>
 * This class is not intended to be extended by clients.
 * </p>
 *
 * @see IParameterTypeListener#parameterTypeChanged(ParameterTypeEvent)
 * @since 3.2
 */
public final class ParameterTypeEvent extends AbstractHandleObjectEvent {

	/**
	 * The parameter type that has changed. This value is never
	 * <code>null</code>.
	 */
	private final ParameterType parameterType;

	/**
	 * Constructs a new instance.
	 *
	 * @param parameterType
	 *            The parameter type that changed; must not be <code>null</code>.
	 * @param definedChanged
	 *            <code>true</code>, iff the defined property changed.
	 */
	ParameterTypeEvent(final ParameterType parameterType,
			final boolean definedChanged) {

		super(definedChanged);

		if (parameterType == null) {
			throw new NullPointerException();
		}

		this.parameterType = parameterType;
	}

	/**
	 * Returns the instance of the parameter type that changed.
	 *
	 * @return the instance of the parameter type that changed. Guaranteed not
	 *         to be <code>null</code>.
	 */
	public ParameterType getParameterType() {
		return parameterType;
	}

}
