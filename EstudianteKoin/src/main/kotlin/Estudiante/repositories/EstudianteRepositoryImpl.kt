package org.example.Estudiante.repositories

import org.example.Estudiante.models.Estudiante
import org.example.Estudiante.services.EstudianteService
import org.example.database.service.SqlDeLightManager
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

private val logger = logging()

/**
 * Implementación de la interfaz [EstudianteRepository] que utiliza un servicio y un gestor de base de datos.
 * @property service El servicio utilizado para acceder a los datos de los estudiantes.
 * @property dbManager El gestor de base de datos utilizado para interactuar con la base de datos.
 */
class EstudianteRepositoryImpl(
    private val service: EstudianteService,
    private val dbManager: SqlDeLightManager
) : EstudianteRepository {

    /**
     * Acceso a las consultas de la base de datos.
     */
    private val db = dbManager.databaseQueries

    /**
     * Identificador único del repositorio.
     */
    private val id = Long

    /**
     * Recupera una lista de estudiantes que tienen una calificación específica.
     * @param calificacion La calificación por la cual buscar estudiantes.
     * @return Una lista de estudiantes que tienen la calificación especificada.
     */
    override fun findByCalificacion(calificacion: Double): List<Estudiante> {
        logger.debug { "Obteniendo estudiantes por calificación: $calificacion" }
        return db.selectAllEstudiantesCalificacion(calificacion)
            .executeAsList()
            .map { it.toEstudiante() }
    }

    /**
     * Recupera todas las entidades de estudiantes.
     * @return Una lista de todas las entidades de estudiantes.
     */
    override fun findAll(): List<Estudiante> {
        logger.debug { "Obteniendo todos los estudiantes" }
        return db.selectAllEstudiante()
            .executeAsList()
            .map { it.toEstudiante() }
    }

    /**
     * Recupera un estudiante por su identificador único.
     * @param id El identificador único del estudiante a recuperar.
     * @return El estudiante encontrado, o null si no se encuentra ninguno con el identificador especificado.
     */
    override fun findById(id: Long): Estudiante? {
        logger.debug { "Obteniendo estudiante por ID: $id" }
        return db.selectEstudianteById(id)
            .executeAsOneOrNull()
            ?.toEstudiante()
    }

    /**
     * Guarda un nuevo estudiante.
     * @param estudiante El estudiante a guardar.
     * @return El estudiante guardado.
     */
    override fun save(estudiante: Estudiante): Estudiante {
        logger.debug { "Guardando estudiante: $estudiante" }
        val timeStamp = LocalDateTime.now().toString()

        db.transaction {
            db.insertEstudiante(
                nombre = estudiante.nombre,
                calificacion = estudiante.calificacion,
                created_at = timeStamp,
                updated_at = timeStamp
            )
        }
        return db.selectEstudianteLastInserted()
            .executeAsOne()
            .toEstudiante()
    }

    /**
     * Actualiza un estudiante existente.
     * @param id El identificador único del estudiante a actualizar.
     * @param estudiante El estudiante actualizado.
     * @return El estudiante actualizado, o null si no se encuentra ninguno con el identificador especificado.
     */
    override fun update(id: Long, estudiante: Estudiante): Estudiante? {
        logger.debug { "Actualizando estudiante por ID: $id" }
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()

        result = result.copy(
            nombre = estudiante.nombre,
            calificacion = estudiante.calificacion,
            isDeleted = estudiante.isDeleted,
            updatedAt = timeStamp
        )

        db.updateEstudiante(
            nombre = result.nombre,
            calificacion = result.calificacion,
            is_deleted = if (result.isDeleted) 1 else 0,
            updated_at = timeStamp.toString(),
            id = estudiante.id
        )
        return result
    }

    /**
     * Elimina un estudiante por su identificador único.
     * @param id El identificador único del estudiante a eliminar.
     * @return El estudiante eliminado, o null si no se encuentra ninguno con el identificador especificado.
     */
    override fun delete(id: Long): Estudiante? {
        logger.debug { "Borrando estudiante por ID: $id" }
        val result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()

        db.updateEstudiante(
            nombre = result.nombre,
            calificacion = result.calificacion,
            is_deleted = 1,
            updated_at = timeStamp.toString(),
            id = result.id
        )
        return result.copy(isDeleted = true, updatedAt = timeStamp)
    }

    /**
     * Devuelve una representación en formato de cadena del repositorio.
     * @return Una cadena que representa el repositorio.
     */
    override fun toString() = "EstudianteRepository(service=$service, id='$id')"
}