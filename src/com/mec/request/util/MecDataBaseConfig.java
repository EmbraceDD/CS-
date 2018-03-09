package com.mec.request.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mec.cs.util.CSUtile;
import com.mysql.jdbc.Driver;

public class MecDataBaseConfig {
	private static final String database_url;
	private static final String database_user;
	private static final String database_password;
	private static Connection connection;
	//jdbc���ӵĹ���Ϊ�����������������ӡ�Ԥִ��SQL��䡢ִ��SQL��䷵�ؽ�������ر�����
	static{
		database_url = CSUtile.getValue("database_url");
		database_user = CSUtile.getValue("database_user");
		database_password = CSUtile.getValue("database_password");
		
		try {
			new Driver();
			connection = DriverManager.getConnection(database_url, database_user,database_password);
			System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet  execute(String sqlString){
		ResultSet resultSet = null;
		PreparedStatement preparedStatement =null;
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			resultSet = preparedStatement.executeQuery();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 return resultSet;
	}
	
	public static void closeDataBase(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void fun(){
		
	}

}
