package com.mec.request.action;

import java.lang.reflect.Method;

public class ActionDefinition {
     private Object object;
     private Method method;
     
     public ActionDefinition() {
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
     
     
     
}
