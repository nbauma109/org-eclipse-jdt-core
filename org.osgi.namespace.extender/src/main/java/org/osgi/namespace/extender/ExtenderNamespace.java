/*
 * Copyright (c) OSGi Alliance (2012, 2014). All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.osgi.namespace.extender;

import org.osgi.resource.Namespace;

/**
 * Extender Capability and Requirement Namespace.
 * 
 * <p>
 * This class defines the names for the attributes and directives for this
 * namespace. All unspecified capability attributes are of type {@code String}
 * and are used as arbitrary matching attributes for the capability. The values
 * associated with the specified directive and attribute keys are of type
 * {@code String}, unless otherwise indicated.
 * 
 * @Immutable
 * @author $Id: 382b1f4aeae204895851c141f2f77d5723a7c3e7 $
 */
public final class ExtenderNamespace extends Namespace {

	/**
	 * Namespace name for extender capabilities and requirements.
	 * 
	 * <p>
	 * Also, the capability attribute used to specify the name of the extender.
	 */
	public static final String	EXTENDER_NAMESPACE							= "osgi.extender";

	/**
	 * The capability attribute contains the {@code Version} of the
	 * specification of the extender. The value of this attribute must be of
	 * type {@code Version}.
	 */
	public final static String	CAPABILITY_VERSION_ATTRIBUTE				= "version";

	private ExtenderNamespace() {
		// empty
	}
}
