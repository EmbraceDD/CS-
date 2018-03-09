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
//				   if(pt.getName().equals("java.util.List")){//参数类型List  里边类型是泛型
//					      Type type = parameter.getParameterizedType();  
//					      //该方法是得到list被擦除前的真正的类型java.util.List<com.mec.request.model.StudentModel>
//					      System.out.println(type);
//					      if(type instanceof ParameterizedType){
//					    	  ParameterizedType parat = (ParameterizedType) type;
//					    	  System.out.println("parat:" + parat);
//					    	  //parat.getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组
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
//		 *泛型的擦除
//		 *在泛型代码内部，无法获得任何有关泛型参数类型的信息
//		 *参数化类型就是一个编译器可以自动定制作用于特定类型上的类
//		 */
////		Class klass1 = new ArrayList<String>().getClass();
////		Class klass2 = new ArrayList<Integer>().getClass();
////		System.out.println(klass1  + ":"+klass2 );  //都是class java.util.ArrayList  在编译过程中，所定义的类型被擦除，都成为他的原始类型
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
//				   if(pt.getName().equals("java.util.List")){//参数类型List  里边类型是泛型
//					      Type type = parameter.getParameterizedType();  
//					      //该方法是得到list被擦除前的真正的类型java.util.List<com.mec.request.model.StudentModel>
//					      System.out.println(type);
//					      if(type instanceof ParameterizedType){
//					    	  ParameterizedType parat = (ParameterizedType) type;
//					    	  System.out.println("parat:" + parat);
//					    	  //parat.getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组
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
