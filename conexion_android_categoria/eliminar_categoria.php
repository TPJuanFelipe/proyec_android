<?php
// Incluir el archivo de conexión a la base de datos
include 'conexion.php';

// Establecer la conexión a la base de datos
$conexion = mysqli_connect("localhost", "root", "", "p_inventariado");

// Verificar la conexión
if (mysqli_connect_errno()) {
    die("Error al conectar con la base de datos: " . mysqli_connect_error());
}

// Obtener el ID de la categoría a eliminar
$id_categoria = $_POST['id_categoria'];

// Preparar la consulta SQL para eliminar la categoría
$consulta = "DELETE FROM categorias WHERE id_categoria = $id_categoria";

// Ejecutar la consulta
$resultado = mysqli_query($conexion, $consulta);

// Verificar si se eliminó correctamente
if ($resultado) {
    echo "Registro eliminado correctamente";
} else {
    echo "Error al eliminar el registro: " . mysqli_error($conexion);
}

// Cerrar la conexión
mysqli_close($conexion);
?>
