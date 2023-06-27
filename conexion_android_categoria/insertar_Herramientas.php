<?php

$hostname = 'localhost';
$database = 'p_inventariado';
$username = 'root';
$password = '';

$conexion = new mysqli($hostname, $username, $password, $database);
if ($conexion->connect_errno) {
    echo "Lo siento, la página web está experimentando problemas.";
}

// Verificar si se envió el formulario de inserción
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Obtener los datos del formulario
    $id_productos = $_POST['id_productos'];
    $nombre = $_POST['nombre'];
    $descripcion = $_POST['descripcion'];
    $cantidad = $_POST['cantidad'];
    $imagen = $_POST['imagen'];
    $id_categoria = $_POST['id_categoria'];
    $identifi_admin = $_POST['identifi_admin'];

    // Preparar la consulta SQL
    $sql = "INSERT INTO productos (id_productos,nombre, descripcion, cantidad, imagen, id_categoria, identifi_admin) 
            VALUES ('$id_productos','$nombre', '$descripcion', '$cantidad', '$imagen', '$id_categoria', '$identifi_admin')";

    // Ejecutar la consulta
    if ($conexion->query($sql) === TRUE) {
        echo "La herramienta se insertó correctamente.";
    } else {
        echo "Error al insertar la herramienta: " . $conexion->error;
    }
}

// Cerrar la conexión
$conexion->close();

?>