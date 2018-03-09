package com.mec.request.test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mec.request.util.MecDataBaseConfig;
import com.mec.request.util.ParameterParser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		JSONObject jsonObject = JSONObject.fromObject("{result:'ok'}");
		System.out.println(jsonObject);
	}

//	public static void main(String[] args) {
//        StudentModel model = new StudentModel();
//        model.setId("003000");
//        model.setName("ldd");
//        ParameterParser parameterParser = new ParameterParser();
//          List<StudentModel> modelList = new ArrayList<>();
//          modelList.add(model);
//          modelList.add(model);
//          modelList.add(model);
//	        StudentModel[] objectArray = new StudentModel[3];
//	        objectArray[0] = model;
//	        objectArray[1] = model;
//	        objectArray[2] = model;
//            Map<String, StudentModel> studentMap = new HashMap<>();
//	        studentMap.put("0", model);
//	        studentMap.put("1", model);
//	        studentMap.put("2", model);
//	        MecDataBaseConfig.fun();
//        //parameterParser.addOgnl("objectArray", objectArray);
//	     //   System.out.println(parameterParser.addOgnl("studentList", modelList).toString());
//	     //  System.out.println(parameterParser.addOgnl("id", 1.3));
//           // System.out.println( parameterParser.addOgnl("studentModel", model).toString());
////          System.out.println(parameterParser.addOgnl("studentMap", studentMap).addOgnl("studentModel", model)
////        		  .addOgnl("studentList", modelList));
//	        //System.out.println(parameterParser.addOgnl("objectArray", objectArray).toString());
//	        
//        //String pararmeter = parameterParser.addOgnl("id", model.getId()).addOgnl("name", model.getName()).toString();
//        //System.out.println(pararmeter);
//	}

}
