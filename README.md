# Estudiantes-Koin

Se trata de usar Koin en el siguiente problema:

Tenemos un modelo de estudiante, con

id autonumérico
nombre
calificación
Queremos un repositorio, con instancia única que implemente las operaciones CRUD. Trabajará con la filosofía de "nulos"

Además, tendremos una caché con uns instancia por demanda de un tamaño determinado. Usará Result como resultado de cada operación.

Finalmente tendremos por composición un Servicio que usará la caché y el repositorio y será creado como nuevo cada vez que se necesite. Además usará Result como resultado de cada operación.

Se usará SQLDelight como manejador de bases de datos SQLite. La base de datos será en memoria.

Tanto la url de la base de datos y el tamaño de la caché se obtendrán del fichero "config.properties" y seráleído con Koin.

Se deberá validar los módulos creados y mostrar ejemplo de ejecución del servicio para las operaciones CRUD.

El código será comentado con Kdoc y Dokka.

Se debe entregar el repositorio con capturas de su funcionamiento

https://insert-koin.io/
https://cashapp.github.io/sqldelight/2.0.2/
