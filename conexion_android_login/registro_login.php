<?php
include 'conexion_login.php';

// Verificar si se recibieron todos los parámetros necesarios
if (isset($_POST['identificacion'], $_POST['tipo_identificacion'], $_POST['nombre_completo']), $_POST['correo'], $_POST['usuario'], $_POST['contrasena']) {
    // Obtener los valores de los parámetros y sanitizarlos
    $identificacion = mysqli_real_escape_string($conexion, $_POST['identificacion']);
    $tipo_identificacion = mysqli_real_escape_string($conexion, $_POST['tipo_identificacion']);
    $nombre_completo = mysqli_real_escape_string($conexion, $_POST['nombre_completo']);
    $correo = mysqli_real_escape_string($conexion, $_POST['correo']);
    $usuario = mysqli_real_escape_string($conexion, $_POST['usuario']);
    $contrasena = mysqli_real_escape_string($conexion, $_POST['contrasena']);

    // Preparar la consulta SQL utilizando una consulta preparada
    $consulta = "INSERT INTO trabajador (identificacion, tipo_identificacion, nombre_completo, correo, usuario, contrasena) VALUES (?, ?, ?,?,?,?)";

    // Preparar la sentencia
    $stmt = mysqli_prepare($conexion, $consulta);
    if ($stmt) {
        // Enlazar los parámetros con los valores correspondientes
        mysqli_stmt_bind_param($stmt, 'iss', $identificacion, $tipo_identificacion, $nombre_completo, $correo , $usuario , $contrasena);

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
