package org.example.Estudiante.errors

/**
 * Clase sellada que representa errores relacionados con estudiantes.
 * @property message El mensaje descriptivo del error.
 * @constructor Crea un nuevo objeto [EstudianteError] con el mensaje de error especificado.
 */
sealed class EstudianteError(val message: String) {
    /**
     * Error que indica que el estudiante no ha sido encontrado.
     * @param message El mensaje descriptivo del error.
     * @constructor Crea un nuevo objeto [EstudianteNoEncontrado] con el mensaje de error especificado.
     */
    class EstudianteNoEncontrado(message: String) : EstudianteError(message)

    /**
     * Error que indica que el estudiante no es válido.
     * @param message El mensaje descriptivo del error.
     * @constructor Crea un nuevo objeto [EstudianteNoValido] con el mensaje de error especificado.
     */
    class EstudianteNoValido(message: String) : EstudianteError(message)

    /**
     * Error que indica que no se pudo actualizar el estudiante.
     * @param message El mensaje descriptivo del error.
     * @constructor Crea un nuevo objeto [EstudianteNoActualizado] con el mensaje de error especificado.
     */
    class EstudianteNoActualizado(message: String) : EstudianteError(message)

    /**
     * Error que indica que no se pudo eliminar el estudiante.
     * @param message El mensaje descriptivo del error.
     * @constructor Crea un nuevo objeto [EstudianteNoEliminado] con el mensaje de error especificado.
     */
    class EstudianteNoEliminado(message: String) : EstudianteError(message)
}