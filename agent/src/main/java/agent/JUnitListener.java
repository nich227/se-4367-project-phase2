package agent;

import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class JUnitListener extends RunListener {

	public void testRunStarted(Description description) throws Exception {

		if (CollectCoverage.testCases == null) {
			CollectCoverage.testCases = new HashMap<>();
		}
		
		if(CollectCoverage.testVars == null) {
			CollectCoverage.testVars = new HashMap<>();
		}
	}

	public void testStarted(Description description) {
		System.out.println("\n-----\nStarting - " + description.getMethodName());
		CollectCoverage.testName = "[TEST]" + description.getClassName() + ":" + description.getMethodName();
		CollectCoverage.linesCovered = new HashMap<>();
		CollectCoverage.visitedVars = new HashSet<>();
		CollectCoverage.variableMap = new HashMap<>();
	}

	public void testFailure(Failure failure) throws Exception {
		System.err.println("FAILURE: " + failure);
	}

	public void testFinished(Description description) {
		System.out.println("Finished - " + description.getMethodName());
		CollectCoverage.testCases.put(CollectCoverage.testName, CollectCoverage.linesCovered);
		CollectCoverage.testVars.put(CollectCoverage.testName, CollectCoverage.variableMap);
	}

	public void testRunFinished(Result result) throws IOException {
		System.out.println("Test done");

		File fout = new File("stmt-cov.txt");
		StringBuilder str_builder = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		str_builder.append("Line coverage:\n");
		for (String testCaseName : CollectCoverage.testCases.keySet()) {
			str_builder.append(testCaseName + "\n");

			HashMap<String, IntLinkedOpenHashSet> caseCoverage = CollectCoverage.testCases.get(testCaseName);

			for (String cName : caseCoverage.keySet()) {
				int[] lines = caseCoverage.get(cName).toIntArray();
				Arrays.sort(lines);
				for (int line : lines) {
					str_builder.append(cName + ":" + line + "\n");
				}
			}
		}

		File fout2 = new File("Invariant-Trace.txt");
		StringBuilder str_buildernew = new StringBuilder();
		FileOutputStream fosnew = new FileOutputStream(fout2);
		BufferedWriter bwnew = new BufferedWriter(new OutputStreamWriter(fosnew));
		
		str_buildernew.append("Possible invariants:\n");
		for (String testCaseName : CollectCoverage.testVars.keySet()) {
			str_buildernew.append(testCaseName + "\n");

			for (Integer index : CollectCoverage.testVars.get(testCaseName).keySet()) {
				str_buildernew.append(index + ":" + CollectCoverage.testVars.get(testCaseName).get(index) + "\n");
			}
		}

		bw.write(str_builder.toString());
		bw.close();
		bwnew.write(str_buildernew.toString());
		bwnew.close();
	}

}