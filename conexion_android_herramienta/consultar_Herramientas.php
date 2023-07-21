<?php
// Incluir el archivo de conexión a la base de datos
include 'conexion.php';

// Establecer la conexión a la base de datos
$conexion = mysqli_connect("localhost", "root", "", "p_inventariado");

// Verificar la conexión
if (mysqli_connect_errno()) {
    die("Error al conectar con la base de datos: " . mysqli_connect_error());
}

// Preparar la consulta SQL para obtener los productos
$consulta = "SELECT id_productos, nombre, descripcion, cantidad, imagen, id_categoria FROM productos";

// Ejecutar la consulta
$resultado = mysqli_query($conexion, $consulta);

// Verificar si se obtuvieron resultados
if ($resultado) {
    // Crear un array para almacenar los productos
    $productos = array();

    // Recorrer los resultados y guardar los productos en el array
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $producto = array(
            'id_productos' => $fila['id_productos'],
            'nombre' => $fila['nombre'],
            'descripcion' => $fila['descripcion'],
            'cantidad' => $fila['cantidad'],
            'imagen' => $fila['imagen'],
            'id_categoria' => $fila['id_categoria']
        );
        $productos[] = $producto;
    }

    // Convertir el array de productos a formato JSON
    $jsonHerramientas = json_encode($productos);

    // Devolver los productos en formato JSON
    echo $jsonHerramientas;
} else {
    echo "Error al obtener las Herramientas de la Base de datos: " . mysqli_error($conexion);
}

// Cerrar la conexión
mysqli_close($conexion);
?>
