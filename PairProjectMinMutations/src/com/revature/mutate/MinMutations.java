package com.revature.mutate;

import java.util.ArrayList;
import java.util.Arrays;

public class MinMutations {
	
	public MinMutations() {
		super();
	}
	public static int calculateMutations(String start, String end, String[] bank) {
		// Gene types "A", "C", "G", "T".
		if(start.equals(end)) {
			return 0;
		} else if (!Arrays.asList(bank).contains(end)) {
			return -1;
		} else {
			//Not an edge case
			ArrayList<String> viableMoves= new ArrayList<String>();
			viableMoves.add(start);
			int stepsFromStart=0;
			while(!viableMoves.contains(end)) {
				//iterate over the bank to see if there is a way to get from current step to step from bank
				String currentState = viableMoves.get(stepsFromStart);
				String[] charsOfCurrentState = currentState.split("");
				for (String pattern : bank) {
					//check each pattern in bank
					String[] patternCheck = pattern.split("");
					int diffs = 0;
					for (int i=0; i<patternCheck.length;i++) {
						if(!charsOfCurrentState[i].equals(patternCheck[i])) {
							diffs += 1;
						}
					}
					if(diffs==1) {
						//if the move is only one away from the current state, try to add to list
						if(!viableMoves.contains(pattern)) {
							viableMoves.add(pattern);
						}
					}
				}
				stepsFromStart +=1;
				if((viableMoves.size()-1)<stepsFromStart) {
					return -1;
				}
			}
			return stepsFromStart;
			
			
		}
	}
}
