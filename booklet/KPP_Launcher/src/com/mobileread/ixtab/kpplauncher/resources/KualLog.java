package com.mobileread.ixtab.kpplauncher.resources;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class KualLog {

	private final String logfilePath = "/var/tmp/KPPL.log";

	// constructor

	// methods
	public void append(String line) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(logfilePath, true)); //append
			bw.write("KPPL: " + line);
			bw.newLine();
			bw.close();
		} catch (IOException t) {
			//
		}
	}

	public void printStackTrace(Throwable t) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(logfilePath, true), true); //append, flush each println
			t.printStackTrace(pw);
			pw.flush();
			pw.close();
		} catch (IOException ex) {
			//
		}
	}
}
