package org.example

import org.example.Estudiante.controllers.EstudianteController
import org.example.Estudiante.models.Estudiante
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

/**
 * Clase principal de la aplicación Estudiante.
 * Esta clase utiliza inyección de dependencias con Koin para obtener instancias de controladores de estudiantes.
 */
class EstudianteApp : KoinComponent {
    /**
     * Controlador de estudiantes para el almacenamiento en base de datos.
     */
    val contRepoStorageBD: EstudianteController by inject(named("EstudianteDataBaseController"))

    /**
     * Controlador de estudiantes para el almacenamiento en archivo.
     */
    val contRepoStorageFile: EstudianteController by inject(named("EstudianteFileController"))

    /**
     * Controlador de estudiantes por defecto.
     */
    val contPorDefecto: EstudianteController by inject()

    /**
     * Método principal para ejecutar la aplicación.
     * Imprime información sobre la ejecución de la aplicación y realiza operaciones CRUD en diferentes
     * contextos de almacenamiento utilizando los controladores de estudiantes obtenidos mediante inyección de dependencias.
     */
    fun run() {
        println("Estudiante: Model->Controller->Repository->Storage(Database|File)")
        println("===============================================================")
        val p = Estudiante(id = 1, nombre = "Juan", calificacion = 4.8)
        println(p)
        println()

        println("Estudiante: Model->Controller->Repository->Storage(Database)")
        println(contRepoStorageBD)
        var resBD = contRepoStorageBD.save(p)
        println("Resultado BD: $resBD")
        println()

        println("Estudiante: Model->Controller->Repository->Storage(File)")
        println(contRepoStorageFile)
        resBD = contRepoStorageFile.save(p)
        println("Estudiante File: $resBD")
        println()

        println("Estudiante: Model->Controller->Repository->Storage(Por Defecto)")
        println(contPorDefecto)
        val res = contPorDefecto.save(p)
        println("Resultado Por Defecto: $res")
        println()
    }
}