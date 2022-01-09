/*******************************************************************************
 * Copyright (c) 2000, 2018 IBM Corporation and others.
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
package org.eclipse.jdt.core;

/**
 * Status codes used with Java model status objects.
 * <p>
 * This interface declares constants only.
 * </p>
 *
 * @see IJavaModelStatus
 * @see org.eclipse.core.runtime.IStatus#getCode()
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IJavaModelStatusConstants {

	/**
	 * Status constant indicating that a container path was resolved
	 * to invalid entries (null or container).
	 *
	 * @since 2.0
	 */
    int INVALID_CP_CONTAINER_ENTRY = 962;

	/**
	 * Status constant indicating that a container path was not resolvable
	 * indicating either the referred container is undefined, unbound.
	 *
	 * @since 2.0
	 */
    int CP_CONTAINER_PATH_UNBOUND = 963;

	/**
	 * Status constant indicating that a classpath entry was invalid
	 */
    int INVALID_CLASSPATH = 964;

	/**
	 * Status constant indicating that a variable path was not resolvable
	 * indicating either the referred variable is undefined, unbound or the resolved
	 * variable path does not correspond to an existing file or folder.
	 */
    int CP_VARIABLE_PATH_UNBOUND = 965;

	/**
	 * Status constant indicating a core exception occurred.
	 * Use <code>getException</code> to retrieve a <code>CoreException</code>.
	 */
    int CORE_EXCEPTION = 966;

	/**
	 * Status constant indicating one or more of the elements
	 * supplied are not of a valid type for the operation to
	 * process.
	 * The element(s) can be retrieved using <code>getElements</code> on the status object.
	 */
    int INVALID_ELEMENT_TYPES = 967;

	/**
	 * Status constant indicating that no elements were
	 * provided to the operation for processing.
	 */
    int NO_ELEMENTS_TO_PROCESS = 968;

	/**
	 * Status constant indicating that one or more elements
	 * supplied do not exist.
	 * The element(s) can be retrieved using <code>getElements</code> on the status object.
	 *
	 * @see IJavaModelStatus#isDoesNotExist()
	 */
    int ELEMENT_DOES_NOT_EXIST = 969;

	/**
	 * Status constant indicating that a <code>null</code> path was
	 * supplied to the operation.
	 */
    int NULL_PATH = 970;

	/**
	 * Status constant indicating that a path outside of the
	 * project was supplied to the operation. The path can be retrieved using
	 * <code>getPath</code> on the status object.
	 */
    int PATH_OUTSIDE_PROJECT = 971;

	/**
	 * Status constant indicating that a relative path
	 * was supplied to the operation when an absolute path is
	 * required. The path can be retrieved using <code>getPath</code> on the
	 * status object.
	 */
    int RELATIVE_PATH = 972;

	/**
	 * Status constant indicating that a path specifying a device
	 * was supplied to the operation when a path with no device is
	 * required. The path can be retrieved using <code>getPath</code> on the
	 * status object.
	 */
    int DEVICE_PATH = 973;

	/**
	 * Status constant indicating that a string
	 * was supplied to the operation that was <code>null</code>.
	 */
    int NULL_STRING = 974;

	/**
	 * Status constant indicating that the operation encountered
	 * a read-only element.
	 * The element(s) can be retrieved using <code>getElements</code> on the status object.
	 */
    int READ_ONLY = 976;

	/**
	 * Status constant indicating that a naming collision would occur
	 * if the operation proceeded.
	 */
    int NAME_COLLISION = 977;

	/**
	 * Status constant indicating that a destination provided for a copy/move/rename operation
	 * is invalid. The destination for a package fragment must be a package fragment root; the
	 * destination for a compilation unit must be a package fragment; the destination for
	 * a package declaration or import declaration must be a compilation unit; the
	 * destination for a type must be a type or compilation unit; the destination for any
	 * type member (other than a type) must be a type. <br>
	 *
	 * The destination element can be retrieved using <code>getElements</code> on the status object.
	 */
    int INVALID_DESTINATION = 978;

	/**
	 * Status constant indicating that a path provided to an operation
	 * is invalid. The path can be retrieved using <code>getPath</code> on the
	 * status object.
	 */
    int INVALID_PATH = 979;

	/**
	 * Status constant indicating the given source position is out of bounds.
	 */
    int INDEX_OUT_OF_BOUNDS = 980;

	/**
	 * Status constant indicating there is an update conflict
	 * for a working copy. The compilation unit on which the
	 * working copy is based has changed since the working copy
	 * was created.
	 */
    int UPDATE_CONFLICT = 981;

	/**
	 * Status constant indicating that <code>null</code> was specified
	 * as a name argument.
	 */
    int NULL_NAME = 982;

	/**
	 * Status constant indicating that a name provided is not syntactically correct.
	 * The name can be retrieved from <code>getString</code>.
	 */
    int INVALID_NAME = 983;

	/**
	 * Status constant indicating that the specified contents
	 * are not valid.
	 */
    int INVALID_CONTENTS = 984;

	/**
	 * Status constant indicating that an <code>java.io.IOException</code>
	 * occurred.
	 */
    int IO_EXCEPTION = 985;

	/**
	 * Status constant indicating that a <code>DOMException</code>
	 * occurred.
	 */
    int DOM_EXCEPTION = 986;

	/**
	 * Status constant indicating that a <code>TargetException</code>
	 * occurred.
	 */
    int TARGET_EXCEPTION = 987;

	/**
	 * Status constant indicating that the Java builder
	 * could not be initialized.
	 */
    int BUILDER_INITIALIZATION_ERROR = 990;

	/**
	 * Status constant indicating that the Java builder's last built state
	 * could not be serialized or deserialized.
	 */
    int BUILDER_SERIALIZATION_ERROR = 991;

	/**
	 * Status constant indicating that an error was encountered while
	 * trying to evaluate a code snippet, or other item.
	 */
    int EVALUATION_ERROR = 992;

	/**
	 * Status constant indicating that a sibling specified is not valid.
	 */
    int INVALID_SIBLING = 993;

	/**
	 * Status indicating that a Java element could not be created because
	 * the underlying resource is invalid.
	 * @see JavaCore
	 */
    int INVALID_RESOURCE = 995;

	/**
	 * Status indicating that a Java element could not be created because
	 * the underlying resource is not of an appropriate type.
	 * @see JavaCore
	 */
    int INVALID_RESOURCE_TYPE = 996;

	/**
	 * Status indicating that a Java element could not be created because
	 * the project owning underlying resource does not have the Java nature.
	 * @see JavaCore
	 */
    int INVALID_PROJECT = 997;

	/**
	 * Status indicating that the package declaration in a <code>ICompilationUnit</code>
	 * does not correspond to the <code>IPackageFragment</code> it belongs to.
	 * The <code>getString</code> method of the associated status object
	 * gives the name of the package in which the <code>ICompilationUnit</code> is
	 * declared.
	 */
    int INVALID_PACKAGE = 998;

	/**
	 * Status indicating that the corresponding resource has no local contents yet.
	 * This might happen when attempting to use a resource before its contents
	 * has been made locally available.
	 */
    int NO_LOCAL_CONTENTS = 999;

	 /**
	  * Status indicating that a .classpath file is ill-formed, and thus cannot
	  * be read/written successfully.
	  * @since 2.1
	  */
     int INVALID_CLASSPATH_FILE_FORMAT = 1000;

	 /**
	  * Status indicating that a project is involved in a build path cycle.
	  * @since 2.1
	  */
     int CLASSPATH_CYCLE = 1001;

	/**
	 * Status constant indicating that an inclusion or an exclusion pattern got specified
	 * on a classpath source entry, though it was explicitely disabled
	 * according to its project preference settings.
	 * @see org.eclipse.jdt.core.IJavaProject#getOptions(boolean)
	 * @since 2.1
	 */
    int DISABLED_CP_EXCLUSION_PATTERNS = 1002;

	/**
	 * Status constant indicating that a specific output location got associated
	 * with a source entry, though it was explicitely disabled according to its project
	 * preference settings.
	 * @see org.eclipse.jdt.core.IJavaProject#getOptions(boolean)
	 * @since 2.1
	 */
    int DISABLED_CP_MULTIPLE_OUTPUT_LOCATIONS = 1003;

	/**
	 * Status constant indicating that a project is prerequisiting some library for which the
	 * classfile JDK version level is more recent than the project JDK target level setting.
	 * This can indicate some binary incompatibility issues later on.
	 * @since 3.0
	 */
    int INCOMPATIBLE_JDK_LEVEL	= 1004;

	/**
	 * Status constant indicating that a compiler failure occurred.
	 * @since 3.0
	 */
    int COMPILER_FAILURE	= 1005;
	/**
	 * Status constant indicating that an element is not on its project's claspath.
	 * @since 3.1
	 */
    int ELEMENT_NOT_ON_CLASSPATH	= 1006;
	/**
	 * Status constant indicating that a compiler option is invalid.
	 * @since 3.1
	 */
