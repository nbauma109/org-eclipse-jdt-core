/*******************************************************************************
 * Copyright (c) 2000, 2021 IBM Corporation and others.
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
 *     Stephan Herrmann - Contributions for
 *     							bug 349326 - [1.7] new warning for missing try-with-resources
 *     							bug 359362 - FUP of bug 349326: Resource leak on non-Closeable resource
 *								bug 186342 - [compiler][null] Using annotations for null checking
 *								bug 358903 - Filter practically unimportant resource leak warnings
 *								bug 400421 - [compiler] Null analysis for fields does not take @com.google.inject.Inject into account
 *								bug 382069 - [null] Make the null analysis consider JUnit's assertNotNull similarly to assertions
 *								Bug 410218 - Optional warning for arguments of "unexpected" types to Map#get(Object), Collection#remove(Object) et al.
 *     Jesper S Moller <jesper@selskabet.org> -  Contributions for
 *								Bug 412153 - [1.8][compiler] Check validity of annotations which may be repeatable
 *     Ulrich Grave <ulrich.grave@gmx.de> - Contributions for
 *                              bug 386692 - Missing "unused" warning on "autowired" fields
 *     Pierre-Yves B. <pyvesdev@gmail.com> - Contribution for
 *                              bug 542520 - [JUnit 5] Warning The method xxx from the type X is never used locally is shown when using MethodSource
 *******************************************************************************/
package org.eclipse.jdt.internal.compiler.lookup;

public interface TypeIds {

    //base type void null undefined Object String
	//should have an id that is 0<= id <= 15
    // The IDs below must be representable using 4 bits so as to fit in operator signatures.
    int T_undefined = 0; // should not be changed
	int T_JavaLangObject = 1;
	int T_char = 2;
	int T_byte = 3;
	int T_short = 4;
	int T_boolean = 5;
	int T_void = 6;
	int T_long = 7;
	int T_double = 8;
	int T_float = 9;
	int T_int = 10;
	int T_JavaLangString = 11;
	int T_null = 12;

	//=========end of 4 bits constraint===========

	// well-known exception types
    int T_JavaLangClass = 16;
	int T_JavaLangStringBuffer = 17;
	int T_JavaLangSystem = 18;
	int T_JavaLangError = 19;
	int T_JavaLangReflectConstructor = 20;
	int T_JavaLangThrowable = 21;
	int T_JavaLangNoClassDefError = 22;
	int T_JavaLangClassNotFoundException = 23;
	int T_JavaLangRuntimeException = 24;
	int T_JavaLangException = 25;

	// wrapper types
    int T_JavaLangByte = 26;
	int T_JavaLangShort = 27;
	int T_JavaLangCharacter = 28;
	int T_JavaLangInteger = 29;
	int T_JavaLangLong = 30;
	int T_JavaLangFloat = 31;
	int T_JavaLangDouble = 32;
	int T_JavaLangBoolean = 33;
	int T_JavaLangVoid = 34;

	// 1.4 features
    int T_JavaLangAssertionError = 35;

	// array interfaces
    int T_JavaLangCloneable = 36;
	int T_JavaIoSerializable = 37;

	// 1.5 features
    int T_JavaLangIterable = 38;
	int T_JavaUtilIterator = 39;
	int T_JavaLangStringBuilder = 40;
	int T_JavaLangEnum = 41;
	int T_JavaLangIllegalArgumentException = 42;
	int T_JavaLangAnnotationAnnotation = 43;
	int T_JavaLangDeprecated = 44;
	int T_JavaLangAnnotationDocumented = 45;
	int T_JavaLangAnnotationInherited = 46;
	int T_JavaLangOverride = 47;
	int T_JavaLangAnnotationRetention = 48;
	int T_JavaLangSuppressWarnings = 49;
	int T_JavaLangAnnotationTarget = 50;
	int T_JavaLangAnnotationRetentionPolicy = 51;
	int T_JavaLangAnnotationElementType = 52;

	int T_JavaIoPrintStream = 53;

	int T_JavaLangReflectField = 54;
	int T_JavaLangReflectMethod = 55;

	int T_JavaIoExternalizable = 56;
	int T_JavaIoObjectStreamException = 57;
	int T_JavaIoException = 58;

	int T_JavaUtilCollection = 59;

