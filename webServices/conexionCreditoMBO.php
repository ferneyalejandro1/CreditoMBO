<?php 
	$conexion = mysqli_connect("https://php-creditombo.rhcloud.com/phpmyadmin/", "adminjpYJC6x", "zPe3PZqZ9GpH", "Creditos") 
				or die("Problemas con la conexion a la base de datos");

	$cedula = $_POST["cedula"];
	$nombre = $_POST["nombre"];
	$direccion = $_POST["direccion"];
	$telefono = $_POST["telefono"];
	$celular = $_POST["celular"];
	$otro = $_POST["otro"];
	$empresa = $_POST["empresa"];

	$nroPrestamo = $_POST["idCredito"];
	$monto = $_POST["valor"];
	$nroCuotas = $_POST["cuotas"];
	$intereses = $_POST["interes"];
	$fecha = $_POST["fecha"];

	$idCuota = $_POST["idCuota"];
	$vrCuota = $_POST["vrCuota"];
	$fechaCuota = $_POST["fechaCuota"];
	$pendiete = $_POST["pendiete"];

	mysqli_query($conexion, "insert into 'clientes' ('Cedula', 'Nombre', 'Direccion', 'Telefono', 'Celular', 'Otro', 'Empresa')
		values('$cedula', '$nombre', '$direccion', '$telefono', '$celular', '$otro', '$empresa')")
	or die("Problemas con la ejecucion del Query ".mysqli_error($conexion));

	mysqli_query($conexion, "insert into prestamos (NroPrestamo, Monto, NroCuotas, Intereses, Fecha, CedulaCliente)
		values(cr.idCredito, cr.valor, cr.cuotas, cr.interes, cr.fecha, cr.cedula)")
	or die("Problemas con la ejecucion del Query ".mysqli_error($conexion));

	mysqli_query($conexion, "insert into cuotas (idCuota, Valor, Fecha, Numero, NroPrestamo, Pendiente)
		values(cuota.idCuota, cuota.vrCuota, cuota.fecha, cuota.nroCuota, cuota.idCredito, cuota.pendiente)")
	or die("Problemas con la ejecucion del Query ".mysqli_error($conexion));

	mysqli_close($conexion);
 ?>