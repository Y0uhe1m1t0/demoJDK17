# Servicio REST usando springboot, JPA, base de datos H2

##Motivación

Este proyecto es un ejemplo de micro servicio que consulta una REST API de un tercero
y almacena la respuesta en una BD para presentar información histórica así como
presentar información cuando el servicio del tercero no se encuentre disponible.

La DB H2 se crea al ejecutar el proyecto, y es accesible a través de la url 
http://localhost:9090/h2-console/ usando los siguientes parametros:

> JDBC URL: 
>> jdbc:h2:mem:testdb

> User Name:
>> sa

> Password:
>> password

Los datos iniciales se cargan automáticamente a la tabla city_weather.

Los script de construcción de la base de datos así como inserción de datos iniciales
forma parte del proyecto y se encuentran en los archivos src\main\resources\schema.sql 
y src\main\resources\data.sql.

##Compilación

En la ruta raíz del proyecto, ejecutar el comando: mvn clean package

##Creación y ejecución del contenedor

En la raíz del proyecto, ejecutar el comando: docker-compose up --build

## API de los servicios disponibles

> Obtener el clima de una ciudad:
>
>> `http://localhost:9090/api/v2/cityWeather/{city}`
>>
>>> Ejemplo: `http://localhost:9090/api/v2/cityWeather/Cali`

> Obtener las ciudades consultadas:
>
>> `http://localhost:9090/api/v2/getHistory`
