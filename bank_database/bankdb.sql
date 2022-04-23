CREATE TABLE clientes (
	dni VARCHAR(9) PRIMARY KEY,
	name VARCHAR(30),
	email VARCHAR(30) NOT NULL,
	pass VARCHAR(30) NOT NULL
	);
	
CREATE TABLE cuentas (
	iban VARCHAR(30) PRIMARY KEY,
	dni VARCHAR(9),
	saldo DOUBLE(12,2),
	FOREIGN KEY (dni) REFERENCES clientes(dni)
	); 