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
 *******************************************************************************/

package org.eclipse.jdt.internal.compiler.parser;

import org.eclipse.jdt.core.compiler.CharOperation;

/**
 * Javadoc tag constants.
 *
 * @since 3.2
 */
public interface JavadocTagConstants {

	// recognized tags
    char[] TAG_DEPRECATED = "deprecated".toCharArray(); //$NON-NLS-1$
	char[] TAG_PARAM = "param".toCharArray(); //$NON-NLS-1$
	char[] TAG_RETURN = "return".toCharArray(); //$NON-NLS-1$
	char[] TAG_THROWS = "throws".toCharArray(); //$NON-NLS-1$
	char[] TAG_EXCEPTION = "exception".toCharArray(); //$NON-NLS-1$
	char[] TAG_SEE = "see".toCharArray(); //$NON-NLS-1$
	char[] TAG_LINK = "link".toCharArray(); //$NON-NLS-1$
	char[] TAG_LINKPLAIN = "linkplain".toCharArray(); //$NON-NLS-1$
	char[] TAG_INHERITDOC = "inheritDoc".toCharArray(); //$NON-NLS-1$
	char[] TAG_VALUE = "value".toCharArray(); //$NON-NLS-1$
	char[] TAG_AUTHOR = "author".toCharArray(); //$NON-NLS-1$
	char[] TAG_CODE = "code".toCharArray(); //$NON-NLS-1$
	char[] TAG_DOC_ROOT = "docRoot".toCharArray(); //$NON-NLS-1$
	char[] TAG_LITERAL = "literal".toCharArray(); //$NON-NLS-1$
	char[] TAG_SERIAL = "serial".toCharArray(); //$NON-NLS-1$
	char[] TAG_SERIAL_DATA = "serialData".toCharArray(); //$NON-NLS-1$
	char[] TAG_SERIAL_FIELD = "serialField".toCharArray(); //$NON-NLS-1$
	char[] TAG_SINCE = "since".toCharArray(); //$NON-NLS-1$
	char[] TAG_VERSION = "version".toCharArray(); //$NON-NLS-1$
	char[] TAG_CATEGORY = "category".toCharArray(); //$NON-NLS-1$
	char[] TAG_SYSTEM_PROPERTY = "systemProperty".toCharArray(); //$NON-NLS-1$
	char[] TAG_USES = "uses".toCharArray(); //$NON-NLS-1$
	char[] TAG_PROVIDES = "provides".toCharArray(); //$NON-NLS-1$
	char[] TAG_HIDDEN = "hidden".toCharArray(); //$NON-NLS-1$
	char[] TAG_INDEX = "index".toCharArray(); //$NON-NLS-1$
	char[] TAG_SUMMARY = "summary".toCharArray(); //$NON-NLS-1$
	char[] TAG_API_NOTE = "apiNote".toCharArray(); //$NON-NLS-1$
	char[] TAG_IMPL_SPEC = "implSpec".toCharArray(); //$NON-NLS-1$
	char[] TAG_IMPL_NOTE = "implNote".toCharArray(); //$NON-NLS-1$

	// tags lengthes
    int TAG_DEPRECATED_LENGTH = TAG_DEPRECATED.length;
	int TAG_PARAM_LENGTH = TAG_PARAM.length;
	int TAG_RETURN_LENGTH = TAG_RETURN.length;
	int TAG_THROWS_LENGTH = TAG_THROWS.length;
	int TAG_EXCEPTION_LENGTH = TAG_EXCEPTION.length;
	int TAG_SEE_LENGTH = TAG_SEE.length;
	int TAG_LINK_LENGTH = TAG_LINK.length;
	int TAG_LINKPLAIN_LENGTH = TAG_LINKPLAIN.length;
	int TAG_INHERITDOC_LENGTH = TAG_INHERITDOC.length;
	int TAG_VALUE_LENGTH = TAG_VALUE.length;
	int TAG_CATEGORY_LENGTH = TAG_CATEGORY.length;
	int TAG_AUTHOR_LENGTH = TAG_AUTHOR.length;
	int TAG_SERIAL_LENGTH = TAG_SERIAL.length;
	int TAG_SERIAL_DATA_LENGTH = TAG_SERIAL_DATA.length;
	int TAG_SERIAL_FIELD_LENGTH = TAG_SERIAL_FIELD.length;
	int TAG_SINCE_LENGTH = TAG_SINCE.length;
	int TAG_VERSION_LENGTH = TAG_VERSION.length;
	int TAG_CODE_LENGTH = TAG_CODE.length;
	int TAG_LITERAL_LENGTH = TAG_LITERAL.length;
	int TAG_DOC_ROOT_LENGTH = TAG_DOC_ROOT.length;
	int TAG_SYSTEM_PROPERTY_LENGTH = TAG_SYSTEM_PROPERTY.length;
	int TAG_USES_LENGTH = TAG_USES.length;
	int TAG_PROVIDES_LENGTH = TAG_PROVIDES.length;
	int TAG_HIDDEN_LENGTH = TAG_HIDDEN.length;
	int TAG_INDEX_LENGTH = TAG_INDEX.length;
	int TAG_SUMMARY_LENGTH = TAG_SUMMARY.length;
	int TAG_API_NOTE_LENGTH = TAG_API_NOTE.length;
	int TAG_IMPL_SPEC_LENGTH = TAG_IMPL_SPEC.length;
	int TAG_IMPL_NOTE_LENGTH = TAG_IMPL_NOTE.length;

