package bank;

import java.sql.SQLException;

public class Cuentas extends Clientes {
//esta es mi bankAcccount
	String iban;
	String dni; // titular =cliente
	double saldo;
	String pass;

	public Cuentas(String dni, String name, String email, String pass, String IBAN) {
		super(dni, name, email, pass);
		this.iban = IBAN;
		this.dni = dni;
		this.saldo = 0; // para que al crear la cuenta el saldo se inicie a 0
	}

//cliente ingresa a su cuenta
	public Cuentas(String dni, String pass) {
		super(dni, pass);
		this.dni = dni;
	}

	// para que el cliente ingrese/saque dinero en su cuenta
	public Cuentas(String iban, String dni, double saldo) {
		super(dni);
		this.iban = iban;
		this.dni = dni;
		this.saldo = saldo;
		//this.saldo = 0;
	}

	public String toString() {// visualiza solo los datos de la cuenta, q si no me salen repes
		return "\n\tIBAN= " + iban + "\tSALDO = " + saldo + "\n";
	}

	// GETS & SETS

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
