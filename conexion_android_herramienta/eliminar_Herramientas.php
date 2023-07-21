<?php
include 'conexion.php';

// Verificar si se recibió el parámetro 'id_productos'
if (isset($_POST['id_productos'])) {
    // Obtener el valor del parámetro y sanitizarlo
    $id_productos = (int) $_POST['id_productos'];

    // Preparar la consulta SQL utilizando una consulta preparada
    $consulta = "DELETE FROM productos WHERE id_productos = ?";

    // Preparar la sentencia
    $stmt = mysqli_prepare($conexion, $consulta);
    if ($stmt) {
        // Enlazar el parámetro con el valor correspondiente
        mysqli_stmt_bind_param($stmt, 'i', $id_productos);

        // Ejecutar la sentencia
        if (mysqli_stmt_execute($stmt)) {
            // Verificar si se eliminó algún registro
            if (mysqli_stmt_affected_rows($stmt) > 0) {
                echo "Herramienta eliminada correctamente";
            } else {
                echo "No se encontró ningún registro con el ID proporcionado";
            }
        } else {
            echo "Error al eliminar la herramienta: " . mysqli_stmt_error($stmt);
        }

        // Cerrar la sentencia preparada
        mysqli_stmt_close($stmt);
    } else {
        echo "Error en la preparación de la consulta: " . mysqli_error($conexion);
    }
} else {
    echo "Falta el parámetro 'id_productos'";
}

// Cerrar la conexión
mysqli_close($conexion);
?>
