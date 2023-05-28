# ProcesoPedido

Este proyecto es un crud que permite Listar, Crear, actualizar y eliminar. los datos registrados en BD interactuando con la parte front realizada en PrimeFaces

## Configuración de la conexión a la base de datos

El proyecto utiliza una base de datos Oracle para almacenar los datos. Para establecer la conexión a la base de datos

### Controlador

- Javax.faces
- Jstl
- Jdbc8
- primeFaces

### Detalles de conexión

Se utilizan los siguientes detalles de conexión para establecer la conexión con la base de datos Oracle:

- URL: `jdbc:oracle:thin:@localhost:1521:XE`
- Usuario: `your_username`
- Contraseña: `your_password`
- Controlador: `oracle.jdbc.OracleDriver`
