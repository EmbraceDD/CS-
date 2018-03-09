package com.mec.request.util;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.type.TypeVariable;

import com.mec.request.annotation.ParameterAnnotation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParameterIoc {
//"{\"id\":\"00003\"}";
//			"{\"studentModel\":{\"id\":\"02\",\"name\":\"lixhiyuan\",\"sex\":\"nv\"}}";
//			"{\"studentModel\":[{\"id\":\"02\",\"name\":\"lixhiyuan\",\"sex\":\"nv\"},{\"id\":\"ooo\",\"name\":\"haha\",\"sex\":\"nv\"}]}";
//			"{\"studentModel\":"
//			+ "[{\"id\":\"02\",\"model\":{\"id\":\"02\",\"name\":\"lixhiyuan\",\"sex\":\"nv\"}},"
//			+ "{\"id\":\"03\",\"model\":{\"id\":\"03\",\"name\":\"lixhiyuan\",\"sex\":\"nv\"}},"
//			+ "{\"id\":\"04\",\"model\":{\"id\":\"04\",\"name\":\"lixhiyuan\",\"sex\":\"nv\"}}]}";
//		 "{\"studentModel\":[\"5\",\"8\"]}";
	
	public ParameterIoc() {
		
	}
	
	public Object[] dealmethod(Method method,JSONObject json){
		List<Object> paraList = new ArrayList<>();
		Parameter [] parameters = method.getParameters();
			for(Parameter parameter :parameters) {
				if(!parameter.isAnnotationPresent(ParameterAnnotation.class)){
					System.out.println(method+"方法参数无注解无法执行");
					return null;
				}
				ParameterAnnotation annotation = parameter.getAnnotation(ParameterAnnotation.class);
				String id = annotation.id();
				Object paraObj = json.get(id);		
				Class<?> tp = parameter.getType();
				Type type = parameter.getParameterizedType();
				paraList.add(dealType(tp,type,paraObj));
			}
			return paraList.toArray();
	}
	
    public <T> Object dealType(Class<?> klass,Type type,Object object){
    	if(type instanceof ParameterizedType){
    		if(klass==Map.class){
        		Map<T, T>  map = dealMap(type , object);
        		return map;
    		}else if(klass == List.class){
    			List<T> list =dealList(klass,type , object);
    			return list;
    		}
    	}else if(type instanceof GenericArrayType){
    		System.out.println(type+"该类型是泛型数组变量");
		}else if(type instanceof TypeVariable){
			System.out.println(type+"该类型是类型变量");
		}else if(type instanceof WildcardType){
			System.out.println(type+"该类型是通配符类型！");
		}else if(type instanceof Class){
			 if(((Class<?>) type).isPrimitive() || isBase(((Class<?>) type))){
				return object;
			 }else if(((Class<?>) type).isArray()){
				 object= dealArray(type,object);
				 return object;
			 }else if(type.getTypeName().equals("java.lang.Object")){
				  return object;
	    	}else {
	    		 object = setField(klass, object);
				 return object;
	    	}
		}
		return object;
    }
    @SuppressWarnings("unchecked")
	public <T> T[] dealArray(Type type,Object obj) {
		 Class<?>  klass = ((Class<?>) type).getComponentType(); //数组内部的元素类型
		 List<T> list = new ArrayList<>();
		List<T> jsonList = (List<T>) obj;
		 for(int i =0; i< jsonList.size();i++) {
			list.add((T) dealType(klass,(Type)klass, jsonList.get(i)));
		 }
		 return (T[]) list.toArray();
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

	
	public Object setField(Class<?> klass, Object object) {
				Object obj = null;
				try {
					obj = klass.newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
				Field [] fields = klass.getDeclaredFields();
				JSONObject json = JSONObject.fromObject(object);
				for(Field field : fields){
					String name = field.getName();
					if(!json.containsKey(name)){
						continue;
					}
					Object value = json.get(name);
					try {
						field.setAccessible(true);
						field.set(obj, value);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				return obj;
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> dealList(Class<?> klass, Type type ,  Object object){
		Type[] types = ((ParameterizedType) type).getActualTypeArguments();
		List<T> list = new ArrayList<>();
		List<T> jsonList = (List<T>) object;
		for(int i=0; i< jsonList.size(); i++) {
			Object  one = dealType((Class<?>)types[0],types[0],jsonList.get(i));
	         list.add((T) one);
		}
		
	    return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public <T>Map<T,T> dealMap(Type type ,  Object object) {
		Type[] types = ((ParameterizedType) type).getActualTypeArguments();
		Map<T,T> map = new HashMap<>();
		JSONObject jsonObj = JSONObject.fromObject(object);
	        Iterator<String> keys =  jsonObj.keys();
	        while (keys.hasNext())
	        {
	         String key = keys.next();
	         Object  one = dealType((Class<?>)types[0],types[0],key);
	         Object  two = dealType((Class<?>)types[1],types[1],jsonObj.get(key));
    	      map.put((T)one,(T) two);
	        }
	    return map;
	}
}
