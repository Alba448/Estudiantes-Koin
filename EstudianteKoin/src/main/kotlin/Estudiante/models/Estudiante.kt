package org.example.Estudiante.models

import java.time.LocalDateTime

/**
 * Clase que representa a un estudiante.
 * @property id El identificador único del estudiante.
 * @property nombre El nombre del estudiante.
 * @property calificacion La calificación del estudiante.
 * @property createdAt La fecha y hora de creación del estudiante.
 * @property updatedAt La fecha y hora de la última actualización del estudiante.
 * @property isDeleted Indica si el estudiante ha sido eliminado.
 * @constructor Crea un nuevo objeto [Estudiante] con los datos especificados.
 */
class Estudiante(
    val id: Long,
    val nombre: String,
    val calificacion: Double,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val isDeleted: Boolean = false
)