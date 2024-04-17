package org.example.Estudiante.repositories

import org.example.Estudiante.models.Estudiante

/**
 * Interfaz genérica que define las operaciones CRUD (Create, Read, Update, Delete) básicas sobre entidades.
 * @param T El tipo de la entidad.
 * @param ID El tipo del identificador único de la entidad.
 */
interface CrudRepository<T, ID> {
    /**
     * Recupera todas las entidades.
     * @return Una lista de todas las entidades.
     */
    fun findAll(): List<Estudiante>

    /**
     * Recupera una entidad por su identificador único.
     * @param id El identificador único de la entidad a recuperar.
     * @return La entidad encontrada, o null si no se encuentra ninguna entidad con el identificador especificado.
     */
    fun findById(id: Long): Estudiante?

    /**
     * Guarda una nueva entidad.
     * @param estudiante La entidad a guardar.
     * @return La entidad guardada.
     */
    fun save(estudiante: Estudiante): Estudiante

    /**
     * Actualiza una entidad existente.
     * @param id El identificador único de la entidad a actualizar.
     * @param estudiante La entidad actualizada.
     * @return La entidad actualizada, o null si no se encuentra ninguna entidad con el identificador especificado.
     */
    fun update(id: Long, estudiante: Estudiante): Estudiante?

    /**
     * Elimina una entidad por su identificador único.
     * @param id El identificador único de la entidad a eliminar.
     * @return La entidad eliminada, o null si no se encuentra ninguna entidad con el identificador especificado.
     */
    fun delete(id: Long): Estudiante?
}