package com.mec.request.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mec.request.model.StudentModel;
import com.mec.request.util.MecDataBaseConfig;

public class StudentDao {
	
	public StudentModel getStudentById(String id) {
		StudentModel student = new StudentModel();
		String sqlString ="SELECT id, name, passward FROM sys_student_info "
				+ "WHERE id ='"+id+"'";
		ResultSet resultSet = MecDataBaseConfig.execute(sqlString);
		
		try {
			if(resultSet.next()){
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				student.setId(id);
				student.setName(name);
				student.setSex(sex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

}
