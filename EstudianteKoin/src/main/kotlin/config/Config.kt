package org.example.config

import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

private val logger = logging()

/**
 * Clase que representa la configuración de la aplicación.
 * Contiene las propiedades relacionadas con la base de datos, almacenamiento y caché.
 */
class Config {
    /**
     * La URL de la base de datos.
     */
    var databaseUrl: String = "jdbc:sqlite:estudiantes.db"
        private set

    /**
     * Indica si se deben inicializar las tablas de la base de datos al iniciar la aplicación.
     */
    var databaseInitTables: Boolean = false
        private set

    /**
     * Indica si se deben inicializar los datos en la base de datos al iniciar la aplicación.
     */
    var databaseInitData: Boolean = false
        private set

    /**
     * Indica si se debe utilizar una base de datos en memoria.
     */
    var databaseInMemory: Boolean = false
        private set

    /**
     * La ubicación del directorio de almacenamiento de datos.
     */
    var storageData: String = "data"
        private set

    /**
     * El tamaño máximo de la caché.
     */
    var cacheSize: Int = 6
        private set

    /**
     * Inicializa la configuración cargando valores desde un archivo de propiedades.
     * Si no se puede cargar el archivo de propiedades o si hay algún error, se utilizan valores por defecto.
     */
    init {
        try {
            logger.debug { "Cargando configuración" }
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables =
                properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData =
                properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
            databaseInMemory =
                properties.getProperty("database.inmemory", this.databaseInMemory.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", this.storageData)
            cacheSize = properties.getProperty("cache.size", this.cacheSize.toString()).toInt()
            logger.debug { "Configuración cargada correctamente" }

            // Crear el directorio si no existe
            Files.createDirectories(Path(storageData))

        } catch (e: Exception) {
            logger.error { "Error cargando configuración: ${e.message}" }
            logger.error { "Usando valores por defecto" }
        }

    }
}