package com.mec.request.dao;

import com.mec.request.model.ClassInfoModel;

public class ClassInfoDao {
    public ClassInfoModel getClassInfoById(String id){
    	ClassInfoModel classInfoModel = new ClassInfoModel();
    	classInfoModel.setClassName("web��̨");
    	classInfoModel.setClassNumber("007");
    	classInfoModel.setId(id);
    	return classInfoModel;
    }
}
