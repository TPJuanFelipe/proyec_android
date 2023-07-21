 <?php
include 'conexion_login.php';

// Función para escapar y limpiar los valores de entrada
function limpiarEntrada($conexion, $valor) {
    return mysqli_real_escape_string($conexion, htmlspecialchars(trim($valor)));
}

// Verificar si se recibieron todos los parámetros necesarios
if (isset($_POST['identificacion'], $_POST['tipo_identificacion'], $_POST['nombre_completo'], $_POST['correo'], $_POST['usuario'], $_POST['contrasena'])) {
    // Obtener los valores de los parámetros y limpiarlos
    $identificacion = limpiarEntrada($conexion, $_POST['identificacion']);
    $tipo_identificacion = limpiarEntrada($conexion, $_POST['tipo_identificacion']);
    $nombre_completo = limpiarEntrada($conexion, $_POST['nombre_completo']);
    $correo = limpiarEntrada($conexion, $_POST['correo']);
    $usuario = limpiarEntrada($conexion, $_POST['usuario']);
    $contrasena = limpiarEntrada($conexion, $_POST['contrasena']);

    // Consulta para insertar un nuevo trabajador
    $query = "INSERT INTO trabajador (identificacion, tipo_identificacion, nombre_completo, correo, usuario, contrasena) VALUES (
                '$identificacion', '$tipo_identificacion', '$nombre_completo', '$correo', '$usuario', '$contrasena')";

    // Ejecutar la consulta
    $result = mysqli_query($conexion, $query);

    if ($result) {
        // Registro exitoso
        $response['status'] = 'success';
        $response['message'] = 'Registro exitoso';
        echo json_encode($response);
    } else {
        // Error al registrar el trabajador
        $response['status'] = 'error';
        $response['message'] = 'Error al registrar el trabajador: ' . mysqli_error($conexion);
        echo json_encode($response);
    }
} else {
    // Faltan parámetros necesarios
    $response['status'] = 'error';
    $response['message'] = 'Faltan parámetros necesarios';
    echo json_encode($response);
}

// Cerrar la conexión
mysqli_close($conexion);
?>
