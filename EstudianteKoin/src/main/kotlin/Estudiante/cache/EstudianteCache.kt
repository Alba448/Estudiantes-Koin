package org.example.Estudiante.cache

import org.example.Estudiante.models.Estudiante
import org.example.cache.base.CacheImpl

/**
 * Implementación de la interfaz [Cache] para almacenar objetos de tipo [estudiante].
 * Extiende la clase [CacheImpl] con claves de tipo Long y valores de tipo [estudiante].
 * @property size El tamaño máximo de la caché.
 * @constructor Crea una instancia de [EstudianteCache] con el tamaño especificado para la caché.
 */
class EstudianteCache(size: Int) : CacheImpl<Long, Estudiante>(size) {
}