<?php
// Incluir el archivo de conexión a la base de datos
include 'conexion.php';

// Establecer la conexión a la base de datos
$conexion = mysqli_connect("localhost", "root", "", "p_inventariado");

// Verificar la conexión
if (mysqli_connect_errno()) {
    die("Error al conectar con la base de datos: " . mysqli_connect_error());
}

// Obtener los datos del formulario
    $id_productos = $_POST['id_productos'];
    $nombre = $_POST['nombre'];
    $descripcion = $_POST['descripcion'];
    $cantidad = $_POST['cantidad'];
    $imagen = $_POST['imagen'];
    $id_categoria = $_POST['id_categoria'];
    $identifi_admin = $_POST['identifi_admin'];

// Preparar la consulta SQL para actualizar el registro
$consulta = "UPDATE productos SET nombre = '$nombre', descripcion = '$descripcion', cantidad = $cantidad , imagen = '$imagen'  identifi_admin = $identifi_admin WHERE id_productos = $id_productos";

// Ejecutar la consulta
$resultado = mysqli_query($conexion, $consulta);

// Verificar si la modificación se realizó correctamente
if ($resultado) {
    echo "Registro modificado correctamente";
} else {
    echo "Error al modificar el registro: " . mysqli_error($conexion);
}

// Cerrar la conexión
mysqli_close($conexion);
?>

