package com.mec.request.action;

import java.util.HashMap;
import java.util.Map;

public class RequestActionFactory {
	 private static final Map<String, ActionDefinition> actionMap;
	
	static{
		 actionMap = new HashMap<>();
	 }
	
	 public static ActionDefinition getObj(String action){
		 return actionMap.get(action);
	 }
	 
	 public static void putObject(String action,ActionDefinition obj){
		 actionMap.put(action, obj);
	 }
}