	// tags value
    int NO_TAG_VALUE = 0;
	int TAG_DEPRECATED_VALUE = 1;
	int TAG_PARAM_VALUE = 2;
	int TAG_RETURN_VALUE = 3;
	int TAG_THROWS_VALUE = 4;
	int TAG_EXCEPTION_VALUE = 5;
	int TAG_SEE_VALUE = 6;
	int TAG_LINK_VALUE = 7;
	int TAG_LINKPLAIN_VALUE = 8;
	int TAG_INHERITDOC_VALUE = 9;
	int TAG_VALUE_VALUE = 10;
	int TAG_CATEGORY_VALUE = 11;
	int TAG_AUTHOR_VALUE = 12;
	int TAG_SERIAL_VALUE = 13;
	int TAG_SERIAL_DATA_VALUE = 14;
	int TAG_SERIAL_FIELD_VALUE = 15;
	int TAG_SINCE_VALUE = 16;
	int TAG_VERSION_VALUE = 17;
	int TAG_CODE_VALUE = 18;
	int TAG_LITERAL_VALUE = 19;
	int TAG_DOC_ROOT_VALUE = 20;
	int TAG_SYSTEM_PROPERTY_VALUE=21;
	int TAG_USES_VALUE=22;
	int TAG_PROVIDES_VALUE=23;
	int TAG_HIDDEN_VALUE = 24;
	int TAG_INDEX_VALUE = 25;
	int TAG_SUMMARY_VALUE = 26;
	int TAG_API_NOTE_VALUE = 27;
	int TAG_IMPL_SPEC_VALUE = 28;
	int TAG_IMPL_NOTE_VALUE = 29;
	int TAG_OTHERS_VALUE = 100;
	// Tag names array
    char[][] TAG_NAMES = {
		CharOperation.NO_CHAR,
		TAG_DEPRECATED,		/* 1 */
		TAG_PARAM,				/* 2 */
		TAG_RETURN,				/* 3 */
		TAG_THROWS,				/* 4 */
		TAG_EXCEPTION,			/* 5 */
		TAG_SEE,						/* 6 */
		TAG_LINK,						/* 7 */
		TAG_LINKPLAIN,			/* 8 */
		TAG_INHERITDOC,		/* 9 */
		TAG_VALUE,					/* 10 */
		TAG_CATEGORY,			/* 11 */
		TAG_AUTHOR,				/* 12 */
		TAG_SERIAL,				/* 13 */
		TAG_SERIAL_DATA,	/* 14 */
		TAG_SERIAL_FIELD,	/* 15 */
		TAG_SINCE,					/* 16 */
		TAG_VERSION,				/* 17 */
		TAG_CODE,					/* 18 */
		TAG_LITERAL,				/* 19 */
		TAG_DOC_ROOT,			/* 20 */
		TAG_SYSTEM_PROPERTY,    /* 21 */
		TAG_USES,				/* 22 */
		TAG_PROVIDES,			/* 23 */
		TAG_HIDDEN,				/* 24 */
		TAG_INDEX,				/* 25 */
		TAG_SUMMARY,			/* 26 */
		TAG_API_NOTE,			/* 27 */
		TAG_IMPL_SPEC,			/* 28 */
		TAG_IMPL_NOTE,			/* 29 */
	};

	// tags expected positions
    int ORDERED_TAGS_NUMBER = 3;
	int PARAM_TAG_EXPECTED_ORDER = 0;
	int THROWS_TAG_EXPECTED_ORDER = 1;
	int SEE_TAG_EXPECTED_ORDER = 2;

