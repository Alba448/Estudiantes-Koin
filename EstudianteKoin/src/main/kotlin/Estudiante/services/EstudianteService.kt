package org.example.Estudiante.services
import com.github.michaelbull.result.Result
import org.example.Estudiante.errors.EstudianteError
import org.example.Estudiante.models.Estudiante

/**
 * Interfaz que define operaciones para acceder y manipular datos de estudiantes.
 */
interface EstudianteService {

        /**
         * Recupera todos los estudiantes.
         * @return Un resultado que contiene una lista de estudiantes si la operación tiene éxito, o un error si falla.
         */
        fun getAll(): Result<List<Estudiante>, EstudianteError>

        /**
         * Recupera una lista de estudiantes que tienen una calificación específica.
         * @param calificacion La calificación por la cual buscar estudiantes.
         * @return Un resultado que contiene una lista de estudiantes si la operación tiene éxito, o un error si falla.
         */
        fun getByCalificacion(calificacion: Double): Result<List<Estudiante>, EstudianteError>

        /**
         * Recupera un estudiante por su identificador único.
         * @param id El identificador único del estudiante a recuperar.
         * @return Un resultado que contiene el estudiante si la operación tiene éxito, o un error si falla.
         */
        fun findById(id: Long): Result<Estudiante, EstudianteError>

        /**
         * Crea un nuevo estudiante.
         * @param estudiante El estudiante a crear.
         * @return Un resultado que contiene el estudiante creado si la operación tiene éxito, o un error si falla.
         */
        fun create(estudiante: Estudiante): Result<Estudiante, EstudianteError>

        /**
         * Actualiza un estudiante existente.
         * @param id El identificador único del estudiante a actualizar.
         * @param estudiante El estudiante actualizado.
         * @return Un resultado que contiene el estudiante actualizado si la operación tiene éxito, o un error si falla.
         */
        fun update(id: Long, estudiante: Estudiante): Result<Estudiante, EstudianteError>

        /**
         * Elimina un estudiante por su identificador único.
         * @param id El identificador único del estudiante a eliminar.
         * @return Un resultado que contiene el estudiante eliminado si la operación tiene éxito, o un error si falla.
         */
        fun delete(id: Long): Result<Estudiante, EstudianteError>
}