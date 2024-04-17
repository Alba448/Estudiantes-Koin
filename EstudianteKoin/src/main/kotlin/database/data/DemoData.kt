package org.example.database.data

import org.example.Estudiante.models.Estudiante
/**
 * Inicializa una lista de estudiantes de muestra con datos predefinidos.
 * @return Una lista de objetos [Estudiante] con datos predefinidos.
 */
fun initDemoEstudiante() = listOf(
    Estudiante(id=1, nombre = "Pepe", calificacion=4.8),
    Estudiante(id=2, nombre = "Lucia", calificacion=7.8),
    Estudiante(id=3, nombre = "Maria", calificacion=5.1),
    Estudiante(id=4, nombre = "Alejandro", calificacion=6.0)
)