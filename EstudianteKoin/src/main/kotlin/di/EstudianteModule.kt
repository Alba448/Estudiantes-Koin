package org.example.di

import org.example.Estudiante.controllers.EstudianteController
import org.example.Estudiante.repositories.EstudianteRepository
import org.example.Estudiante.repositories.EstudianteRepositoryImpl
import org.example.Estudiante.services.EstudianteService
import org.example.Estudiante.services.EstudianteServiceImpl
import org.example.config.Config
import org.example.database.service.SqlDeLightManager
import org.koin.core.qualifier.named
import org.koin.dsl.module
/**
 * Módulo de inyección de dependencias para la gestión de estudiantes.
 */
val EstudianteModule = module {
    /**
     * Obtiene la configuración de la aplicación.
     * @return Instancia de [Config] con la configuración de la aplicación.
     */
    fun getConfig(): Config {
        return Config()
    }

    /**
     * Obtiene el gestor de base de datos SQLDelight.
     * @return Instancia de [SqlDeLightManager] para la gestión de la base de datos.
     */
    fun getSqlDeligManager(): SqlDeLightManager {
        return SqlDeLightManager(
            databaseUrl = getConfig().databaseUrl,
            databaseInMemory = getConfig().databaseInMemory,
            databaseInitData = getConfig().databaseInitData
        )
    }

    /**
     * Define una única instancia de [EstudianteServiceImpl] como servicio de almacenamiento en base de datos.
     */
    single<EstudianteService>(named("DataBaseStorage")) { EstudianteServiceImpl() }

    /**
     * Define una única instancia de [EstudianteServiceImpl] como servicio de almacenamiento.
     */
    single<EstudianteService> { EstudianteServiceImpl() }

    /**
     * Define una única instancia de [EstudianteRepositoryImpl] como repositorio de base de datos.
     */
    single<EstudianteRepository>(named("DataBaseRepository")) { EstudianteRepositoryImpl(get(named("DataBaseStorage"))) }

    /**
     * Define una única instancia de [EstudianteRepositoryImpl] como repositorio de archivos.
     */
    single<EstudianteRepository>(named("FileRepository")) { EstudianteRepositoryImpl(get(named("FileStorage"))) }

    /**
     * Define una fábrica de [EstudianteRepositoryImpl] para la creación de instancias de repositorio.
     */
    factory<EstudianteRepositoryImpl> { EstudianteRepositoryImpl(get()) }

    /**
     * Define una única instancia de [EstudianteController] para la gestión de estudiantes en la base de datos.
     */
    single(named("EstudianteDataBaseController")) {
        EstudianteController(
            get(named("DataBaseRepository")),
            getProperty("server_url")
        )
    }

    /**
     * Define una única instancia de [EstudianteController] para la gestión de estudiantes en archivos.
     */
    single(named("EstudianteFileController")) {
        EstudianteController(
            get(named("FileRepository")),
            getProperty("server_url")
        )
    }

    /**
     * Define una única instancia de [EstudianteController] para la gestión de estudiantes, utilizando el repositorio
     * y la URL del servidor especificados en las propiedades.
     */
    single { EstudianteController(get(), getProperty("server_url", "no_encontrada")) }

}