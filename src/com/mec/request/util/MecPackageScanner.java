package com.mec.request.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyStore.Entry;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.mec.request.action.ActionDefinition;
import com.mec.request.action.RequestActionFactory;
import com.mec.request.annotation.ActionAnnotation;
import com.mec.request.annotation.ComponentAnnotation;

public class MecPackageScanner {
	public  static void scannerPackage(String packageName){
		String name = packageName.replace(".", "/");
		try {
			//代码中动态加载jar、资源文件的时候，首先应该是使用Thread.currentThread().getContextClassLoader()
			Enumeration<URL> urls=  MecPackageScanner.class.getClassLoader().getResources(name);
					//Thread.currentThread().getContextClassLoader().getResources(name);
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				//得到协议的名称
				String  protocol= url.getProtocol();
				//以文件的形式保存在服务器上的
				if(protocol.equals("file")){
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					 findFile(packageName,filePath);
				}else if(protocol.equals("jar")){
					JarFile jarfail;
					try {
						jarfail = ((JarURLConnection)url.openConnection()).getJarFile();
						Enumeration<JarEntry> entries = jarfail.entries();
						
						while(entries.hasMoreElements()){
							JarEntry entry = entries.nextElement();
							String enteyName = entry.getName();
							//System.out.println(enteyName);
							findJar(entry, enteyName, name) ;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
		     	}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MecPackageScanner.scannerPackage("org.apache.commons.beanutils");
	}
	
	public static void findFile(String packageName,String packagePath){
		File dir = new File(packagePath);
		if(!dir.exists() || !dir.isDirectory()){
			System.out.println("文件不存在或者不是目录");
			return  ;
		}
		
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				//自动过滤只留目录或者是.class文件
				return (file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		
		for(File file :files){
			if(file.isDirectory()){
				findFile(packageName + "." + file.getName() ,file.getAbsolutePath());
			  //递归继续查找
			}else{
				String className= file.getName();
				int index = className.indexOf(".class");
				className = packageName+"."+className.substring(0,index);
				try {
					//这个方法会触发static 方法
					Class<?> klass = Class.forName(className);
					//Class<?> klass = Thread.currentThread().getContextClassLoader().loadClass(className);
					dealClass(klass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void findJar(JarEntry entry,String enteyName, String packagePath) {
																						//  是斜杠类型的
				if(enteyName.charAt(0)=='/'){
					enteyName = enteyName.substring(1);
				}
				//如果前半部分和定义的包名相同
				if(enteyName.startsWith(packagePath)){
					//如果是以“/”结尾是一个包
						if(enteyName.endsWith(".class")&&!entry.isDirectory()){
							enteyName = enteyName.replace("/", ".");
							String packageName = packagePath.replace("/", ".");
							String className =enteyName.substring(0, enteyName.indexOf(".class"));
							try {
								Class<?> klass = Class.forName(className);
								dealClass(klass);
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
				}
	}

	private static void dealClass(Class<?> klass) {
			if(!klass.isAnnotationPresent(ComponentAnnotation.class)){
				return;
			}
			try {
				Object obj = klass.newInstance();
				Method [] methods = klass.getDeclaredMethods();
				for(Method method: methods){
					if(!method.isAnnotationPresent(ActionAnnotation.class)){
					 continue;
					}
					ActionAnnotation actionAnnotation = method.getAnnotation(ActionAnnotation.class);
					ActionDefinition actionDefinition = new ActionDefinition();
					actionDefinition.setMethod(method);
					actionDefinition.setObject(obj);
					String action = actionAnnotation.action();
					RequestActionFactory.putObject(action,actionDefinition);
				}
 			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	}

}
