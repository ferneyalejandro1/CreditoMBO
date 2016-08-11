 <html>
 <head>
 	<title></title>
 </head>
 <body>
 	
<?php 
	//$servername = getenv("OPENSHIFT_MYSQL_DB_HOST");
	//$username 	= getenv("OPENSHIFT_MYSQL_DB_USERNAME");
	//$password 	= getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
	//$dbname 	= "Creditos";
	//crear conexion
	$conexion = new mysqli("localhost", "root", "", "creditos");
	//$conexion = new mysqli($servername, $username, $password, $dbname);

	//verifica la conexion
	if ($conexion->connect_error) {
		die("Fallo la conexion con la BD:".$conexion->connect_error);
	}else{
		//echo "Conexion exitosa";
	}
	
	$sql = "SELECT * FROM clientes";
		
	$resultado = $conexion -> query($sql);
	
	if ($resultado->num_rows > 0){
	//salida de datos de cada fila
		while ($row = $resultado->fetch_assoc()) {
			echo var_dump($row);
			//array(7) { ["Cedula"]=> string(7) "8105756" 
						//["Nombre"]=> string(15) "Ferney Gonzalez" 
						//["Direccion"]=> string(22) "Cra 64 b 29-236 ap 501" 
						//["Telefono"]=> string(7) "5773037" 
						//["Celular"]=> string(10) "3103750108" 
						//["OtroTel"]=> string(7) "3784149" ["Empresa"]=> string(8) "WeBit.co" }
		}
	}else {
		echo "Cero";
	}
	$conexion->close();
 ?> 
 </body>
 </html>