<?php
// Incluir el archivo de conexión a la base de datos
include 'conexion.php';

// Preparar la consulta SQL para obtener los trabajadores
$consulta = "SELECT * FROM trabajador";

// Ejecutar la consulta
$resultado = mysqli_query($conexion, $consulta);

// Verificar si se obtuvieron resultados
if ($resultado && mysqli_num_rows($resultado) > 0) {
    // Crear un array para almacenar los trabajadores
    $trabajadores = array();

    // Recorrer los resultados y guardar los trabajadores en el array
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $trabajador = array(
            'identificacion' => $fila['identificacion'],
            'tipo_identificacion' => $fila['tipo_identificacion'],
            'nombre_completo' => $fila['nombre_completo'],
            'correo' => $fila['correo'],
            'usuario' => $fila['usuario'],
            'contrasena' => $fila['contrasena'],
        );
        $trabajadores[] = $trabajador;
    }

    // Convertir el array de trabajadores a formato JSON
    $jsonTrabajadores = json_encode($trabajadores);

    // Devolver los trabajadores en formato JSON
    echo $jsonTrabajadores;
} else {
    echo "No se encontraron trabajadores en la base de datos.";
}

// Cerrar la conexión (if not already closed in "conexion.php")
mysqli_close($conexion);
?>

