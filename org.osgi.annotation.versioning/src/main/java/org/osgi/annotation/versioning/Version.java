/*******************************************************************************
 * Copyright (c) Contributors to the Eclipse Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0 
 *******************************************************************************/

package org.osgi.annotation.versioning;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify the version of a package.
 * <p>
 * This annotation is not retained at runtime. It is for use by tools to
 * generate bundle manifests or otherwise process the version of a package.
 * 
 * @see <a href= "https://docs.osgi.org/whitepaper/semantic-versioning/" >
 *      Semantic Versioning</a>
 * @author $Id: 6e4361486f9f41adbfadba45e4f07f83ea81864e $
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PACKAGE)
public @interface Version {
	/**
	 * The version of the annotated package.
	 * 
	 * <p>
	 * The version must be a valid OSGi version string.
	 */
	String value();
}
