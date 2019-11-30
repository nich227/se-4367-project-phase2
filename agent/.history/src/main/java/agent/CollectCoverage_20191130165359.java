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

	public static void addVariable(Object obj){
		System.out.println(obj.getClass());
	}

}
