package org.example


import org.example.di.EstudianteModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.GlobalContext.startKoin
import org.koin.fileProperties
import org.koin.test.verify.verify


fun main() {
    /**
     * Función principal de la aplicación Estudiante.
     * Esta función inicializa Koin para la inyección de dependencias, carga las propiedades desde un archivo de configuración
     * y ejecuta la aplicación Estudiante.
     */
    @OptIn(KoinExperimentalAPI::class)
    fun main() {
        println("Hola Koin")
        // Inicializamos Koin
        startKoin {
            // Declarar el logger utilizado
            printLogger()
            // Leemos las propiedades desde un archivo
            fileProperties("/config.properties") // Por defecto busca en src/main/resources/koin.properties, pero puede ser otro archivo si se lo pasa como parámetro
            // Declarar módulos de inyección de dependencias, pero se verifica antes de inyectarlos
            EstudianteModule.verify()
            modules(EstudianteModule)
        }

        // Crear una instancia de la aplicación Estudiante y ejecutarla
        val app = EstudianteApp()
        app.run()
    }
}