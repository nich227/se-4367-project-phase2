package agent;

import java.util.HashMap;
import java.util.Set;

import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;

public class CollectCoverage {

	public static HashMap<String, IntLinkedOpenHashSet> linesCovered;
	public static HashMap<String, HashMap<String, IntLinkedOpenHashSet>> testCases;
	public static String testName;

	public static HashMap<Integer, Object> variableMap;
	public static Set<Integer> visitedVars;
	public static HashMap<String, HashMap<Integer, Object>> testVars;
	public static HashMap<Integer, String> variableName;
	public static HashMap<String, HashMap<Integer, String>> testFinal;

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



	public static void addVariable(Integer val, Integer var) {

		// If the variable map hasn't been initialized or this variable has already been
		// visited
		if (variableMap == null || visitedVars == null || visitedVars.contains(var))
			return;
		visitedVars.add(var);

		// If the variable map has no values for the specified var index
		if (variableMap.get(var) == null) {
			variableMap.put(var, val);
		}
	}

	public static void addVariable(Double val, Integer var) {
		// If the variable map hasn't been initialized or this variable has already been
		// visited
		if (variableMap == null || visitedVars == null || visitedVars.contains(var))
			return;
		visitedVars.add(var);

		// If the variable map has no values for the specified var index
		if (variableMap.get(var) == null) {
			variableMap.put(var, val);
		}
	}

	public static void addVariable(Float val, Integer var) {
		// If the variable map hasn't been initialized or this variable has already been
		// visited
		if (variableMap == null || visitedVars == null || visitedVars.contains(var))
			return;
		visitedVars.add(var);

		// If the variable map has no values for the specified var index
		if (variableMap.get(var) == null) {
			variableMap.put(var, val);
		}
	}

	public static void addVariable(Long val, Integer var) {
		// If the variable map hasn't been initialized or this variable has already been
		// visited
		if (variableMap == null || visitedVars == null || visitedVars.contains(var))
			return;
		visitedVars.add(var);

		// If the variable map has no values for the specified var index
		if (variableMap.get(var) == null) {
			variableMap.put(var, val);
		}
	}

	public static void addVariable(Object val, Integer var) {
		// If the variable map hasn't been initialized or this variable has already been
		// visited

		if (variableMap == null || visitedVars == null || visitedVars.contains(var))
			return;
		visitedVars.add(var);

		// If the variable map has no values for the specified var index
		if (variableMap.get(var) == null) {
			variableMap.put(var, val);
		}
	}

	public static void addName(String name, String desc,  Integer index){
		if (variableName == null || visitedVars == null || visitedVars.contains(index))
			return;

		String var = name+" : "+desc;
		if (variableName.get(index) == null) {
			variableName.put(index, var);
		}
		//System.out.println("Variable Name is: " +name + " Index value is: "+ index + "description: "+ desc );
	}
}