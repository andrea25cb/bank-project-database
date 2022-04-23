package bank;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class Conexion {
		protected Connection con = null; //objeto iniciado

		public void conectar() throws SQLException {
			String db = "bankdb"; // esta es mi base de datos
			String password = "";
			String usuario = "root";
			String url = "jdbc:mysql://localhost:3306/"+db;
			 
			con = DriverManager.getConnection(url, usuario, password);
			// me devuelve la conexion//con es el canal de comunicacion

		}
	}