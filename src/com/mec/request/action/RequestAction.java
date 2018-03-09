package com.mec.request.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mec.cs.common.ConversationMessage;
import com.mec.request.util.ParameterIoc;
import com.mec.request.util.ParameterParser;

import net.sf.json.JSONObject;

public class RequestAction implements IActionInterfaces{
	public RequestAction() {
	}

	@Override
	public String executeAction(String action, String paramater) {
		Object result = null;
		ActionDefinition actionDefinition = RequestActionFactory.getObj(action);
		Object object = actionDefinition.getObject();
		Method method = actionDefinition.getMethod();
		ParameterIoc io = new ParameterIoc();
		Object [] parameterArray = io.dealmethod(method, JSONObject.fromObject(paramater));
		try {
			result = method.invoke(object, parameterArray);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		ParameterParser parameterParser = new ParameterParser();
		return result==null?null:parameterParser.addOgnl("result", result).toString();
	}
}
