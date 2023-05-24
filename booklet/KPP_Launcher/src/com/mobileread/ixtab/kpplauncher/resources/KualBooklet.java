/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.mobileread.ixtab.kpplauncher;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.lang.reflect.Method;
import java.net.URI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.amazon.kindle.booklet.AbstractBooklet;
import com.amazon.kindle.restricted.content.catalog.ContentCatalog;
import com.amazon.kindle.restricted.runtime.Framework;
import com.mobileread.ixtab.kpplauncher.resources.KualLog;

public class KualBooklet extends AbstractBooklet {

	public void start(URI contentURI) {
		new KualLog().append("KPPLauncher Started With URI " + contentURI.toString().replace("app://com.mobileread.ixtab.kpplauncher",""));
		try {
			new KualLog().append("Running command:");
			new KualLog().append("/bin/sh" + " -c" + " { /mnt/us/kpplauncher/kpplauncher.sh ".concat(contentURI.toString().replace("app://com.mobileread.ixtab.kpplauncher","")).concat(" ; } 2>>/var/tmp/KPPL.log "));
			Runtime.getRuntime().exec(
				new String[] { "/bin/sh", "-c", " /mnt/us/kpplauncher/kpplauncher.sh ".concat(contentURI.toString().replace("app://com.mobileread.ixtab.kpplauncher","")).concat("  2>>/var/tmp/KPPL.log ") }, null,
			// new String[] { "/mnt/us/koreader/koreader.sh ".concat(contentURI.toString().replace("app://com.mobileread.ixtab.kpplauncher","")) }, null,
			new File("/mnt/us/koreader/")).waitFor();
			new KualLog().append("KPP Application Closed.");
			new KualLog().append("Closing KPPLauncher");
			Runtime.getRuntime().exec("lipc-set-prop com.lab126.appmgrd start app://com.lab126.KPPMainApp?view=KPP_LIBRARY");
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	public KualBooklet() {
		new KualLog().append("HELLO FROM KINDLEBOOKLET PLEASE HELP");
		// try {
		// 	updateCCDB();
		// 	execute("Hello butt");
		// } catch (Throwable t) {
		// 	throw new RuntimeException(t);
		// }
	}
	// And this was always obfuscated...
	// NOTE: Pilfered from KPVBooklet (https://github.com/koreader/kpvbooklet/blob/master/src/com/github/chrox/kpvbooklet/ccadapter/CCAdapter.java)
	/**
	 * Perform CC request of type "query" and "change"
	 * @param req_type request type of "query" or "change"
	 * @param req_json request json string
	 * @return return json object
	 */
	private JSONObject ccPerform(String req_type, String req_json) {
		ContentCatalog CC = (ContentCatalog) Framework.getService(ContentCatalog.class);
		try {
			Method perform = null;

			// Enumeration approach
			Class[] signature = {String.class, String.class, int.class, int.class};
			Method[] methods = ContentCatalog.class.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				Class[] params = methods[i].getParameterTypes();
				if (params.length == signature.length) {
					int j;
					for (j = 0; j < signature.length && params[j].isAssignableFrom( signature[j] ); j++ ) {}
					if (j == signature.length) {
						perform = methods[i];
						break;
					}
				}
			}

			if (perform != null) {
				JSONObject json = (JSONObject) perform.invoke(CC, new Object[] { req_type, req_json, new Integer(200), new Integer(5) });
				return json;
			}
			else {
				new KualLog().append("Failed to find perform method, last access time won't be set on exit!");
				return new JSONObject();
			}
		} catch (Throwable t) {
			throw new RuntimeException(t.toString());
		}
	}

	// NOTE: Again, adapted from KPVBooklet ;)
	private void updateCCDB() {
		long lastAccess = new Date().getTime() / 1000L;
		String tag = "KOReader";	// Fancy sash in the top right corner of the thumbnail ;)
		// NOTE: Hard-code the path, as no-one should be using a custom .kual trigger...
		String path = JSONObject.escape("/mnt/us/documents/KOReader.app");
		String json_query = "{\"filter\":{\"Equals\":{\"value\":\"" + path + "\",\"path\":\"location\"}},\"type\":\"QueryRequest\",\"maxResults\":1,\"sortOrder\":[{\"order\":\"descending\",\"path\":\"lastAccess\"},{\"order\":\"ascending\",\"path\":\"titles[0].collation\"}],\"startIndex\":0,\"id\":1,\"resultType\":\"fast\"}";
		JSONObject json = ccPerform("query", json_query);
		JSONArray values = (JSONArray) json.get("values");
		JSONObject value = (JSONObject) values.get(0);
		String uuid = (String) value.get("uuid");
		String json_change = "{\"commands\":[{\"update\":{\"uuid\":\"" + uuid + "\",\"lastAccess\":" + lastAccess + ",\"displayTags\":[\"" + tag + "\"]" + "}}],\"type\":\"ChangeRequest\",\"id\":1}";
		ccPerform("change", json_change);
		//new KualLog().append("Set KUAL's lastAccess ccdb entry to " + lastAccess);
	}

	private Process execute(String pathOfJs)
			throws IOException, InterruptedException {

		// Check current privileges...
		// Call Gandalf for help if need be...
		String currentUsername = System.getProperty("user.name");
		if ("root".equals(currentUsername)) {} else {
			new KualLog().append("PRIV Hello");
			if (new File("/var/local/mkk/gandalf").exists()) {
				return Runtime.getRuntime().exec(
					new String[] { "/var/local/mkk/su", "-s", "/bin/ash", "-c", "{ /mnt/us/koreader/koreader.sh --kual --asap ; } 2>>/var/tmp/KPPL.log &" }, null,
					new File("/mnt/us/koreader/"));
			}
		}
		new KualLog().append("UNPRIV Hello");
		return Runtime.getRuntime().exec(
			new String[] { "/bin/sh", "-c", "{ /mnt/us/koreader/koreader.sh --kual --asap ; } 2>>/var/tmp/KPPL.log &" }, null,
			new File("/mnt/us/koreader/"));
	}

	public void stop() {
		/*
		 * This should really be run on the destroy() method, because stop()
		 * might be invoked multiple times. But in the destroy() method, it
		 * just won't work. Might be related with what the life cycle
		 * documentation says about not holding files open etc. after stop() was
		 * called. Anyway: seems to work, since we only set commandToRunOnExit at
		 * very specific times, where we'll always exit right after, so we can't really
		 * fire a random command during an unexpected stop event ;).
		 */
		//new KualLog().append("stop()");

		try {
			// NOTE: This can be a bit racey with destroy(),
			//	 so sleep for a teeny tiny bit so that our execute() call actually goes through...
			Thread.sleep(175);
		} catch (Exception ignored) {
			// can't do much, really. Too late for that :-)
		}

		super.stop();
	}

	public void destroy() {
		//new KualLog().append("destroy()");
		// Try to cleanup behind us on exit...
		try {
			// NOTE: This can be a bit racey with stop(),
			//	 so sleep for a tiny bit so our commandToRunOnExit actually has a chance to run...
			Thread.sleep(175);
			//cleanupTemporaryDirectory();
		} catch (Exception ignored) {
			// Avoid the framework shouting at us...
		}

		super.destroy();
	}
}
