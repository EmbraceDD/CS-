package com.mec.cs.server.test;
import com.mec.request.util.MecPackageScanner;

public class ServerTest {

	public static void main(String[] args) {
		new ServerView();
		MecPackageScanner.scannerPackage("com.mec.request.control");
	}

}
