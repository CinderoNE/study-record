package com.cinder.question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static String url="jdbc:mysql://localhost:3306/flight?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";

	public static String user="root";

	public static String password="1220";

	// public static String driver="com.mysql.cj.jdbc.Driver";

	public static Connection getCon() {
		Connection conn=null;
		try {
			// Class.forName(driver);  通过spi机制加载了驱动
			conn=DriverManager.getConnection(url, user, password);
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