	/*
	 * Tag kinds indexes
	 */
    int BLOCK_IDX = 0;
	int INLINE_IDX = 1;

	// href tag
    char[] HREF_TAG = {'h', 'r', 'e', 'f'};
	/*
	 * Tags versions
	 */
    char[][][] BLOCK_TAGS = {
		// since 1.0
		{ TAG_AUTHOR, TAG_DEPRECATED, TAG_EXCEPTION, TAG_PARAM, TAG_RETURN, TAG_SEE, TAG_VERSION, TAG_CATEGORY /* 1.6 tag but put here as we support it for all compliances */ },
		// since 1.1
		{ TAG_SINCE },
		// since 1.2
		{ TAG_SERIAL, TAG_SERIAL_DATA, TAG_SERIAL_FIELD , TAG_THROWS },
		// since 1.3
		{},
		// since 1.4
		{},
		// since 1.5
		{},
		// since 1.6
		{},
		// since 1.7
		{},
		// since 1.8
		{TAG_API_NOTE, TAG_IMPL_SPEC, TAG_IMPL_NOTE},
		// since 9
		{ TAG_HIDDEN, TAG_USES, TAG_PROVIDES },
		// since 10
		{},
		// since 11
		{},
		//since 12
		{},
		//since 13
		{},
		//since 14
		{},
		//since 15
		{},
		//since 16
		{},
		// since 17
		{},
	};
	char[][][] INLINE_TAGS = {
		// since 1.0
		{},
		// since 1.1
		{},
		// since 1.2
		{ TAG_LINK },
		// since 1.3
		{ TAG_DOC_ROOT },
		// since 1.4
		{ TAG_INHERITDOC, TAG_LINKPLAIN, TAG_VALUE },
		// since 1.5
		{ TAG_CODE, TAG_LITERAL },
		// since 1.6
		{},
		// since 1.7
		{},
		// since 1.8
		{},
		// since 9
		{ TAG_INDEX },
		// since 10
		{ TAG_SUMMARY },
		// since 11
		{},
		//since 12
		{TAG_SYSTEM_PROPERTY},
		//since 13
		{},
		//since 14
		{},
		//since 15
		{},
		//since 16
		{},
		//since 17
		{}
	};
	int INLINE_TAGS_LENGTH = INLINE_TAGS.length;
	int BLOCK_TAGS_LENGTH = BLOCK_TAGS.length;
	int ALL_TAGS_LENGTH = BLOCK_TAGS_LENGTH+INLINE_TAGS_LENGTH;

