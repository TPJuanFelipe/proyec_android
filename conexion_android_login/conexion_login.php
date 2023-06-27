<?php

$hostname = 'localhost';
$database = 'p_inventariado';
$username = 'root';
$password = 'your_password';

$conexion= new mysqli($hostname, $username, $password, $database );
if($conexion->connect_errno){
    echo"lo sien,la pagina wed esta experimentado  problemas";
}

?>