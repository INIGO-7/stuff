package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlite {

	public void Sqlite() {
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sqliteDatabase.db");
				Statement stmt = (Statement) conn.createStatement()
				
				
			){
			
//			String instruccionBorrar = "DROP TABLE IF EXISTS " + "dataBase" + ";";
//			stmt.executeUpdate(instruccionBorrar);
//			
//			String instruccionCrear = "CREATE TABLE " + "nombreTabla"
//					+ "(NOMBRE VARCHAR(25), APELLIDO VARCHAR(50), DNI VARCHAR(10), CODIGO VARCHAR(5), HORAS VARCHAR(5));";
//			
//			stmt.executeUpdate(instruccionCrear);
//
//			String instruccion = "INSERT INTO " + "nombreTabla" + " VALUES('" + "value1" + "', '"
//					+ "value" + "' , '" + "value" + "' , '" + "value" + "' , '"
//					+ "value" + "');";
//
//			stmt.executeUpdate(instruccion);
//
//			String sql = String.format("INSERT INTO usuarios VALUES ('%s', '%s', %d)", nombre, apellido, edad);
//			stmt.executeUpdate(sql);
//			
//			conn.setAutoCommit(false);
//			conn.commit();
//			conn.rollback(); 
			
			} catch (SQLException e) {
			 System.out.println("No se ha podido cargar el driver de la base de datos, " + e);
			} 
			
	}
	
	public static void main(String[] args) {
		new Sqlite();
		System.out.println("Hello World!");
	}
	
	//preparedStatement
	
}
