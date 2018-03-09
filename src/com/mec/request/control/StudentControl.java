package com.mec.request.control;

import java.util.List;
import java.util.Map;

import com.mec.request.annotation.ActionAnnotation;
import com.mec.request.annotation.ComponentAnnotation;
import com.mec.request.annotation.ParameterAnnotation;
import com.mec.request.model.StudentModel;
import com.mec.request.server.StudentServer;

@ComponentAnnotation
public class StudentControl {
	
   private StudentServer server;
   
   public StudentControl() {
	   server = new StudentServer();
   }
   
   @ActionAnnotation(action="getStudentById")
   public StudentModel getStudentById(@ParameterAnnotation(id="id")String id,
		   													@ParameterAnnotation(id="name")String name){
	   System.out.println("id"+id);
	   System.out.println("name"+name);
	   return  this.server.getStudentById(id);
   }
   
   @ActionAnnotation(action="getClassInfo")
   public StudentModel getClassInfoById(@ParameterAnnotation(id="id")String id){
	   return  this.server.getStudentById(id);
   }
   
   
   @ActionAnnotation(action="setStudent")
   public StudentModel setStudent(@ParameterAnnotation(id="studentModel")StudentModel studentModel){
	   return studentModel;
   }
   
   @ActionAnnotation(action="setStudentAndId")
   public StudentModel  getid(@ParameterAnnotation(id="studentModel")StudentModel studentModel,
		   												@ParameterAnnotation(id="id")String id){
	   System.out.println("id:" + id);
	   return studentModel;
   }
   
   @ActionAnnotation(action="setStudentList")
   public List<StudentModel> setStudentList(
		   @ParameterAnnotation(id="studentModelList")List<StudentModel> studentModel){
	   return studentModel;
   }
   
   @ActionAnnotation(action="setStudentListAndId")
   public List<StudentModel> setStudentListAndId(
		   @ParameterAnnotation(id="studentModelList")List<StudentModel> studentModel,
		   @ParameterAnnotation(id ="id") String id){
	   System.out.println("id:" + id);
	   return studentModel;
   }
   
   @ActionAnnotation(action="setStudentMap")
   public Map<String, StudentModel> setStudentMap(
		   @ParameterAnnotation(id="studentModelMap")Map<String, StudentModel> studentModel){
	   return studentModel;
   }
   
   @ActionAnnotation(action="setStudentMapAndId")
   public Map<String, StudentModel> setStudentMapAndId(
		   @ParameterAnnotation(id="studentModelMap")Map<String, StudentModel> studentModel,
		   @ParameterAnnotation(id ="id") String id){
	   System.out.println("id:"+id);
	   return studentModel;
   }
//   public void  getStudentById(@ParameterAnnotation (id= "id")String id ) {
//	   System.out.println("执行成功！'");
//   }
//		 public void  getStudentById(StudentModel model) {
//		   System.out.println("执行成功！'");
//		}
//   
//   public void getAll(List<StudentModel> models){
//	   System.out.println("执行成功！'");
//   }
//   public void getAll(ArrayList<Integer> models){
//	   System.out.println("执行成功！'");
//   }
//   
//   public void getAll(List<?> models){
//	   System.out.println("执行成功！'");
//   }
//   
//   public void get(Map<String, StudentModel> map){
//	   System.out.println("执行成功！'");
//   }
//   
//   public void getlll(ArrayList [] arr){
//	   System.out.println("执行成功！'");
//   }
//   
//   public void getlll(LinkedList[][] arr){
//	   System.out.println("执行成功！'");
//   }
//   
   public void getlllkk(Object[] arr){
	   System.out.println("执行成功！'");
   }
//   public <E> void getlll(E [] arr){
//	   System.out.println("执行成功！'");
//   }
//   
//   public  <E> void getlllS(E arr){
//	   System.out.println("执行成功！'");
//   }
//    
    
}
