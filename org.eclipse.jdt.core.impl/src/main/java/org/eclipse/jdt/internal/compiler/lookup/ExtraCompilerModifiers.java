/*******************************************************************************
 * Copyright (c) 2000, 2020 IBM Corporation and others.
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
 *     Stephan Herrmann <stephan@cs.tu-berlin.de> - Contributions for
 *								bug 328281 - visibility leaks not detected when analyzing unused field in private class
 *								bug 382353 - [1.8][compiler] Implementation property modifiers should be accepted on default methods.
 *******************************************************************************/
package org.eclipse.jdt.internal.compiler.lookup;

import org.eclipse.jdt.internal.compiler.ast.ASTNode;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

// TODO (philippe) these should be moved to tagbits
public interface ExtraCompilerModifiers { // modifier constant
	/**
	 * Bits that are depending upon ClassFileConstants (relying that classfiles only use the 16 lower bits).
	 * <p>
	 * Does <b>not</b> include {@link ClassFileConstants#AccDeprecated} and
	 * {@link ClassFileConstants#AccAnnotationDefault}!
	 * </p>
	 */
    int AccJustFlag = 0xFFFF;// 16 lower bits

	int AccDefaultMethod = ASTNode.Bit17;
	int AccCompactConstructor = ASTNode.Bit24;
	// bit18 - use by ClassFileConstants.AccAnnotationDefault
    int AccRestrictedAccess = ASTNode.Bit19;
	int AccFromClassFile = ASTNode.Bit20;
	int AccDefaultAbstract = ASTNode.Bit20;
	// bit21 - use by ClassFileConstants.AccDeprecated
    int AccDeprecatedImplicitly = ASTNode.Bit22; // record whether deprecated itself or contained by a deprecated type
	int AccAlternateModifierProblem = ASTNode.Bit23;
	int AccModifierProblem = ASTNode.Bit24;
	int AccSemicolonBody = ASTNode.Bit25;
	int AccRecord = ASTNode.Bit25;
	int AccUnresolved = ASTNode.Bit26;
	int AccBlankFinal = ASTNode.Bit27; // for blank final variables
	int AccIsDefaultConstructor = ASTNode.Bit27; // for default constructor
	int AccNonSealed = ASTNode.Bit27; // for class/interface
	int AccLocallyUsed = ASTNode.Bit28; // used to diagnose unused (a) private/local members or (b) members of private classes
											  // generally set when actual usage has been detected
											  // or, (b) when member of a private class is exposed via a non-private subclass
											  //     see https://bugs.eclipse.org/bugs/show_bug.cgi?id=328281
                                              int AccVisibilityMASK = ClassFileConstants.AccPublic | ClassFileConstants.AccProtected | ClassFileConstants.AccPrivate;

	int AccSealed = ASTNode.Bit29; // used for class/interface to set sealed
	int AccOverriding = ASTNode.Bit29; // record fact a method overrides another one
	int AccImplementing = ASTNode.Bit30; // record fact a method implements another one (it is concrete and overrides an abstract one)
	int AccGenericSignature = ASTNode.Bit31; // record fact a type/method/field involves generics in its signature (and need special signature attr)
	int AccPatternVariable = ASTNode.Bit29;
}
