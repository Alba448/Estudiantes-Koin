package org.example.database.service
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries


import org.example.database.data.initDemoEstudiante
import org.lighthousegames.logging.logging

private val logger = logging()

/**
 * Clase que gestiona la base de datos utilizando SQLDelight.
 * @property databaseUrl La URL de la base de datos.
 * @property databaseInMemory Indica si se utilizará una base de datos en memoria.
 * @property databaseInitData Indica si se deben inicializar los datos de la base de datos al iniciar la aplicación.
 */
class SqlDeLightManager(
    private val databaseUrl: String,
    private val databaseInMemory: Boolean,
    private val databaseInitData: Boolean,
) {
    /**
     * Las consultas a la base de datos.
     */
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    /**
     * Inicializa el gestor de la base de datos SQLDelight.
     * Se inicializan los datos de ejemplo si está configurado.
     */
    init {
        logger.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
        initialize()
    }

    private fun initQueries(): DatabaseQueries {
        return if (databaseInMemory) {
            logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            logger.debug { "SqlDeLightClient - File: $databaseUrl" }
            JdbcSqliteDriver(databaseUrl)
        }.let { driver ->
            logger.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries
    }

    /**
     * Inicializa los datos de la base de datos si está configurado para hacerlo.
     */
    fun initialize() {
        if (databaseInitData) {
            removeAllData()
            initDataExamples()
        }
    }

    private fun initDataExamples() {
        logger.debug { "Iniciando datos de ejemplo" }
        databaseQueries.transaction {
            demoEstudiante()
        }
    }

    private fun demoEstudiante() {
        logger.debug { "Datos de ejemplo de Estudiante" }
        initDemoEstudiante().forEach {
            databaseQueries.insertEstudiante(
                id = it.id,
                nombre = it.nombre,
                calificacion = it.calificacion
            )
        }
    }

    /**
     * Elimina todos los datos de las tablas de la base de datos.
     */
    private fun removeAllData() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.removeAllEstudiante()
        }
    }
}