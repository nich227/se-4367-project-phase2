package agent;

import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class JUnitListener extends RunListener {

	public void testRunStarted(Description description) throws Exception {

		if (CollectCoverage.testCases == null) {
			CollectCoverage.testCases = new HashMap<String, HashMap<String, IntLinkedOpenHashSet>>();
		}
	}

	public void testStarted(Description description) {
		CollectCoverage.testName = "[TEST]" + description.getClassName() + ":" + description.getMethodName();
		CollectCoverage.linesCovered = new HashMap<String, IntLinkedOpenHashSet>();
	}

	public void testFailure(Failure failure) throws Exception {
		System.err.println("FAILURE: " + failure);
	}

	public void testFinished(Description description) {
		CollectCoverage.testCases.put(CollectCoverage.testName, CollectCoverage.linesCovered);
	}

	public void testRunFinished(Result result) throws IOException {

		File fout = new File("stmt-cov.txt");
		StringBuilder str_builder = new StringBuilder();
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		
		for (String testCaseName : CollectCoverage.testCases.keySet()) {
			//System.out.print("Test case: " + testCaseName);
			str_builder.append(testCaseName + "\n");

			HashMap<String, IntLinkedOpenHashSet> caseCoverage = CollectCoverage.testCases.get(testCaseName);
			//System.out.println(Arrays.asList(caseCoverage));

			for (String cName : caseCoverage.keySet()) {
				int[] lines = caseCoverage.get(cName).toIntArray();
				Arrays.sort(lines);
				//System.out.println(Arrays.toString(lines));
				for (int line : lines) {
					str_builder.append(cName + ":" + line + "\n");
				}
			}
		}

		bw.write(str_builder.toString());
		bw.close();
	}

}