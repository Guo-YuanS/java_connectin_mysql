package Desposite_database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 向数据库中添加数据
 * @author guoy10
 *
 */
public class ReadJson {
	public static void main(String[] args){
		File file = new File("C:/Users/guoy10/eclipse-workspace/Sql/data/test.json");
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");//读取文件
			BufferedReader brname = new BufferedReader(read);
			String rjson = null;
			
			while((rjson=brname.readLine())!=null) {
				System.out.println(rjson);
				try {
					JSONObject jsonData = new JSONObject(rjson);
					JSONArray arr1 = jsonData.getJSONArray(rjson);//获取json数组
					System.out.println(arr1.length());
					for(int i=0;i<arr1.length();i++) {
					JSONObject info = arr1.getJSONObject(i);//获取arr1数组的第i个对象；
						//JSONObject properties/*对象*/ = info.getJSONObject("properties");
						String name = info.getString("name");
						int ID_0 = info.getInt("ID_0");
						int ID_1 = info.getInt("ID_1");
						String ISO = info.getString("ISO");
						WriteIntoSql(name, ID_0, ID_1, ISO);
					}
				}catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}
	public static void WriteIntoSql(String name,int ID_0,int ID_1,String ISO) {
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			System.out.println("加载数据库驱动成功");
			String url = "jdbc:mysql://10.207.86.22/guo";
			String user	= "meng";
			String password = "123456";
			//建立数据库连接，获取连接对象
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("数据库连接成功");
			//创建一条sql语句
			//String sql0 = "create"
			String sql = "insert into featurecollection(name,ID_0,ID_1,ISO) values(?,?,?,?)";
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, name);
			pStatement.setInt(2, ID_0);
			pStatement.setInt(3, ID_1);
			pStatement.setString(4, ISO);
			pStatement.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
