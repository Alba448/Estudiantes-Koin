package org.example.Estudiante.controllers


import org.example.Estudiante.models.Estudiante
import org.example.Estudiante.repositories.EstudianteRepository
import org.lighthousegames.logging.logging

private val logger = logging()
/**
 * Controlador para la gestión de estudiantes.
 * @property estudianteRepository Repositorio de estudiantes utilizado para acceder a los datos.
 * @property serverUrl La URL del servidor utilizado por el controlador.
 */
class EstudianteController(
    private val estudianteRepository: EstudianteRepository,
    private val serverUrl: String
) {
    /**
     * Identificador único del controlador.
     */
    private val id = Long

    /**
     * Guarda un estudiante en el repositorio.
     * @param estudiante El estudiante que se va a guardar.
     * @return El estudiante guardado.
     */
    fun save(estudiante: Estudiante): Estudiante {
        logger.debug { "EstudianteController.save() --> $estudiante" }
        println("EstudianteController.save() --> $estudiante")
        return estudianteRepository.save(estudiante)
    }

    /**
     * Devuelve una representación en formato de cadena del controlador.
     * @return Una cadena que representa el controlador.
     */
    override fun toString() =
        "EstudianteController(estudianteRepository=$estudianteRepository, id='$id', serverUrl='$serverUrl')"
}