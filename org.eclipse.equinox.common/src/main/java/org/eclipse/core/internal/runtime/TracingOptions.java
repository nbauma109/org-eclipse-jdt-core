package org.eclipse.core.internal.runtime;

import org.eclipse.osgi.service.debug.DebugOptionsListener;

public class TracingOptions {
	public static DebugOptionsListener DEBUG_OPTIONS_LISTENER = options -> {
    	TracingOptions.debug = options.getBooleanOption(Activator.PLUGIN_ID + "/debug", false); //$NON-NLS-1$

    	TracingOptions.debugProgressMonitors = TracingOptions.debug && options.getBooleanOption(Activator.PLUGIN_ID + "/progress_monitors", false); //$NON-NLS-1$
    };

	public static boolean debug;
	public static boolean debugProgressMonitors;
}
