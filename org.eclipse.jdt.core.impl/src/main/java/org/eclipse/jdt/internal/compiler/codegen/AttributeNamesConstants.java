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
 *     Jesper S Moller - Contributions for
 *							Bug 405066 - [1.8][compiler][codegen] Implement code generation infrastructure for JSR335
 *							Bug 406973 - [compiler] Parse MethodParameters attribute
 *        Andy Clement - Contributions for
 *                          Bug 383624 - [1.8][compiler] Revive code generation support for type annotations (from Olivier's work)
 *******************************************************************************/
package org.eclipse.jdt.internal.compiler.codegen;

public interface AttributeNamesConstants {
	char[] SyntheticName = "Synthetic".toCharArray(); //$NON-NLS-1$
	char[] ConstantValueName = "ConstantValue".toCharArray(); //$NON-NLS-1$
	char[] LineNumberTableName = "LineNumberTable".toCharArray(); //$NON-NLS-1$
	char[] LocalVariableTableName = "LocalVariableTable".toCharArray(); //$NON-NLS-1$
	char[] InnerClassName = "InnerClasses".toCharArray(); //$NON-NLS-1$
	char[] CodeName = "Code".toCharArray(); //$NON-NLS-1$
	char[] ExceptionsName = "Exceptions".toCharArray(); //$NON-NLS-1$
	char[] SourceName = "SourceFile".toCharArray(); //$NON-NLS-1$
	char[] DeprecatedName = "Deprecated".toCharArray(); //$NON-NLS-1$
	char[] SignatureName = "Signature".toCharArray(); //$NON-NLS-1$
	char[] LocalVariableTypeTableName = "LocalVariableTypeTable".toCharArray(); //$NON-NLS-1$
	char[] EnclosingMethodName = "EnclosingMethod".toCharArray(); //$NON-NLS-1$
	char[] ModuleName = "Module".toCharArray(); //$NON-NLS-1$
	char[] ModuleMainClass = "ModuleMainClass".toCharArray(); //$NON-NLS-1$
	char[] ModulePackages = "ModulePackages".toCharArray(); //$NON-NLS-1$
	char[] AnnotationDefaultName = "AnnotationDefault".toCharArray(); //$NON-NLS-1$
	char[] RuntimeInvisibleAnnotationsName = "RuntimeInvisibleAnnotations".toCharArray(); //$NON-NLS-1$
	char[] RuntimeVisibleAnnotationsName = "RuntimeVisibleAnnotations".toCharArray(); //$NON-NLS-1$
	char[] RuntimeInvisibleParameterAnnotationsName = "RuntimeInvisibleParameterAnnotations".toCharArray(); //$NON-NLS-1$
	char[] RuntimeVisibleParameterAnnotationsName = "RuntimeVisibleParameterAnnotations".toCharArray(); //$NON-NLS-1$
	char[] StackMapTableName = "StackMapTable".toCharArray(); //$NON-NLS-1$
	char[] InconsistentHierarchy = "InconsistentHierarchy".toCharArray(); //$NON-NLS-1$
	char[] VarargsName = "Varargs".toCharArray(); //$NON-NLS-1$
	char[] StackMapName = "StackMap".toCharArray(); //$NON-NLS-1$
	char[] MissingTypesName = "MissingTypes".toCharArray(); //$NON-NLS-1$
	char[] BootstrapMethodsName = "BootstrapMethods".toCharArray(); //$NON-NLS-1$
	// jsr308
    char[] RuntimeVisibleTypeAnnotationsName = "RuntimeVisibleTypeAnnotations".toCharArray(); //$NON-NLS-1$
	char[] RuntimeInvisibleTypeAnnotationsName = "RuntimeInvisibleTypeAnnotations".toCharArray(); //$NON-NLS-1$
	// jep118
    char[] MethodParametersName = "MethodParameters".toCharArray(); //$NON-NLS-1$
	// jep181
    char[] NestHost = "NestHost".toCharArray(); //$NON-NLS-1$
	char[] NestMembers = "NestMembers".toCharArray(); //$NON-NLS-1$
	// jep 359 records
    char[] RecordClass = "Record".toCharArray(); //$NON-NLS-1$
	char[] PermittedSubclasses = "PermittedSubclasses".toCharArray(); //$NON-NLS-1$
}