//	public static final int INVALID_COMPILER_OPTION = 1007;
	/**
	 * <p>Status constant indicating that the attached javadoc content cannot be retrieved due to multiple reasons:
	 * invalid url, incorrect proxy, wrong authentication,...</p>
	 *
	 * @since 3.2
	 */
    int CANNOT_RETRIEVE_ATTACHED_JAVADOC = 1008;
	/**
	 * <p>Status constant indicating that the attached javadoc content format is unrecognized.</p>
	 *
	 * @since 3.2
	 */
    int UNKNOWN_JAVADOC_FORMAT = 1009;
	/**
	 * <p>Status constant indicating that the variable is deprecated.</p>
	 *
	 * @since 3.3
	 */
    int DEPRECATED_VARIABLE = 1010;

	/**
	 * <p>Status constant indicating that a text edit can not be applied as there
	 * is a problem with the text edit location.</p>
	 *
	 * @since 3.4
	 */
    int BAD_TEXT_EDIT_LOCATION = 1011;

	/**
	 * <p>Status constant indicating that the attached javadoc content cannot be retrieved due to timeout
	 * @since 3.7
	 */
    int CANNOT_RETRIEVE_ATTACHED_JAVADOC_TIMEOUT = 1012;

	/**
	 * <p>Status constant indicating that the default or specific output folder is overlapping
	 * with another source location. </p>
	 * @since 3.6.4
	 */
    int OUTPUT_LOCATION_OVERLAPPING_ANOTHER_SOURCE = 1013;

	/**
	 * <p>Status constant indicating that an external annotation path is invalid.</p>
	 * @since 3.12
	 * @see org.eclipse.jdt.core.IClasspathAttribute#EXTERNAL_ANNOTATION_PATH
	 */
    int CP_INVALID_EXTERNAL_ANNOTATION_PATH = 1014;

	/**
	 * Status constant indicating that a source folder marked to contain test sources has no separate output location, but the project also contains main sources.
	 * preference settings.
	 * @see IClasspathAttribute#TEST
	 * @since 3.14
	 */
    int TEST_SOURCE_REQUIRES_SEPARATE_OUTPUT_LOCATION = 1015;

	/**
	 * Status constant indicating that the output folder of a source folder marked to contain test sources is not separate from the output folders of main sources.
	 * @see IClasspathAttribute#TEST
	 * @since 3.14
	 */
    int TEST_OUTPUT_FOLDER_MUST_BE_SEPARATE_FROM_MAIN_OUTPUT_FOLDERS = 1016;

	/**
	 * <p>
	 * Status constant indicating that the project has only main source folders but depends on a project that has only
	 * test source folders.
	 * </p>
	 *
	 * @since 3.16
	 */
    int MAIN_ONLY_PROJECT_DEPENDS_ON_TEST_ONLY_PROJECT = 1017;

}
