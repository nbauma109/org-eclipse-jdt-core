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
package org.eclipse.jdt.internal.compiler.util;

public interface SuffixConstants {
	String EXTENSION_class = "class"; //$NON-NLS-1$
	String EXTENSION_CLASS = "CLASS"; //$NON-NLS-1$
	String EXTENSION_java = "java"; //$NON-NLS-1$
	String EXTENSION_JAVA = "JAVA"; //$NON-NLS-1$
	String EXTENSION_jmod = "jmod"; //$NON-NLS-1$
	String EXTENSION_JMOD = "JMOD"; //$NON-NLS-1$

	String SUFFIX_STRING_class = "." + EXTENSION_class; //$NON-NLS-1$
	String SUFFIX_STRING_CLASS = "." + EXTENSION_CLASS; //$NON-NLS-1$
	String SUFFIX_STRING_java = "." + EXTENSION_java; //$NON-NLS-1$
	String SUFFIX_STRING_JAVA = "." + EXTENSION_JAVA; //$NON-NLS-1$

	char[] SUFFIX_class = SUFFIX_STRING_class.toCharArray();
	char[] SUFFIX_CLASS = SUFFIX_STRING_CLASS.toCharArray();
	char[] SUFFIX_java = SUFFIX_STRING_java.toCharArray();
	char[] SUFFIX_JAVA = SUFFIX_STRING_JAVA.toCharArray();
}
