<?php
// Incluir el archivo de conexión a la base de datos
include 'conexion.php';

// Establecer la conexión a la base de datos
$conexion = mysqli_connect("localhost", "root", "", "p_inventariado");

// Verificar la conexión
if (mysqli_connect_errno()) {
    die("Error al conectar con la base de datos: " . mysqli_connect_error());
}

// Preparar la consulta SQL para obtener las categorías
$consulta = "SELECT * FROM categorias";

// Ejecutar la consulta
$resultado = mysqli_query($conexion, $consulta);

// Verificar si se obtuvieron resultados
if ($resultado) {
    // Crear un array para almacenar las categorías
    $categorias = array();

    // Recorrer los resultados y guardar las categorías en el array
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $categoria = array(
            'id_categoria' => $fila['id_categoria'],
            'categorias' => $fila['categorias'],
            'identifi_admin' => $fila['identifi_admin']
        );
        $categorias[] = $categoria;
    }

    // Convertir el array de categorías a formato JSON
    $jsonCategorias = json_encode($categorias);

    // Devolver las categorías en formato JSON
    echo $jsonCategorias;
} else {
    echo "Error al obtener las categorías de la base de datos: " . mysqli_error($conexion);
}

// Cerrar la conexión
mysqli_close($conexion);
?>
