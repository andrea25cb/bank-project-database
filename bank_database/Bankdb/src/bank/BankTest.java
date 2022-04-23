package bank;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BankTest {

	static Scanner teclado = new Scanner(System.in);
	public static Bankdb ruralvia;
	public static Clientes cliente;
	public static Cuentas cuenta;
	public static String iban = "";
	public static String nombre = "";
	public static String pass = "";
	public static String email = "";

	public static void main(String[] args) throws Exception {

		try { // creo el objeto
			ruralvia = new Bankdb();
			// me conecto
			System.out.println("Estás conectado");

		} catch (SQLException e) {
			System.out.println("Problemas con la base de datos");
			e.printStackTrace();
		}

		int op = 0;
		do {
			op = menuPrincipal();
			switch (op) {
			case 1:
				entrarAdmin();

				break;
			case 2:
				entrarCliente(ruralvia, cuenta);

				break;
			case 3:
				System.out.println("\n¡Hasta la próxima!");
				break;
			}
		} while (op != 3);
	}

	// menú principal, sale lo primero, elegir entre administrador o cliente
	public static int menuPrincipal() throws Exception {
		System.out.println("\nBienvenido a RURALVÍA\n\tMenu Principal");
		System.out.println("__________________________________");
		System.out.println("¿Qué desea hacer?");
		System.out.println("1. Ingresar como administrador\n2. Ingresar como cliente \n3. Salir");
		System.out.println("Opción:");
		int op = teclado.nextInt();
		return op;
	}

	// Metodo para identificarse
	public static void entrarCliente(Bankdb ruralvia, Cuentas cuenta) throws SQLException {
		System.out.println("Introduce tu DNI:");
		String introDNI = teclado.next();
		System.out.println("Ahora tu contraseña:");
		String introPass = teclado.next();
		
		// Comprobar email y contraseña del cliente
		cliente = ruralvia.getCliente(introDNI, introPass); //NO BORRAR
		if (introDNI.equals(cliente.getDni()) && introPass.equals(cliente.getPass())) {
		int op = 0;
		double cantidad = 0;
		do {
			op = menuCliente();
			switch (op) {
			case 1://ingresar dinero
				cuenta = ruralvia.getCuenta(introDNI);
				System.out.println(cuenta);
				System.out.println("¿Cuánto desea ingresar?");
				cantidad = teclado.nextDouble();
				ruralvia.ingresarDinero(cuenta, cantidad);
				System.out.println("Has ingresado: "+cantidad+" euros");
				break;
				
			case 2://retirar dinero
				System.out.println("Introduce tu DNI:");
				introDNI = teclado.next();
				cuenta = ruralvia.getCuenta(introDNI);
				System.out.println(cuenta);
				System.out.println("¿Cuánto desea retirar?");
				cantidad = teclado.nextDouble();
				ruralvia.sacarDinero(cuenta,cantidad);
				System.out.println("Has retirado: "+cantidad+" euros");
				break;
				
			case 3: //ver mis datos//funciona //cambiar esto por: hacer una transferencia
				System.out.println("Introduce tu DNI:");
				introDNI = teclado.next();
				cuenta = ruralvia.getCuenta(introDNI);
				System.out.println(cliente);
				System.out.println(cuenta);
				break;
				
			case 4://hacer transferencia
				System.out.println("Introduce el DNI de la cuenta que quiere hacer la transferencia: ");
				introDNI = teclado.next();
				Cuentas c1 = ruralvia.getCuenta(introDNI);
				System.out.println("Introduce el DNI del receptor de la transferencia: ");
				introDNI = teclado.next();
				Cuentas c2 = ruralvia.getCuenta(introDNI);
				System.out.println("Introduce una cantidad: ");
				cantidad = teclado.nextDouble();
				ruralvia.hacerTransferencia(c1, c2, cantidad);
				System.out.println("Transferencia realizada con éxito.");
				break;
				
			case 5: //salir
				System.out.println("¡Hasta la próxima!");
			}
		} while (op != 5);
	}else { //
		System.out.println("Cuenta no registrada");
	}
}
	public static void entrarAdmin() throws Exception {// zona de administración
		System.out.println("IDENTIFÍQUESE:");
		System.out.println("Usuario:");
		String nombreAdmin = teclado.next();
		System.out.println("Contraseña:");
		String passAdmin = teclado.next();
		
		if (nombreAdmin.equals("Admin") && passAdmin.equals("1234")) {
			System.out.println("\n_____MODO ADMINISTRADOR ACTIVADO____");

			int op;
			do {
				op = menuAdmin();
				System.out.println("\n");
				switch (op) {
				case 1:
					System.out.println("Introduce el nombre de la nueva cuenta:");
					nombre = teclado.next();
					System.out.println("Introduce la contraseña de la nueva cuenta:");
					pass = teclado.next();
					System.out.println("Introduce el IBAN de la nueva cuenta:");
					iban = teclado.next();
					System.out.println("DNI del cliente:");
					String introDNI = teclado.next();
					System.out.println("Email del cliente:");
					String email = teclado.next();
					double saldo = 0.0;
					Cuentas newCuenta = new Cuentas(introDNI, nombre, email, pass, iban);
					ruralvia.addCuenta(newCuenta);
					System.out.println("¡Cuenta creada con éxito!");
					break;
				case 2:
					System.out.println("Introduce el DNI de la cuenta a eliminar:");
					introDNI = teclado.next();
					Clientes cl =ruralvia.getClien(introDNI);//borra cliente
					Cuentas cu = ruralvia.getCuenta(introDNI);//borra cuenta
					if (cl!=null && cu!=null) {
						ruralvia.dropCliente(introDNI);
						System.out.println("Cuenta eliminada con éxito");
					} else {
						System.out.println("Cuenta no registrada");
					}
					break;
				case 3:
					System.out.println("Introduce el DNI de la cuenta a mostrar:");
					introDNI = teclado.next();
					cl =ruralvia.getClien(introDNI);
					cu = ruralvia.getCuenta(introDNI);
					if(cl!=null) {
					System.out.println(cl);
				}else {
						System.out.println("NO EXISTE CLIENTE");}
					if(cu!=null) {
					System.out.println(cu);
					}else {
						System.out.println("NO EXISTE CUENTA");}
					break;
				case 4:System.out.println("-- DATOS DE TODOS LOS CLIENTES: --");
				ArrayList<Clientes> allCli = ruralvia.getAllClientes();
				System.out.println(allCli);
					break;
				case 5:
					System.out.println("¡Hasta la próxima!");break;
				}
		}while (op != 5);
	}else {
			System.out.println("No eres administrador");
		}
	}

	// una vez que el usuario se ha identificado, se usa este método para que el
	// cliente realice operaciones
	public static int menuCliente() {
		System.out.println("¡Bienvenido!");
		System.out.println("¿Qué desea hacer?");
		System.out.println("\t1. Ingresar dinero. \n\t2. Retirar dinero.\n\t3. Ver mis datos.\n\t4. Hacer una transferencia.\n\t5. Salir.");
		int op = teclado.nextInt();
		return op;
	}

	// //una vez que el admin se ha identificado, se usa este método para que
	// realice operaciones
	public static int menuAdmin() {
		System.out.println("\tBienvenido, administrador ");
		System.out.println("__________________________________");
		System.out.println("¿Qué desea hacer?");
		System.out.println(
				"\n\t1. Crear una cuenta bancaria\n\t2. Eliminar una cuenta bancaria\n\t3. Mostrar una cuenta bancaria concreta\n\t4. Mostrar todas los clientes\n\t5. Salir");
		int op = teclado.nextInt();
		return op;

	}

}
