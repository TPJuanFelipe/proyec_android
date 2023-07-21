<?php
include 'conexion.php';

// Verificar si se recibieron todos los parámetros necesarios
if (isset($_POST['id_categoria'], $_POST['categorias'], $_POST['identifi_admin'])) {
    // Obtener los valores de los parámetros y sanitizarlos
    $id_categoria = mysqli_real_escape_string($conexion, $_POST['id_categoria']);
    $categorias = mysqli_real_escape_string($conexion, $_POST['categorias']);
    $identifi_admin = mysqli_real_escape_string($conexion, $_POST['identifi_admin']);

    // Preparar la consulta SQL utilizando una consulta preparada
    $consulta = "INSERT INTO categorias (id_categoria, categorias, identifi_admin) VALUES (?, ?, ?)";

    // Preparar la sentencia
    $stmt = mysqli_prepare($conexion, $consulta);
    if ($stmt) {
        // Enlazar los parámetros con los valores correspondientes
        mysqli_stmt_bind_param($stmt, 'iss', $id_categoria, $categorias, $identifi_admin);

        // Ejecutar la sentencia
        if (mysqli_stmt_execute($stmt)) {
            echo "Registro insertado correctamente";
        } else {
            echo "Error al insertar el registro: " . mysqli_stmt_error($stmt);
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
