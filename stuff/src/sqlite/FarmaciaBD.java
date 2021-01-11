package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FarmaciaBD {

	private ResultSet rs;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement insertSql;
	
	//datos para la bd
	
	//medicamento
	private String nombreM, laboratorio;
	private int idM;
	private float precio;
	
	//paciente
	
	private String nombreP, dir, ciudad, fechaNCTO;
	private int nss;
	
	//receta
	
	private String fecha, medico;
	private int cant, dosis;
	
	public FarmaciaBD() {
		
		//medicamento
		nombreM = "frenadol"; 
		laboratorio = "cinfa";
		idM = 12;
		precio = 123.99f;
		
		//paciente
		
		nombreP = "Federico"; 
		dir = "Sabino Arana 59"; 
		ciudad = "Bilbao"; 
		fechaNCTO = "1-02-1997";
		nss = 8736491;
		
		//receta
		
		fecha = "2-10-2020"; 
		medico = "Carlos Cabeza";
		cant = 34;
		dosis = 2;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void conectarConBD() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:farmacia.db");
			stmt = conn.createStatement();
			
			System.out.println("Conexión a la base de datos satisfactoria.");
		} catch (SQLException e) {
			System.out.println("Conexión a la base de datos fallida. Error: " + e);
		}
		
	}
	
	public void crearBD() {
		try {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MEDICAMENTO( ID_M INT(2), NOMBRE_M VARCHAR(15), PRECIO DEC(4, 2) CHECK(PRECIO>0), LABORATORIO VARCHAR(15), PRIMARY KEY(ID_M) );");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS PACIENTE( NSS INT(9), NOMBRE VARCHAR(15), FECHA_NACTO DATE, DIR VARCHAR(15), CIUDAD VARCHAR(15), PRIMARY KEY(NSS) );");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RECETA( NSS INT(9), ID_M INT(2), FECHA DATE, CANT INT(2) DEFAULT 1, MEDICO VARCHAR(15),"
					+ "DOSIS INT(2), PRIMARY KEY(NSS, ID_M, FECHA), FOREIGN KEY(NSS) REFERENCES PACIENTE(NSS), FOREIGN KEY(ID_M) REFERENCES MEDICAMENTO(ID_M) ON DELETE CASCADE );");

			System.out.println("Tablas para la base de datos creadas.");
		} catch (SQLException e) {
			System.out.println("No se ha podido conectar a la base de datos. Error: " + e);
		}
	}
	
	public void poblarTablas() {
		try {
			insertSql = conn.prepareStatement("INSERT INTO MEDICAMENTO VALUES (?, ?, ?, ?)");
			
			insertSql.setInt(1, idM);
			insertSql.setString(2, nombreM);
			insertSql.setFloat(3, precio);
			insertSql.setString(4, laboratorio);
			
			insertSql.executeUpdate();
			
			insertSql = conn.prepareStatement("INSERT INTO MEDICAMENTO VALUES (?, ?, ?, ?)");
			
			insertSql.setInt(1, 17);
			insertSql.setString(2, "apiretal");
			insertSql.setFloat(3, 7.99f);
			insertSql.setString(4, "gilead");
			
			insertSql.executeUpdate();
			
//			stmt.executeUpdate(String.format("INSERT INTO MEDICAMENTO VALUES (%d, '%s', %f, '%s')", idM, nombreM, precio, laboratorio));
			
			System.out.println("Tablas para la base de datos rellenadas.");
			
		} catch (SQLException e) {
			System.out.println("La población de tablas ha fallado. Error: " + e);
		}
	}
	
	public void ensenyarMedicamentos() {
		try {
			rs = stmt.executeQuery("SELECT * FROM MEDICAMENTO;");
			
			while(rs.next()) {
				System.out.println(rs.getInt("id_m") + " " + rs.getString("nombre_m") + " " + rs.getFloat("precio") + " " + rs.getString("laboratorio"));	//si ponemos getString en todos también funciona, pero por
			}																																				//motivo desconocido en precio pondría más decimales al valor.
			
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al intentar enseñar los datos de medicamentos. Error: "+ e);
		}
	}
	
	public void borrarMedicamentos() {
		try {
			stmt.executeUpdate("DELETE FROM MEDICAMENTO;");
			System.out.println("Medicamentos borrados correctamente.");
		} catch (SQLException e) {
			System.out.println("No se han podido borrar los medicamentos. Error: " + e);
		}
	}
	
	public void cerrarBD() {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("La conexión a la base de datos no ha podido ser cerrada. Error: " + e);
		}
	}
	
	public static void main(String[] args) {
		FarmaciaBD fbd = new FarmaciaBD();
		fbd.conectarConBD();
		//fbd.crearBD();
		//fbd.poblarTablas();
		
		fbd.ensenyarMedicamentos();
		//fbd.borrarMedicamentos();
	}
	
	
}
