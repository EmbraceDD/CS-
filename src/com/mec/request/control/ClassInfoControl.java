package com.mec.request.control;

import com.mec.request.annotation.ActionAnnotation;
import com.mec.request.annotation.ComponentAnnotation;
import com.mec.request.annotation.ParameterAnnotation;
import com.mec.request.model.ClassInfoModel;
import com.mec.request.server.ClassInfoServer;

@ComponentAnnotation
public class ClassInfoControl {
	private ClassInfoServer server;
	
	public ClassInfoControl() {
		server = new ClassInfoServer();
	}
	
	@ActionAnnotation(action="getClassInfoById")
	public ClassInfoModel getClassInfoById(@ParameterAnnotation(id="id")String id){
		return server.getClassInfoById(id);
	}

}
