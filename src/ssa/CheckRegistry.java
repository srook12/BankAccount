package ssa;

import java.util.ArrayList;
import java.util.List;

public class CheckRegistry {
	List<Check> registry = new ArrayList<Check>();
	
	public void addCheck(Check check) {
		registry.add(check);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for(Check check : registry) {
			// Automatically calls toString()
			sb.append(check);
		}
		
		return sb.toString();
	}
}
