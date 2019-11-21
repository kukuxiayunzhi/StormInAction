package main.java.chapter05.storm;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Test {


	public PreparedStatement prepStatement;
	private Connection connection;
	private String url = "jdbc:mysql://192.168.11.244:3306/check_account";
	private String password = "root";
	private String driver = "com.mysql.jdbc.Driver";
	private String username = "root";
	private PreparedStatement st = null;


	

	public void execute() {

		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		try {
			
			String sql = "insert into abnormal(uid,date,times,hour,tag) values(?,?,?,?,?)";
			st = connection.prepareStatement(sql);

			st.setString(1, "123456874");
			st.setString(2, "2016-03-16");
			st.setInt(3, 23);
			st.setInt(4, 12);

			st.setString(5, "test1");
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	


	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		new Test().execute();
		
		//Socket socket = new Socket("192.168.11.133",5140);
		
		//socket.getOutputStream();

	}

}
