/*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
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
package org.eclipse.jdt.internal.compiler.ast;

public interface OperatorIds {
	int AND_AND = 0;
	int OR_OR = 1;
	int AND = 2;
	int OR = 3;
	int LESS = 4;
	int LESS_EQUAL = 5;
	int GREATER = 6;
	int GREATER_EQUAL = 7;
	int XOR = 8;
	int DIVIDE = 9;
	int LEFT_SHIFT = 10;
	int NOT = 11;
	int TWIDDLE = 12;
	int MINUS = 13;
	int PLUS = 14;
	int MULTIPLY = 15;
	int REMAINDER = 16;
	int RIGHT_SHIFT = 17;
	int EQUAL_EQUAL = 18;
	int UNSIGNED_RIGHT_SHIFT= 19;
	int NumberOfTables = 20;

	int QUESTIONCOLON = 23;

	int NOT_EQUAL = 29;
	int EQUAL = 30;
	int INSTANCEOF = 31;
	int PLUS_PLUS = 32;
	int MINUS_MINUS = 33;
}
