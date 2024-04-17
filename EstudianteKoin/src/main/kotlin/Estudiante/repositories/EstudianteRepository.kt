package org.example.Estudiante.repositories

import org.example.Estudiante.models.Estudiante

/**
 * Interfaz que extiende la interfaz [CrudRepository] para proporcionar operaciones específicas para la entidad Estudiante.
 */
interface EstudianteRepository : CrudRepository<Estudiante, Long> {
    /**
     * Recupera una lista de estudiantes que tienen una calificación específica.
     * @param calificacion La calificación por la cual buscar estudiantes.
     * @return Una lista de estudiantes que tienen la calificación especificada.
     */
    fun findByCalificacion(calificacion: Double): List<Estudiante>
}