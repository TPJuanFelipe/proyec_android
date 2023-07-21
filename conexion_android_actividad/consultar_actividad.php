<?php
// Incluir el archivo de conexión a la base de datos
include 'conexion.php';

// Preparar la consulta SQL para obtener las actividades
$consulta = "SELECT * FROM actividad";

// Ejecutar la consulta
$resultado = mysqli_query($conexion, $consulta);

// Verificar si se obtuvieron resultados
if ($resultado && mysqli_num_rows($resultado) > 0) {
    // Crear un array para almacenar las actividades
    $actividades = array();

    // Recorrer los resultados y guardar las actividades en el array
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $actividad = array(
            'id_trabajo' => $fila['id_trabajo'],
            'trabajoRealizado' => $fila['trabajoRealizado'],
            'fechaRealizado' => $fila['fechaRealizado'],
            'id_trabajador' => $fila['id_trabajador'],
        );
        $actividades[] = $actividad;
    }

    // Convertir el array de actividades a formato JSON
    $jsonActividades = json_encode($actividades);

    // Devolver las actividades en formato JSON
    echo $jsonActividades;
} else {
    echo "No se encontraron actividades en la base de datos.";
}

// Cerrar la conexión (if not already closed in "conexion.php")
mysqli_close($conexion);
?>
