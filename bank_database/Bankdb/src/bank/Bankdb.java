package bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Bankdb extends Conexion { // ESTA CLASE ES LA BASE DE DATOS

	private String nombreBanco;
	private Statement stm = null; // da la orden de comunicarse con la base de datos//se pone en cada método para que se comunique

	// constructores:
	public Bankdb(String nombreBanco) throws SQLException {
		this.conectar();
		this.nombreBanco = nombreBanco;
	}

	public Bankdb() throws SQLException {
		this.conectar();
	}

	// OPERACIONES ADMIN:
//para crear un cliente y a su cuenta//este funciona bien ya
	public boolean addCuenta(Cuentas cu) throws Exception {
		boolean ok = false;
		String valores = "'" + cu.getDni() + "','" + cu.getName() + "','" + cu.getEmail() + "','" + cu.getPass() + "'";
		// insert into clientes
		String sql = "insert into clientes values(" + valores + ")";

		stm = this.con.createStatement();
		ok = stm.execute(sql);
		// insert into cuentas
		valores = "'" + cu.getIban() + "','" + cu.getDni() + "'," + cu.getSaldo();
		sql = "insert into cuentas values(" + valores + ")";
		ok = stm.execute(sql);
		return ok;
	}

// para eliminar una cuenta con el dni//NO funciona
	public boolean dropCliente(String dni) throws Exception {
		boolean drop = false;
		String valores = "'" + dni + "'";
		
		String sql = "DELETE FROM cuentas WHERE dni =" + valores;
		stm = this.con.createStatement();
		stm.execute(sql);
		
		 sql = "DELETE FROM clientes WHERE dni =" + valores;
		stm = this.con.createStatement();
		stm.execute(sql);
		
		return drop;
	}

//para mostrar todos los clientes//este funciona//probar otra vez por si acaso
	public ArrayList<Clientes> getAllClientes() throws Exception {
		ArrayList<Clientes> list = new ArrayList<Clientes>(); // devuelvo esto
		
		String sql = "SELECT * FROM clientes";//todos los datos de todos los clientes
		stm = this.con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {// el next coge la siguiente tupla
			String dni = rs.getString(1);
			String name = rs.getString(2);
			String email = rs.getString(3);
			String pass = rs.getString(4);
			list.add(new Clientes(dni, name, email, pass));
		}
		return list;
	}

//para que el cliente entre con dni y su contraseña //funciona. no tocar.
	public Clientes getCliente(String dni, String pass) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE dni ='" + dni + "' and pass= '" + pass + "'";
		stm = this.con.createStatement(); //
		ResultSet rs = stm.executeQuery(sql);
		// System.out.println(email);
		if (rs.next()) {
			String dnii = rs.getString(1);
			String name = rs.getString(2);
			String email = rs.getString(3);
			String pass1 = rs.getString(4);
			return new Clientes(dnii, name, email, pass1);
		}
		return null;
	}

	// mostrar datos de cuenta //Funciona lo de eliminar cuentas!
	public Cuentas getCuenta(String dni) throws SQLException {
		String sql = "SELECT * FROM cuentas WHERE dni ='" + dni + "'";
		stm = this.con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		if (rs.next()) {
			String iban = rs.getString(1);
			String dnii = rs.getString(2);
			double saldo = rs.getDouble(3);
			return new Cuentas(iban, dnii, saldo);
		}return null;
	}
	//mostrar datos del cliente
	public Clientes getClien(String dni) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE dni ='" + dni + "'";
		stm = this.con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		if (rs.next()) {
			String dnii = rs.getString(1);
			String name = rs.getString(2);
			String email = rs.getString(3);
			String pass = rs.getString(4);
			return new Clientes(dnii, name, email, pass);
		}return null;
	}
	

	public String getNombreBanco() {
		return nombreBanco;
	}

	// OPERACIONES CLIENTE://
	public boolean ingresarDinero(Cuentas cu, double cantidad) throws SQLException { //ya funcionan 
		cantidad = cu.getSaldo()+cantidad;
		String dni = cu.getDni();
		String sql="UPDATE cuentas SET saldo="+cantidad+" WHERE dni='"+dni+"'";
		stm=this.con.createStatement();
		return stm.execute(sql);
	}

	public boolean sacarDinero(Cuentas cu, double cantidad) throws SQLException {
		cantidad = cu.getSaldo()-cantidad;
		String dni = cu.getDni();
		String sql="UPDATE cuentas SET saldo="+cantidad+" WHERE dni='"+dni+"'";
		stm=this.con.createStatement();
		return stm.execute(sql);
	}
	
	// para hacer una transferencia de la cuenta c1 a la cuenta c2
		public void hacerTransferencia(Cuentas c1, Cuentas c2, double cantidad) throws SQLException {
			if (this.ingresarDinero(c2, cantidad))//este si funciona
				sacarDinero(c1, cantidad);//no se me retira de la cuenta
		}
	
}
