package com.mec.cs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CSUtile {
      private static final Map<String, String> configMap;
      public static final String DEBUG = "DEBUG";
      public static final String ALLOW_SAME_ID = "ALLOW_SAME_ID";
      static{
    	  configMap = new HashMap<>();
    	  InputStream is = CSUtile.class.getResourceAsStream("../../../../net_config.properties");
    	  Properties properties = new Properties();
    	  try {
			properties.load(is);
			
		   @SuppressWarnings("unchecked")
		Enumeration<String> names = (Enumeration<String>) properties.propertyNames();
		   
		   while(names.hasMoreElements()){
			   String key = names.nextElement();
			   String value = properties.getProperty(key);
			   configMap.put(key, value);
		   }
		} catch (IOException e) {
			e.printStackTrace();
		}
    			  
      }
      
      public static void fun() {
    	  
      }
      public static String getValue(String key){
    	  return configMap.get(key);
      }
}
