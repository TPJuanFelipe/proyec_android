<?php
include 'conexion.php';

if(isset($_POST['id_categoria'], $_POST['categorias'], $_POST['identifi_admin'])) {
    $id_categoria = mysqli_real_escape_string($conexion, $_POST['id_categoria']);
    $categorias = mysqli_real_escape_string($conexion, $_POST['categorias']);
    $identifi_admin = mysqli_real_escape_string($conexion, $_POST['identifi_admin']);

    // Corrected SQL query with placeholders
    $modificar_cate = "UPDATE categorias SET categorias = ?, identifi_admin = ? WHERE id_categoria = ?";

    $consulta = mysqli_prepare($conexion, $modificar_cate);
    if($consulta) {
        mysqli_stmt_bind_param($consulta, 'ssi', $categorias, $identifi_admin, $id_categoria);
        if(mysqli_stmt_execute($consulta)) {
            echo "Categoría modificada con éxito.";
        } else {
            echo "Error al modificar la categoría: " . mysqli_stmt_error($consulta);
        }

        mysqli_stmt_close($consulta);
    } else {
        echo "Error en la preparación de la consulta: " . mysqli_error($conexion);
    }
} else {
    echo "Faltan parámetros necesarios.";
}

mysqli_close($conexion);
?>
