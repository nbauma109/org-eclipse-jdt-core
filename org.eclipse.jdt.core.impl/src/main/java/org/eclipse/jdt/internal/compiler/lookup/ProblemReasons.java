/*******************************************************************************
 * Copyright (c) 2000, 2017 IBM Corporation and others.
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
 *     Jesper S Moller - Contributions for
 *								bug 382701 - [1.8][compiler] Implement semantic analysis of Lambda expressions & Reference expression
 *	   Stephan Herrmann - Contribution for
 *								bug 404649 - [1.8][compiler] detect illegal reference to indirect or redundant super
 *								Bug 400874 - [1.8][compiler] Inference infrastructure should evolve to meet JLS8 18.x (Part G of JSR335 spec)
 *								Bug 416182 - [1.8][compiler][null] Contradictory null annotations not rejected
 *******************************************************************************/
package org.eclipse.jdt.internal.compiler.lookup;

public interface ProblemReasons {
	int NoError = 0;
	int NotFound = 1;
	int NotVisible = 2;
	int Ambiguous = 3;
	int InternalNameProvided = 4; // used if an internal name is used in source
	int InheritedNameHidesEnclosingName = 5;
	int NonStaticReferenceInConstructorInvocation = 6;
	int NonStaticReferenceInStaticContext = 7;
	int ReceiverTypeNotVisible = 8;
	int IllegalSuperTypeVariable = 9;
	int ParameterBoundMismatch = 10; // for generic method
	int TypeParameterArityMismatch = 11; // for generic method
	int ParameterizedMethodTypeMismatch = 12; // for generic method
	int TypeArgumentsForRawGenericMethod = 13; // for generic method
	int InvalidTypeForStaticImport = 14;
	int InvalidTypeForAutoManagedResource = 15;
	int VarargsElementTypeNotVisible = 16;
	int NoSuchSingleAbstractMethod = 17;
	int NotAWellFormedParameterizedType = 18;
	// no longer in use: final int IntersectionHasMultipleFunctionalInterfaces = 19;
    int NonStaticOrAlienTypeReceiver = 20;
	int AttemptToBypassDirectSuper = 21; // super access within default method
	int DefectiveContainerAnnotationType = 22;
	int InvocationTypeInferenceFailure = 23;
	int ApplicableMethodOverriddenByInapplicable = 24;
	int ContradictoryNullAnnotations = 25;
	int NoSuchMethodOnArray = 26;
	int InferredApplicableMethodInapplicable = 27; // 18.5.1 ignores arguments not pertinent to applicability. When these are taken into consideration method could fail applicability
	int NoProperEnclosingInstance = 28;
	int InterfaceMethodInvocationNotBelow18 = 29;
	int NotAccessible = 30; // JLS 6.6.1 - module aspects
	int ErrorAlreadyReported = 31;
}