	short TAG_TYPE_NONE = 0;
	short TAG_TYPE_INLINE = 1;
	short TAG_TYPE_BLOCK = 2;
	short[] JAVADOC_TAG_TYPE = {
		TAG_TYPE_NONE, 		// NO_TAG_VALUE = 0;
		TAG_TYPE_BLOCK,		// TAG_DEPRECATED_VALUE = 1;
		TAG_TYPE_BLOCK,		// TAG_PARAM_VALUE = 2;
		TAG_TYPE_BLOCK,		// TAG_RETURN_VALUE = 3;
		TAG_TYPE_BLOCK,		// TAG_THROWS_VALUE = 4;
		TAG_TYPE_BLOCK,		// TAG_EXCEPTION_VALUE = 5;
		TAG_TYPE_BLOCK,		// TAG_SEE_VALUE = 6;
		TAG_TYPE_INLINE,	// TAG_LINK_VALUE = 7;
		TAG_TYPE_INLINE,	// TAG_LINKPLAIN_VALUE = 8;
		TAG_TYPE_INLINE,	// TAG_INHERITDOC_VALUE = 9;
		TAG_TYPE_INLINE,	// TAG_VALUE_VALUE = 10;
		TAG_TYPE_BLOCK,		// TAG_CATEGORY_VALUE = 11;
		TAG_TYPE_BLOCK,		// TAG_AUTHOR_VALUE = 12;
		TAG_TYPE_BLOCK,		// TAG_SERIAL_VALUE = 13;
		TAG_TYPE_BLOCK,		// TAG_SERIAL_DATA_VALUE = 14;
		TAG_TYPE_BLOCK,		// TAG_SERIAL_FIELD_VALUE = 15;
		TAG_TYPE_BLOCK,		// TAG_SINCE_VALUE = 16;
		TAG_TYPE_BLOCK,		// TAG_VERSION_VALUE = 17;
		TAG_TYPE_INLINE,	// TAG_CODE_VALUE = 18;
		TAG_TYPE_INLINE,	// TAG_LITERAL_VALUE = 19;
		TAG_TYPE_INLINE,	// TAG_DOC_ROOT_VALUE = 20;
		TAG_TYPE_INLINE,    // TAG_DOC_SYSTEM_PROPERTY = 21
		TAG_TYPE_BLOCK,		// TAG_USES_VALUE = 22;
		TAG_TYPE_BLOCK,		// TAG_PROVIDES_VALUE = 23;
		TAG_TYPE_BLOCK,		// TAG_HIDDEN_VALUE = 24;
		TAG_TYPE_INLINE,	// TAG_INDEX_VALUE = 25;
		TAG_TYPE_INLINE,	// TAG_SUMMARY_VALUE = 26;
		TAG_TYPE_BLOCK,		// TAG_API_NOTE = 27;
		TAG_TYPE_BLOCK,		// TAG_IMPL_SPEC = 28;
		TAG_TYPE_BLOCK,		// TAG_IMPL_NOTE = 29;
	};
	/*
	 * Tags usage
	 */
    char[][] PACKAGE_TAGS = {
		TAG_SEE,
		TAG_SINCE,
		TAG_SERIAL,
		TAG_AUTHOR,
		TAG_VERSION,
		TAG_CATEGORY,
		TAG_LINK,
		TAG_LINKPLAIN,
		TAG_DOC_ROOT,
		TAG_VALUE,
		TAG_SYSTEM_PROPERTY,
		TAG_HIDDEN,
		TAG_INDEX,
		TAG_SUMMARY,
		TAG_API_NOTE,
		TAG_IMPL_SPEC,
		TAG_IMPL_NOTE,
	};
	char[][] COMPILATION_UNIT_TAGS = {};
	char[][] CLASS_TAGS = {
		TAG_SEE,
		TAG_SINCE,
		TAG_DEPRECATED,
		TAG_SERIAL,
		TAG_AUTHOR,
		TAG_VERSION,
		TAG_PARAM,
		TAG_CATEGORY,
		TAG_LINK,
		TAG_LINKPLAIN,
		TAG_DOC_ROOT,
		TAG_VALUE,
		TAG_CODE,
		TAG_LITERAL,
		TAG_SYSTEM_PROPERTY,
		TAG_HIDDEN,
		TAG_INDEX,
		TAG_SUMMARY,
		TAG_API_NOTE,
		TAG_IMPL_SPEC,
		TAG_IMPL_NOTE,
	};
	char[][] FIELD_TAGS = {
		TAG_SEE,
		TAG_SINCE,
		TAG_DEPRECATED,
		TAG_SERIAL,
		TAG_SERIAL_FIELD,
		TAG_CATEGORY,
		TAG_LINK,
		TAG_LINKPLAIN,
		TAG_DOC_ROOT,
		TAG_VALUE,
		TAG_CODE,
		TAG_LITERAL,
		TAG_SYSTEM_PROPERTY,
		TAG_INDEX,
		TAG_HIDDEN,
		TAG_SUMMARY,
		TAG_API_NOTE,
		TAG_IMPL_SPEC,
		TAG_IMPL_NOTE,
	};
	char[][] METHOD_TAGS = {
		TAG_SEE,
		TAG_SINCE,
		TAG_DEPRECATED,
		TAG_PARAM,
		TAG_RETURN,
		TAG_THROWS,
		TAG_EXCEPTION,
		TAG_SERIAL_DATA,
		TAG_CATEGORY,
		TAG_LINK,
		TAG_LINKPLAIN,
		TAG_INHERITDOC,
		TAG_DOC_ROOT,
		TAG_VALUE,
		TAG_CODE,
		TAG_LITERAL,
		TAG_SYSTEM_PROPERTY,
		TAG_HIDDEN,
		TAG_INDEX,
		TAG_SUMMARY,
		TAG_API_NOTE,
		TAG_IMPL_SPEC,
		TAG_IMPL_NOTE,
	};
	char[][] MODULE_TAGS = {
			TAG_SEE,
			TAG_SINCE,
			TAG_DEPRECATED,
			TAG_SERIAL,
			TAG_AUTHOR,
			TAG_VERSION,
			TAG_CATEGORY,
			TAG_LINK,
			TAG_LINKPLAIN,
			TAG_DOC_ROOT,
			TAG_VALUE,
			TAG_CODE,
			TAG_LITERAL,
			TAG_USES,
			TAG_PROVIDES,
			TAG_HIDDEN,
			TAG_INDEX,
			TAG_SUMMARY,
			TAG_API_NOTE,
			TAG_IMPL_SPEC,
			TAG_IMPL_NOTE,
		};
}
