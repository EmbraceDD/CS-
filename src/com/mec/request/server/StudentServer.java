package com.mec.request.server;

import com.mec.request.dao.StudentDao;
import com.mec.request.model.StudentModel;

public class StudentServer {
   private StudentDao dao;
   
   public StudentServer() {
	   dao = new StudentDao();
   }
   
   public StudentModel getStudentById(String id){
	   return dao.getStudentById(id);
   }
}
