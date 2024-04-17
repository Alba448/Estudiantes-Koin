package org.example.Estudiante.services

import com.github.michaelbull.result.*
import org.example.Estudiante.cache.EstudianteCache
import org.example.Estudiante.errors.EstudianteError
import org.example.Estudiante.models.Estudiante
import org.example.Estudiante.repositories.EstudianteRepository
import org.example.Estudiante.validators.EstudianteValidator
import org.lighthousegames.logging.logging


private val logger = logging()

/**
 * Implementación de la interfaz [EstudianteService] que utiliza un repositorio de estudiantes,
 * una caché de estudiantes y un validador de estudiantes.
 * @property estudianteRepository El repositorio utilizado para acceder a los datos de los estudiantes.
 * @property estudianteCache La caché utilizada para almacenar los estudiantes.
 * @property estudianteValidator El validador utilizado para validar los estudiantes antes de guardar o actualizar.
 */
class EstudianteServiceImpl(
    private val estudianteRepository: EstudianteRepository,
    private val estudianteCache: EstudianteCache,
    private val estudianteValidator: EstudianteValidator
) : EstudianteService {

    /**
     * Identificador único del servicio.
     */
    private val id = Long

    /**
     * Recupera todos los estudiantes.
     * @return Un resultado que contiene una lista de estudiantes si la operación tiene éxito, o un error si falla.
     */
    override fun getAll(): Result<List<Estudiante>, EstudianteError> {
        logger.debug { "Obteniendo todos los estudiantes" }
        return Ok(estudianteRepository.findAll())
    }

    /**
     * Recupera una lista de estudiantes que tienen una calificación específica.
     * @param calificacion La calificación por la cual buscar estudiantes.
     * @return Un resultado que contiene una lista de estudiantes si la operación tiene éxito, o un error si falla.
     */
    override fun getByCalificacion(calificacion: Double): Result<List<Estudiante>, EstudianteError> {
        logger.debug { "Obteniendo estudiantes por calificación: $calificacion" }
        return Ok(estudianteRepository.findByCalificacion(calificacion))
    }

    /**
     * Recupera un estudiante por su identificador único.
     * @param id El identificador único del estudiante a recuperar.
     * @return Un resultado que contiene el estudiante si la operación tiene éxito, o un error si falla.
     */
    override fun findById(id: Long): Result<Estudiante, EstudianteError> {
        logger.debug { "Recuperando estudiante por ID: $id" }
        return estudianteRepository.findById(id)
            ?.let { Ok(it) }
            ?: Err(EstudianteError.EstudianteNoEncontrado("Estudiante no encontrado con ID: $id"))
    }

    /**
     * Crea un nuevo estudiante.
     * @param estudiante El estudiante a crear.
     * @return Un resultado que contiene el estudiante creado si la operación tiene éxito, o un error si falla.
     */
    override fun create(estudiante: Estudiante): Result<Estudiante, EstudianteError> {
        logger.debug { "Guardando estudiante: $estudiante" }
        return estudianteValidator.validate(estudiante).andThen { validatedStudent ->
            Ok(estudianteRepository.save(validatedStudent))
        }.andThen { savedStudent ->
            println("Guardando en caché")
            estudianteCache.put(savedStudent.id, savedStudent)
        }
    }

    /**
     * Actualiza un estudiante existente.
     * @param id El identificador único del estudiante a actualizar.
     * @param estudiante El estudiante actualizado.
     * @return Un resultado que contiene el estudiante actualizado si la operación tiene éxito, o un error si falla.
     */
    override fun update(id: Long, estudiante: Estudiante): Result<Estudiante, EstudianteError> {
        logger.debug { "Actualizando estudiante por ID: $id" }
        return estudianteValidator.validate(estudiante).andThen { validatedStudent ->
            estudianteRepository.update(id, validatedStudent)
                ?.let { Ok(it) }
                ?: Err(EstudianteError.EstudianteNoActualizado("Estudiante no actualizado con ID: $id"))
        }.andThen { updatedStudent ->
            estudianteCache.put(id, updatedStudent)
        }
    }

    /**
     * Elimina un estudiante por su identificador único.
     * @param id El identificador único del estudiante a eliminar.
     * @return Un resultado que contiene el estudiante eliminado si la operación tiene éxito, o un error si falla.
     */
    override fun delete(id: Long): Result<Estudiante, EstudianteError> {
        logger.debug { "Borrando estudiante por ID: $id" }
        return estudianteRepository.delete(id)
            ?.let { deletedStudent ->
                estudianteCache.remove(id)
                Ok(deletedStudent)
            }
            ?: Err(EstudianteError.EstudianteNoEliminado("Estudiante no eliminado con ID: $id"))
    }

    /**
     * Devuelve una representación en formato de cadena del servicio.
     * @return Una cadena que representa el servicio.
     */
    override fun toString() = "EstudianteDataBaseStorage(id='$id')"
}