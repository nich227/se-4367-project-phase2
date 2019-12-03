package agent;

import java.util.HashMap;
import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;

public class CollectCoverage {

	public static HashMap<String, IntLinkedOpenHashSet> linesCovered;
	public static HashMap<String, HashMap<String, IntLinkedOpenHashSet>> testCases;
	public static String testName;

	// Accessors

	public static void addCoveredLine(String name, Integer line) {

		// The lines covered is empty
		if (linesCovered == null)
			return;

		IntLinkedOpenHashSet covered_lines = linesCovered.get(name);
		// If linesCovered has no values for the specified class name
		if (covered_lines == null) {
			int[] new_set = { line };
			linesCovered.put(name, new IntLinkedOpenHashSet(new_set));
		}

		else {
			// If adding lines to existing linesCovered
			covered_lines.add(line);
		}
	}
	
	public static void addVariable(Integer var) {
		System.out.println("Add variable int: " + var);
	}
	
	public static void addVariable(Double var) {
		System.out.println("Add variable double: " + var);
	}
	
	public static void addVariable(Float var) {
		System.out.println("Add variable float: " + var);
	}
	
	public static void addVariable(Long var) {
		System.out.println("Add variable long: " + var);
	}

	public static void addVariable(Object var) {
		System.out.println("Add variable obj: " + var.toString());
	}

}