package com.mec.request.test;

import com.mec.cs.common.ConversationMessage;

import net.sf.json.JSONObject;

public class TestT {

	public static void main(String[] args) {
//		StudentControl control = new StudentControl();
//		Class <?> klass = control.getClass();
//	   StudentModel.class.isPrimitive();
//		
//		Method [] methods = klass.getDeclaredMethods();
//		for(Method method : methods){
//			Parameter [] parameters = method.getParameters();
//			for(Parameter parameter : parameters){
//				String name = parameter.getName();
//				Class<?> pt = parameter.getType();   
//				if(pt.isPrimitive()){
//					//dealBaseData(pt, value);
//				}
//				//arg0
//				   System.out.println("parameter.getName():" +name + "type:" + pt);
//				   
//				   if(pt.getName().equals("java.util.List")){//��������List  ��������Ƿ���
//					      Type type = parameter.getParameterizedType();  
//					      //�÷����ǵõ�list������ǰ������������java.util.List<com.mec.request.model.StudentModel>
//					      System.out.println(type);
//					      if(type instanceof ParameterizedType){
//					    	  ParameterizedType parat = (ParameterizedType) type;
//					    	  System.out.println("parat:" + parat);
//					    	  //parat.getActualTypeArguments()���ر�ʾ������ʵ�����Ͳ����� Type ���������
//					    	  Class<?> pataKlass = (Class<?>) parat.getActualTypeArguments()[0];
//					    	  //com.mec.request.model.StudentModel
//					    	  System.out.println(pataKlass.getName());
//					    	  //class com.mec.request.model.StudentModel
//					    	  System.out.println(parat.getActualTypeArguments()[0]);
//					      }
//				   }else if (pt.getName().equals("int")){
//					   //parseInt() ;
//				   }
//			
//		}
//		}
//	}
////        System.out.println(int.class.isPrimitive());
////	}
//		/*
//		 *���͵Ĳ���
//		 *�ڷ��ʹ����ڲ����޷�����κ��йط��Ͳ������͵���Ϣ
//		 *���������;���һ�������������Զ������������ض������ϵ���
//		 */
////		Class klass1 = new ArrayList<String>().getClass();
////		Class klass2 = new ArrayList<Integer>().getClass();
////		System.out.println(klass1  + ":"+klass2 );  //����class java.util.ArrayList  �ڱ�������У�����������ͱ�����������Ϊ����ԭʼ����
////		System.out.println(klass1 == klass2);  //true
////		System.out.println(klass1.getTypeName());
//	/*
//		StudentControl control = new StudentControl();
//		Class <?> klass = control.getClass();
//		
//		Method [] methods = klass.getDeclaredMethods();
//		for(Method method : methods){
//			Parameter [] parameters = method.getParameters();
//			for(Parameter parameter : parameters){
//				String name = parameter.getName();
//				Class<?> pt = parameter.getType();                //arg0
//				   System.out.println("parameter.getName():" +name + "type:" + pt);
//				   
//				   if(pt.getName().equals("java.util.List")){//��������List  ��������Ƿ���
//					      Type type = parameter.getParameterizedType();  
//					      //�÷����ǵõ�list������ǰ������������java.util.List<com.mec.request.model.StudentModel>
//					      System.out.println(type);
//					      if(type instanceof ParameterizedType){
//					    	  ParameterizedType parat = (ParameterizedType) type;
//					    	  System.out.println("parat:" + parat);
//					    	  //parat.getActualTypeArguments()���ر�ʾ������ʵ�����Ͳ����� Type ���������
//					    	  Class<?> pataKlass = (Class<?>) parat.getActualTypeArguments()[0];
//					    	  //com.mec.request.model.StudentModel
//					    	  System.out.println(pataKlass.getName());
//					    	  //class com.mec.request.model.StudentModel
//					    	  System.out.println(parat.getActualTypeArguments()[0]);
//					      }
//				   }else if (pt.getName().equals("int")){
//					   //parseInt() ;
//				   }
//			}
////		   try {
////			Class <?>klasses =  Class.class.forName("java.lang.Integer");
////			Object obj = klasses.newInstance();
////			System.out.println( klasses.getName() +"vvvvv" + obj);
////		} catch (ClassNotFoundException e) {
////			e.printStackTrace();
////		} catch (InstantiationException e) {
////			e.printStackTrace();
////		} catch (IllegalAccessException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//			
//				}
//		}
//	*/
//	public Object  dealBaseData(Class<?> klass1, String value) {
//		Object obj = null;
//		if(klass1 == int.class){
//			obj = Integer.valueOf(value);
//		}else if(klass1 == short.class){
//			obj = Short.valueOf(value);
//		}else if(klass1== double.class){
//			obj = Double.valueOf(value);
//		}else if (klass1 == long.class){
//			obj = Long.valueOf(value);
//		}else if(klass1 == char.class){
//			obj = value.charAt(0);
//		}else if(klass1 == byte.class){
//			obj = Byte.valueOf(value);
//		}else if(klass1 == float.class){
//			obj = Float.valueOf(value);
//		}else if(klass1 == boolean.class){
//			obj= Boolean.valueOf(value);
//		}
//		return obj;
    }
	
}
