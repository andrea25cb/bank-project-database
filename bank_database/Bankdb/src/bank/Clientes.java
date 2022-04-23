package bank;

public class Clientes {
	
	private String name;
	private String dni;
	private String email;
	private String pass;

	public Clientes(String dni, String name,  String email, String pass) {
		this.dni = dni;
		this.name = name;
		this.email = email;
		this.pass = pass;
	}
	
	public Clientes(String dni, String pass) { //con este método entrarán los clientes
		this.dni = dni;
		this.pass = pass;
	}
	
	public Clientes(String dni) { //para borrar clientes
		this.dni = dni;
	}

	public boolean equals(Clientes u) {
		if (this.email.equals(u.email) && this.getPass().equals(u.getPass()))
			return true;
		return false;
	}
	
	public String toString() {
		return "CLIENTE [DNI=" + dni+ " name=" + name + ", email=" + email + " , Pass="
				+ pass + "]\n";
		}

	public boolean equals(String dni) {
		if (this.dni.equals(dni))
			return true;
		return false;
	}

	public String getPass() {
		return pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
