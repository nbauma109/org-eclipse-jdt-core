/*******************************************************************************
 * Copyright (c) 2006, 2019 IBM Corporation and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.osgi.internal.signedcontent;

public interface SignedContentConstants {

	String SHA1_STR = "SHA1"; //$NON-NLS-1$
	String SHA256_STR = "SHA256"; //$NON-NLS-1$
	String SHA384_STR = "SHA384"; //$NON-NLS-1$
	String SHA512_STR = "SHA512"; //$NON-NLS-1$
	String SHA224_STR = "SHA224"; //$NON-NLS-1$
	String SHA512_224_STR = "SHA512-224"; //$NON-NLS-1$
	String SHA512_256_STR = "SHA512-256"; //$NON-NLS-1$
	String MD5_STR = "MD5"; //$NON-NLS-1$
	String MD2_STR = "MD2"; //$NON-NLS-1$

	String DOT_DSA = ".DSA"; //$NON-NLS-1$
	String DOT_RSA = ".RSA"; //$NON-NLS-1$
	String DOT_SF = ".SF"; //$NON-NLS-1$
	String SIG_DASH = "SIG-"; //$NON-NLS-1$
	String META_INF = "META-INF/"; //$NON-NLS-1$
	String META_INF_MANIFEST_MF = "META-INF/MANIFEST.MF"; //$NON-NLS-1$
	String[] EMPTY_STRING = {};

	//
	// following are variables and methods to cache the entries related data
	// for a given MF file
	//
    String MF_ENTRY_NEWLN_NAME = "\nName: "; //$NON-NLS-1$
	String MF_ENTRY_NAME = "Name: "; //$NON-NLS-1$
	String MF_DIGEST_PART = "-Digest: "; //$NON-NLS-1$
	String digestManifestSearch = "-Digest-Manifest: "; //$NON-NLS-1$
	int digestManifestSearchLen = digestManifestSearch.length();

	int[] SIGNEDDATA_OID = {1, 2, 840, 113549, 1, 7, 2};
	int[] MD5_OID = {1, 2, 840, 113549, 2, 5};
	int[] MD2_OID = {1, 2, 840, 113549, 2, 2};

	int[] SHA1_OID = {1, 3, 14, 3, 2, 26};

	int[] SHA256_OID = {2, 16, 840, 1, 101, 3, 4, 2, 1};
	int[] SHA384_OID = {2, 16, 840, 1, 101, 3, 4, 2, 2};
	int[] SHA512_OID = {2, 16, 840, 1, 101, 3, 4, 2, 3};
	int[] SHA224_OID = {2, 16, 840, 1, 101, 3, 4, 2, 4};
	int[] SHA512_224_OID = {2, 16, 840, 1, 101, 3, 4, 2, 5};
	int[] SHA512_256_OID = {2, 16, 840, 1, 101, 3, 4, 2, 6};

	int[] DSA_OID = {1, 2, 840, 10040, 4, 1};
	int[] RSA_OID = {1, 2, 840, 113549, 1, 1, 1};

	// constant for trust engine service
    String TRUST_ENGINE = "osgi.signedcontent.trust.engine"; //$NON-NLS-1$
	Object DEFAULT_TRUST_ENGINE = "org.eclipse.osgi"; //$NON-NLS-1$

	// constant for the timestamp related
    int[] TIMESTAMP_OID = {1, 2, 840, 113549, 1, 9, 16, 2, 14};
	int[] TIMESTAMP_TST_OID = {1, 2, 840, 113549, 1, 9, 16, 1, 4};
	int[] SIGNING_TIME = {1, 2, 840, 113549, 1, 9, 5};

}
