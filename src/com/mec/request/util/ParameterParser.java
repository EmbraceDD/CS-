package com.mec.request.util;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParameterParser {
	private JSONObject jsonObject;
	public ParameterParser() {
		jsonObject = new JSONObject();
	}
	
	public ParameterParser addOgnl(String key,Object object){
		if(object.getClass().isArray()|| object instanceof List){
			JSONArray array = JSONArray.fromObject(object);
			jsonObject.accumulate(key,array);
			return this;
		}else if(isBase(object.getClass())){
			jsonObject.accumulate(key,object);
		}else {
			JSONObject json = JSONObject.fromObject(object);
			jsonObject.accumulate(key,json);
		}
		return this;
	}
	
	 private boolean isBase(Class<?> klass){
		   return klass.equals(String.class)
				   ||klass.equals(Double.class)
				   ||klass.equals(Short.class)
				   ||klass.equals(Byte.class)
				   ||klass.equals(Float.class)
				   ||klass.equals(Boolean.class)
				   ||klass.equals(Long.class)
				   ||klass.equals(Character.class)
				   ||klass.equals(Integer.class)
				   ||klass.equals(Object.class);
	   }

	@Override
	public String toString() {
		return jsonObject.toString();
	}
	
	

}
