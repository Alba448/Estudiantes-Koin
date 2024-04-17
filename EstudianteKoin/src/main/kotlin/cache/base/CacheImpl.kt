package org.example.cache.base

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.cache.errors.CacheError
import org.lighthousegames.logging.logging


private val logger = logging()

/**
 * Implementación de la interfaz [Cache] que utiliza un mapa mutable para almacenar los datos en caché.
 * @param K El tipo de las claves del caché.
 * @param T El tipo de los valores del caché.
 * @property size El tamaño máximo del caché.
 */
open class CacheImpl<K, T>(
    private val size: Int
) : Cache<K, T> {
    private val cache = mutableMapOf<K, T>()

    /**
     * Recupera el valor asociado con la clave especificada desde el caché.
     * @param key La clave cuyo valor asociado se va a recuperar.
     * @return Objeto [Result] que contiene el valor asociado con la clave o un [CacheError].
     */
    override fun get(key: K): Result<T, CacheError> {
        logger.debug { "Obteniendo valor de la cache" }
        return if (cache.containsKey(key)) {
            Ok(cache.getValue(key))
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    /**
     * Asocia el valor especificado con la clave especificada en el caché.
     * Si el tamaño máximo del caché se excede, se elimina el elemento más antiguo.
     * @param key La clave con la que se asociará el valor especificado.
     * @param value El valor que se asociará con la clave especificada.
     * @return Objeto [Result] que contiene el valor asociado con la clave o Nothing si es exitoso.
     */
    override fun put(key: K, value: T): Result<T, Nothing> {
        logger.debug { "Guardando valor en la cache" }
        if (cache.size >= size && !cache.containsKey(key)) {
            logger.debug { "Eliminando valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }

    /**
     * Elimina el mapeo para la clave especificada desde el caché si está presente.
     * @param key La clave cuyo mapeo se va a eliminar del caché.
     * @return Objeto [Result] que contiene el valor asociado con la clave o un [CacheError].
     */
    override fun remove(key: K): Result<T, CacheError> {
        logger.debug { "Eliminando valor de la cache" }
        return if (cache.containsKey(key)) {
            Ok(cache.remove(key)!!)
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    /**
     * Elimina todos los mapeos del caché.
     * @return Objeto [Result] que contiene Unit si es exitoso, de lo contrario, Nothing.
     */
    override fun clear(): Result<Unit, Nothing> {
        logger.debug { "Limpiando cache" }
        cache.clear()
        return Ok(Unit)
    }
}