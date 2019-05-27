package Desposite_database;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 向数据库中添加数据
 * @author guoy10
 *
 */
public class test {
	public static void main(String[] args) throws IOException, JSONException{
		File file = new File("C:/Users/guoy10/eclipse-workspace/Sql/data/test.json");
		String text = FileUtils.readFileToString(file,"UTF-8");
		JSONObject jsonObject = new JSONObject(text);
		//JSONArray arr1 = jsonObject.getJSONArray(text);
		
		//System.out.println(arr1.length());
		//for(int i=0;i<arr1.length();i++) {
			//JSONObject info = new JSONObject(i);
			String name = jsonObject.getString("name");
			int ID_0 = jsonObject.getInt("ID_0");
			int ID_1 = jsonObject.getInt("ID_1");
			String ISO = jsonObject.getString("ISO");
			WriteIntoSql(name, ID_0, ID_1, ISO);
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
			/*String sql = "insert ignore into test_gys(name,ID_0,ID_1,ISO) values(?,?,?,?)";*/
			String sql = "insert into test_gys(name,ID_0,ID_1,ISO) values(?,?,?,?)";
			
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
}

