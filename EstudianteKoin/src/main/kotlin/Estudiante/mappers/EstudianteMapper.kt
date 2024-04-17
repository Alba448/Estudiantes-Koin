package org.example.Estudiante.mappers

import org.example.Estudiante.models.Estudiante

fun EstudianteEntity.toEstudiante(): Estudiante {
    return Producto(
        id = this.id,
        nombre = this.nombre,
        calificacion = this.precio,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
        isDeleted = this.is_deleted.toInt() == 1
    )
}