	// java 7
    int T_JavaLangSafeVarargs = 60;

	int T_JavaLangInvokeMethodHandlePolymorphicSignature = 61;

	// java 7 java.lang.AutoCloseable
    int T_JavaLangAutoCloseable = 62;

	// new in 3.8 for null annotations, removed in 4.6 (ids 65-67)

	// new in 3.8 to identify org.eclipse.core.runtime.Assert
    int T_OrgEclipseCoreRuntimeAssert = 68;
	// new in 3.9 to identify more assertion utilities:
    int T_JunitFrameworkAssert = 69;
	int T_OrgJunitAssert = 70;
	int T_OrgApacheCommonsLangValidate = 71;
	int T_OrgApacheCommonsLang3Validate = 72;
	int T_ComGoogleCommonBasePreconditions = 73;
	int T_JavaUtilObjects = 74;
	// new in 3.26 to identify more assertion utilities:
    int T_OrgJunitJupiterApiAssertions = 75;

	// java 8
    int T_JavaLangFunctionalInterface = 77;

	// new in 3.9 to identify known @Inject annotations
    int T_JavaxInjectInject = 80;
	int T_ComGoogleInjectInject = 81;

	// @Autowired
    int T_OrgSpringframeworkBeansFactoryAnnotationAutowired = 82;

	// Java 8 - JEP 120
    int T_JavaLangAnnotationRepeatable = 90;

	// classes with methods with "dangerous" signatures:
    int T_JavaUtilMap = 91;
	int T_JavaUtilList = 92;

	// @MethodSource
    int T_OrgJunitJupiterParamsProviderMethodSource = 93;

	// Java 14 preview
    int T_JavaLangRecord = 93;

	int T_JdkInternalPreviewFeature = 94;

	// If you add new type id, make sure to bump up T_LastWellKnownTypeId if there is a cross over.
    int T_LastWellKnownTypeId = 128;


	int NoId = Integer.MAX_VALUE;

	int IMPLICIT_CONVERSION_MASK = 0xFF;
	int COMPILE_TYPE_MASK = 0xF;

