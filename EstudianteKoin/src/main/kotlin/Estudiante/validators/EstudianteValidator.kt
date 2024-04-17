package org.example.Estudiante.validators
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.example.Estudiante.errors.EstudianteError
import org.example.Estudiante.models.Estudiante

/**
 * Clase encargada de validar instancias de la clase Estudiante.
 */
class EstudianteValidator {
    /**
     * Valida un estudiante.
     * @param estudiante El estudiante a validar.
     * @return Un resultado que contiene el estudiante validado si la validación tiene éxito, o un error si falla.
     */
    fun validate(estudiante: Estudiante): Result<Estudiante, EstudianteError> {
        return when {
            estudiante.nombre.isBlank() -> Err(EstudianteError.EstudianteNoValido("El nombre no puede estar vacío"))
            estudiante.calificacion <= 0 -> Err(EstudianteError.EstudianteNoValido("La calificación no puede ser menor o igual a 0"))
            else -> Ok(estudiante)
        }
    }
}