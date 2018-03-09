package com.mec.request.model;

public class ClassInfoModel {
	private String id;
	private String className;
	private String classNumber;
	
	public ClassInfoModel() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	@Override
	public String toString() {
		return "id=" + id + ", className=" + className + ", classNumber=" + classNumber;
	}
	
	
	

}
