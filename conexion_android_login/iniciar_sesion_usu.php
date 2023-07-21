<?php

include 'conexion_login.php';

// Función para escapar y limpiar los valores de entrada
function limpiarEntrada($conexion, $valor) {
    return mysqli_real_escape_string($conexion, htmlspecialchars(trim($valor)));
}

// Verificar si se recibieron todos los parámetros necesarios
if (isset($_POST['correo'], $_POST['contrasena'])) {
    // Obtener los valores de los parámetros y limpiarlos
    $correo = limpiarEntrada($conexion, $_POST['correo']);
    $contrasena = limpiarEntrada($conexion, $_POST['contrasena']);  

    // Consultar el usuario en la tabla de trabajador
    $sql = "SELECT * FROM trabajador WHERE correo = '$correo' AND contrasena = '$contrasena'";
    $result = mysqli_query($conexion, $sql);

    if ($result && mysqli_num_rows($result) > 0) {
        // Inicio de sesión exitoso
        $response = array('status' => 'success', 'message' => 'Login exitoso');
    } else {
        // Credenciales incorrectas
        $response = array('status' => 'error', 'message' => 'Credenciales incorrectas');
    }
} else {
    $response = array('status' => 'error', 'message' => 'Error al reconocer los datos');
}

mysqli_close($conexion);

// Enviar la respuesta en formato JSON
header('Content-Type: application/json');
echo json_encode($response);
?>
