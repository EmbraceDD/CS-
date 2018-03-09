package com.mec.request.server;

import com.mec.request.dao.ClassInfoDao;
import com.mec.request.model.ClassInfoModel;

public class ClassInfoServer {
   private ClassInfoDao dao;
   
   public ClassInfoServer() {
	   dao = new ClassInfoDao();
   }
   
   public ClassInfoModel getClassInfoById(String id){
	   return dao.getClassInfoById(id);
   }
   
}
