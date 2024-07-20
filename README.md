# Servicio REST usando springboot, JPA, base de datos H2

##Motivación

Este proyecto es un ejemplo de micro servicio que consulta una REST API de un tercero
y almacena la respuesta en una BD para presentar información histórica así como
presentar información cuando el servicio del tercero no se encuentre disponible.

La DB H2 se crea al iniciar el proyecto, y es accesible a través de la url 
http://localhost:8092/h2-console/ usando la contraseña "password". Los datos iniciales
se cargan automáticamente a la tabla city_weather.

Los script de construcción de la base de datos así como inserción de datos iniciales
forma parte del proyecto y se encuentran en los archivos src\main\resources\schema.sql 
y src\main\resources\data.sql.

##Instalación


##Ejecución


## API de los servicios disponibles.