	// implicit conversions: <compileType> to <runtimeType>  (note: booleans are integers at runtime)
    int Boolean2Int = T_boolean + (T_int << 4);
	int Boolean2String = T_boolean + (T_JavaLangString << 4);
	int Boolean2Boolean = T_boolean + (T_boolean << 4);
	int Byte2Byte = T_byte + (T_byte << 4);
	int Byte2Short = T_byte + (T_short << 4);
	int Byte2Char = T_byte + (T_char << 4);
	int Byte2Int = T_byte + (T_int << 4);
	int Byte2Long = T_byte + (T_long << 4);
	int Byte2Float = T_byte + (T_float << 4);
	int Byte2Double = T_byte + (T_double << 4);
	int Byte2String = T_byte + (T_JavaLangString << 4);
	int Short2Byte = T_short + (T_byte << 4);
	int Short2Short = T_short + (T_short << 4);
	int Short2Char = T_short + (T_char << 4);
	int Short2Int = T_short + (T_int << 4);
	int Short2Long = T_short + (T_long << 4);
	int Short2Float = T_short + (T_float << 4);
	int Short2Double = T_short + (T_double << 4);
	int Short2String = T_short + (T_JavaLangString << 4);
	int Char2Byte = T_char + (T_byte << 4);
	int Char2Short = T_char + (T_short << 4);
	int Char2Char = T_char + (T_char << 4);
	int Char2Int = T_char + (T_int << 4);
	int Char2Long = T_char + (T_long << 4);
	int Char2Float = T_char + (T_float << 4);
	int Char2Double = T_char + (T_double << 4);
	int Char2String = T_char + (T_JavaLangString << 4);
	int Int2Byte = T_int + (T_byte << 4);
	int Int2Short = T_int + (T_short << 4);
	int Int2Char = T_int + (T_char << 4);
	int Int2Int = T_int + (T_int << 4);
	int Int2Long = T_int + (T_long << 4);
	int Int2Float = T_int + (T_float << 4);
	int Int2Double = T_int + (T_double << 4);
	int Int2String = T_int + (T_JavaLangString << 4);
	int Long2Byte = T_long + (T_byte << 4);
	int Long2Short = T_long + (T_short << 4);
	int Long2Char = T_long + (T_char << 4);
	int Long2Int = T_long + (T_int << 4);
	int Long2Long = T_long + (T_long << 4);
	int Long2Float = T_long + (T_float << 4);
	int Long2Double = T_long + (T_double << 4);
	int Long2String = T_long + (T_JavaLangString << 4);
	int Float2Byte = T_float + (T_byte << 4);
	int Float2Short = T_float + (T_short << 4);
	int Float2Char = T_float + (T_char << 4);
	int Float2Int = T_float + (T_int << 4);
	int Float2Long = T_float + (T_long << 4);
	int Float2Float = T_float + (T_float << 4);
	int Float2Double = T_float + (T_double << 4);
	int Float2String = T_float + (T_JavaLangString << 4);
	int Double2Byte = T_double + (T_byte << 4);
	int Double2Short = T_double + (T_short << 4);
	int Double2Char = T_double + (T_char << 4);
	int Double2Int = T_double + (T_int << 4);
	int Double2Long = T_double + (T_long << 4);
	int Double2Float = T_double + (T_float << 4);
	int Double2Double = T_double + (T_double << 4);
	int Double2String = T_double + (T_JavaLangString << 4);
	int String2String = T_JavaLangString + (T_JavaLangString << 4);
	int Object2String = T_JavaLangObject + (T_JavaLangString << 4);
	int Null2Null = T_null + (T_null << 4);
	int Null2String = T_null + (T_JavaLangString << 4);
	int Object2Object = T_JavaLangObject + (T_JavaLangObject << 4);
	int Object2byte = T_JavaLangObject + (T_byte << 4);
	int Object2short = T_JavaLangObject + (T_short << 4);
	int Object2char = T_JavaLangObject + (T_char << 4);
	int Object2int = T_JavaLangObject + (T_int << 4);
	int Object2long = T_JavaLangObject + (T_long << 4);
	int Object2float = T_JavaLangObject + (T_float << 4);
	int Object2double = T_JavaLangObject + (T_double << 4);
	int Object2boolean = T_JavaLangObject + (T_boolean << 4);
	int BOXING = 0x200;
	int UNBOXING = 0x400;

	/**
	 * Marks a type whose type bits have not yet been initialized.
	 * @see ReferenceBinding#hasTypeBit(int)
	 */
    int BitUninitialized = 0x8000000;
	/**
	 * Marks all sub-types of java.lang.AutoCloseable.
	 * @see ReferenceBinding#hasTypeBit(int)
	 */
    int BitAutoCloseable = 1;
	/**
	 * Marks all sub-types of java.io.Closeable.
	 * @see ReferenceBinding#hasTypeBit(int)
	 */
    int BitCloseable = 2;
	/**
	 * Bit for members of a white list:
	 * Subtypes of Closeable that wrap another resource without directly holding any OS resources.
	 */
    int BitWrapperCloseable = 4;
	/**
	 * Bit for members of a white list:
	 * Subtypes of Closeable that do not hold an OS resource that needs to be released.
	 */
    int BitResourceFreeCloseable = 8;

	int BitUninternedType = 16;

	/** Bit for a type configured as a @NonNull annotation. */
    int BitNonNullAnnotation = 32;
	/** Bit for a type configured as a @Nullable annotation. */
    int BitNullableAnnotation = 64;
	/** Bit for a type configured as a @NonNullByDefault annotation. */
    int BitNonNullByDefaultAnnotation = 128;
	int BitAnyNullAnnotation = BitNonNullAnnotation | BitNullableAnnotation | BitNonNullByDefaultAnnotation;

	/** Mark subtypes of Map to analyze dangerous get/remove et al. */
    int BitMap = 256;

	/** Mark subtypes of Collection to analyze dangerous contains/remove. */
    int BitCollection = 512;

	/** Mark subtypes of List to analyze dangerous indexOf. */
    int BitList = 1024;

	/**
	 * Set of type bits that should be inherited by any sub types.
	 */
    int InheritableBits = BitAutoCloseable | BitCloseable | BitUninternedType | BitMap | BitCollection | BitList ;

	static int getCategory(int typeId) {
		return typeId == TypeIds.T_double || typeId == TypeIds.T_long ? 2 : 1;
	}
}
