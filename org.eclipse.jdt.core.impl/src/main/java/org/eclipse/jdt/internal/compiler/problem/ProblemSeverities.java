/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
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
package org.eclipse.jdt.internal.compiler.problem;

public interface ProblemSeverities {

	int Warning = 0; // during handling only

	int Error = 1; // when bit is set: problem is error, if not it is a warning
	int AbortCompilation = 2;
	int AbortCompilationUnit = 4;
	int AbortType = 8;
	int AbortMethod = 16;
	int Abort = 30; // 2r11110
	int Optional = 32; // when bit is set: problem was configurable
	int SecondaryError = 64;
	int Fatal = 128; // when bit is set: problem was either a mandatory error, or an optional+treatOptionalErrorAsFatal
	int Ignore = 256; // during handling only
	int InternalError = 512;  // always exposed, even when silent error handling policy is in effect.
	int Info = 1024; // When bit is set, the unit or project is not flagged.

	int CoreSeverityMASK = Warning | Error | Info | Ignore;
}
