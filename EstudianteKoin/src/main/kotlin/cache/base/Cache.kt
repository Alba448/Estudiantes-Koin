package org.example.cache.base
import com.github.michaelbull.result.Result
import org.example.cache.errors.CacheError

/**
 * Una interfaz genérica de caché para almacenar pares clave-valor.
 * @param K El tipo de las claves del caché.
 * @param T El tipo de los valores del caché.
 */
interface Cache<K, T> {

    /**
     * Recupera el valor asociado con la clave especificada desde el caché.
     * @param key La clave cuyo valor asociado se va a recuperar.
     * @return Objeto [Result] que contiene el valor asociado con la clave o un [CacheError].
     */
    fun get(key: K): Result<T, CacheError>

    /**
     * Asocia el valor especificado con la clave especificada en el caché.
     * @param key La clave con la que se asociará el valor especificado.
     * @param value El valor que se asociará con la clave especificada.
     * @return Objeto [Result] que contiene el valor asociado con la clave o Nothing si es exitoso.
     */
    fun put(key: K, value: T): Result<T, Nothing>

    /**
     * Elimina el mapeo para la clave especificada desde el caché si está presente.
     * @param key La clave cuyo mapeo se va a eliminar del caché.
     * @return Objeto [Result] que contiene el valor asociado con la clave o un [CacheError].
     */
    fun remove(key: K): Result<T, CacheError>

    /**
     * Elimina todos los mapeos del caché.
     * @return Objeto [Result] que contiene Unit si es exitoso, de lo contrario, Nothing.
     */
    fun clear(): Result<Unit, Nothing>
}