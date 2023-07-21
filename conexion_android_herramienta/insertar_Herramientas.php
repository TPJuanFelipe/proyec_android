<?php
include 'conexion.php';

// Verificar si se recibieron todos los parámetros necesarios
if (isset($_POST['id_productos'], $_POST['nombre'], $_POST['descripcion'], $_POST['cantidad'], $_POST['imagen'], $_POST['id_categoria'], $_POST['identifi_admin'])) {
    // Obtener los valores de los parámetros y sanitizarlos
    $id_productos = (int) $_POST['id_productos'];
    $nombre = mysqli_real_escape_string($conexion, $_POST['nombre']);
    $descripcion = mysqli_real_escape_string($conexion, $_POST['descripcion']);
    $cantidad = (int) $_POST['cantidad'];
    $imagen = mysqli_real_escape_string($conexion, $_POST['imagen']);
    $id_categoria = (int) $_POST['id_categoria'];
    $identifi_admin = (int) $_POST['identifi_admin'];

    // Preparar la consulta SQL utilizando una consulta preparada
    $consulta = "INSERT INTO productos (id_productos, nombre, descripcion, cantidad, imagen, id_categoria, identifi_admin) VALUES (?, ?, ?, ?, ?, ?, ?)";

    // Preparar la sentencia
    $stmt = mysqli_prepare($conexion, $consulta);
    if ($stmt) {
        // Enlazar los parámetros con los valores correspondientes
        mysqli_stmt_bind_param($stmt, 'issisii', $id_productos, $nombre, $descripcion, $cantidad, $imagen, $id_categoria, $identifi_admin);

        // Ejecutar la sentencia
        if (mysqli_stmt_execute($stmt)) {
            echo "Herramienta insertada correctamente";
        } else {
            echo "Error al insertar la herramienta: " . mysqli_stmt_error($stmt);
        }

        // Cerrar la sentencia preparada
        mysqli_stmt_close($stmt);
    } else {
        echo "Error en la preparación de la consulta: " . mysqli_error($conexion);
    }
} else {
    echo "Faltan parámetros necesarios";
}

// Cerrar la conexión
mysqli_close($conexion);
?>

