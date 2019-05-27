package Desposite_database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.InputStreamReader;
import java.sql.*;



/**
 * 
 * @author guoy10
 *
 */
public class Success {
	public static void main(String[] args) {
		String JsonContext = ReadFile("C:\\Users\\guoy10\\eclipse-workspace\\Sql\\data\\test.json");
		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
		int size = jsonArray.size();
		System.out.println("Size: "+size);
		for(int i=0;i<size;i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String name = jsonObject.getString("name");
			int ID_0 = jsonObject.getInt("ID_0");
			int ID_1 = jsonObject.getInt("ID_1");
			String ISO = jsonObject.getString("ISO");
			WriteIntoSql(name, ID_0, ID_1, ISO);
		}
		System.out.println("数据存储完成");
	}
	
	public static void WriteIntoSql(String name,int ID_0,int ID_1,String ISO) {
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			System.out.println("加载数据库驱动成功");
			String url = "jdbc:mysql://10.62.54.100:3306/guo";
			String user	= "meng";
			String password = "123456";
			//建立数据库连接，获取连接对象
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("数据库连接成功");
			//创建一条sql语句
			//String sql0 = "create"
			String sql = "insert ignore into test_gys(name,ID_0,ID_1,ISO) values(?,?,?,?)";
			//String sql = "insert into test_gys(name,ID_0,ID_1,ISO) values(?,?,?,?)";
			
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, name);
			pStatement.setInt(2, ID_0);
			pStatement.setInt(3, ID_1);
			pStatement.setString(4, ISO);
			pStatement.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String ReadFile(String path) {
		BufferedReader reader = null;
		String lastStr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while((tempString = reader.readLine())!=null) {
				lastStr += tempString;
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lastStr;
	}
}
