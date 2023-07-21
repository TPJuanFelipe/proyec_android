<?php
include 'conexion.php';

// Verificar si se recibieron todos los parámetros necesarios
if (isset($_POST['id_trabajo'], $_POST['trabajoRealizado'], $_POST['fechaRealizado'], $_POST['id_trabajador'])) {
    // Obtener los valores de los parámetros y sanitizarlos
    
    $id_trabajo = (int)$_POST['id_trabajo'];
    $trabajoRealizado = mysqli_real_escape_string($conexion, $_POST['trabajoRealizado']);
    $fechaRealizado = mysqli_real_escape_string($conexion, $_POST['fechaRealizado']);
    $id_trabajador = (int) $_POST['id_trabajador'];
    

    // Preparar la consulta SQL utilizando una consulta preparada
    $consulta = " INSERT INTO actividad (id_trabajo, trabajoRealizado, fechaRealizado, id_trabajador) VALUES (?,  ?, ?, ?)";

    // Preparar la sentencia
    $stmt = mysqli_prepare($conexion, $consulta);
    if ($stmt) {
        // Enlazar los parámetros con los valores correspondientes
        mysqli_stmt_bind_param($stmt, 'issi', $id_trabajo, $trabajoRealizado, $fechaRealizado, $id_trabajador);

        // Ejecutar la sentencia
        if (mysqli_stmt_execute($stmt)) {
            echo "actividad insertada correctamente";
        } else {
            echo "Error al insertada la actividad  " . mysqli_stmt_error($stmt);
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
