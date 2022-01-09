/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
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
package org.eclipse.jdt.internal.compiler.env;

import java.util.jar.Manifest;

import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

public interface IModule {

	IModuleReference[] NO_MODULE_REFS = {};
	IPackageExport[] NO_EXPORTS = {};
	char[][] NO_USES = {};
	IService[] NO_PROVIDES = {};
	IModule[] NO_MODULES = {};
	IPackageExport[] NO_OPENS = {};

	String MODULE_INFO = "module-info"; //$NON-NLS-1$
	String MODULE_INFO_JAVA = "module-info.java"; //$NON-NLS-1$
	String MODULE_INFO_CLASS = "module-info.class"; //$NON-NLS-1$

	char[] name();

	IModuleReference[] requires();

	IPackageExport[] exports();

	char[][] uses();

	IService[] provides();

	/*
	 * the opens package statement is very similar to package export statement, hence
	 * the same internal models are being used here.
	 */
    IPackageExport[] opens();

	interface IModuleReference {
		char[] name();
		default boolean isTransitive() {
			return (getModifiers() & ClassFileConstants.ACC_TRANSITIVE) != 0;
		}
		int getModifiers();
		default boolean isStatic() {
			return (getModifiers() & ClassFileConstants.ACC_STATIC_PHASE) != 0;
		}
	}

	interface IPackageExport {
		char[] name();
		char[][] targets();
		default boolean isQualified() {
			char[][] targets = targets();
			return targets != null && targets.length > 0;
		}
	}

	interface IService {
		char[] name();
		char[][] with();
	}

	default boolean isAutomatic() {
		return false;
	}
	default boolean isAutoNameFromManifest() {
		return false;
	}
	boolean isOpen();


	static IModule createAutomatic(char[] moduleName, boolean fromManifest) {
		final class AutoModule implements IModule {
			char[] name;
			boolean nameFromManifest;
			public AutoModule(char[] name, boolean nameFromManifest) {
				this.name = name;
				this.nameFromManifest = nameFromManifest;
			}
			@Override
			public char[] name() {
				return this.name;
			}

			@Override
			public IModuleReference[] requires() {
				return IModule.NO_MODULE_REFS;
			}

			@Override
			public IPackageExport[] exports() {
				return IModule.NO_EXPORTS;
			}

			@Override
			public char[][] uses() {
				return IModule.NO_USES;
			}

			@Override
			public IService[] provides() {
				return IModule.NO_PROVIDES;
			}

			@Override
			public IPackageExport[] opens() {
				return NO_OPENS;
			}

			@Override
			public boolean isAutomatic() {
				return true;
			}
			@Override
			public boolean isAutoNameFromManifest() {
				return this.nameFromManifest;
			}
			@Override
			public boolean isOpen() {
				return false;
			}
		}
		return new AutoModule(moduleName, fromManifest);
	}

	static IModule createAutomatic(String fileName, boolean isFile, Manifest manifest) {
		boolean fromManifest = true;
		char[] inferredName = AutomaticModuleNaming.determineAutomaticModuleNameFromManifest(manifest);
		if (inferredName == null) {
			fromManifest = false;
			inferredName = AutomaticModuleNaming.determineAutomaticModuleNameFromFileName(fileName, true, isFile);
		}
		return createAutomatic(inferredName, fromManifest);
	}
}
