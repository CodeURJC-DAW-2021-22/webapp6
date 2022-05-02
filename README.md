# Padelante

# FASE 0

|**Nombre y Apellidos**|**Correo**|**Usuario Github**|
|:-----------------:|:-----:|:-------------:|
|Rubén Catalán Medina|r.catalan.2018@alumnos.urjc.es|Ruben-urjc|
|Álvaro González Aroas | a.gonzalezar.2018@alumnos.urjc.es|alvaroga2175|
|Daniel Haro Murcia|d.haro.2018@alumnos.urjc.es|DaniURJC|
|Silvia Ventura Cabreja|s.ventura.2018@alumnos.urjc.es|Silvia-vcp|

## Entidades
- **Usuario**. Los usuarios son aquellos tanto registrados como no, que puede hacer uso de la web para formar sus campeonatos de padel, formando pareja con otro usuario.
  - Anónimo
  - Registrado
  - Administrador
- **Pareja**. Unión de dos usuarios registrados.
- **Torneo**. Conjunto de partidos.
- **Partido**. Jugado por 2 Parejas.

## Permisos usuarios
- **Usuario anónimo:** Solo podrá mirar los torneo públicos, información general de la web, reglas de los torneos y registrarse
- **Usuario registrado:** Podrá crear torneos privados, compartirlos con otros jugadores por email (¿o generar código o enlace?), modificar características del torneo que crea, modificar su perfil, ver estadísticas, ver histórico de partidos y parejas, unirse a torneos, añadir resultado a sus partidos, confirmar resultado.
- **Usuario administrador:** Expulsar gente de la aplicación, crear y borrar torneos públicos.

## Imágenes
- **Usuario registrado:** Imagen de perfil. Opcional. Modificable
- **Torneo: Imagen de torneo de fondo:** Defecto. Modificable
- **Pareja: Imagen de pareja:** Opcional. Modificable

## Gráficos
- **Porcentaje de victoria total:** Cúmulo individual. Gráficos de sectores
- **Evolución individual de puntos de karma:** Gráfico lineal

## Tecnología complementaria
- **Enviar email:** Enviar PDFs a los ganadores de torneos.
- **Generar PDF:** Para campeón de torneo. Para descargar estadísticas.

## Algoritmo o consulta avanzada
- **Aleatoriedad de emparejamientos inicial.**
- **Cálculo automático de próximos partidos en un torneo.**
- **Clasificación en competiciones de formato liga.**
- **Karma:** Puntos totales que bajen o suban en función del karma de la pareja contraria.

# FASE 1

## Pantallas
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Login.png" align="center"  width="300"></img>
<p>Un usuario puede hacer Login o Registrarse</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Register.png" width="300"></img>
<p>Al registrarse, introducirá su nombre de usuario, nombre completo, email, contraseña y aceptará los términos y condiciones</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/main.png" width="300"></img>
<p>Desde la página inicial, cualquier usuario podrá ver los torneos públicos, pero solo los registrados podrán crear torneos</p>
<p>En la página inicial se mostrarán inicialmente 6 torneos públicos. Para ver más, habrá que pulsar en "Cargar más"</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Admin.png" width="300"></img>
<p>Un administrador podrá buscar tanto torneos como usuarios y eliminarlos si es necesario. Se mostrarán inicialmente 6 y si quiere ver más tendrá que pulsar en "Cargar más"</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ErrorPage.png" width="300"></img>
<p>La página de error se mostrará cuando haya fallos de conexión con el servidor</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/CreateTournament.png" width="300"></img>
<p>Un usuario registrado podrá crear torneos rellenado un formulario</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/MatchNotification.png" width="300"></img>
<p>Un usuario registrado podrá ver notificaciones referentes a los partidos que tiene que jugar</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Match.png" width="300"></img>
<p>Cualquier jugador que haya jugado un partido podrá añadir un resultado válido</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/UserOptions.png" width="300"></img>
<p>Los usuarios registrados podrán ver su perfil y cerrar sesión</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/UserProfileOverview.png" width="300"></img>
<p>Dentro del perfil se podrán ver los datos generales del usuario registrado</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/UserProfileEdit.png" width="300"></img>
<p>A su vez, se podrá editar sus datos</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/UserProfileTournaments.png" width="300"></img>
<p>Por último, dentro del perfil se podrán ver los torneos en los que el usuario ha jugado o está jugando</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/TournamentOverview.png" width="300"></img>
<p>En la página del torneo se podrá ver toda su información y los participantes. Además el creador del torneo podrá iniciar el torneo desde aquí</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Inscription.png" width="300"></img>
<p>En la página del torneo, un usuario podrá inscribirse añadiendo a su pareja si hay plazas libres y ninguno de los dos ya está apuntado</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/TournamentBracket.png" width="300"></img>
<p>Se podrán ir viendo los cruces de los partidos una vez haya empezado el torneo</p>
<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/TournamentEdit.png" width="300"></img>
<p>El creador del torneo podrá cambiar diferentes ajustes y datos relativos al mismo</p>

## Navegation Map

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/NavegationMap.png" width="1000"></img>

# FASE 2

## Class and Templates Diagram

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ClassDiagram.png" width="1000"></img>

## Database Diagram

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ERDDiagram.png" width="1000"></img>

## Participación de los miembros

  _**Nota:** En los Controller aparece que Rubén ha hecho todo, pero realmente separó en varios Controller lo que teníamos en uno único, por lo que es un copy-paste. Para ver la autoría a grandes rasgos de cada Controller, se debe acceder al fichero previo a esa separación:_

  https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/4ffae254ca09287f931b915cb4e7f4a2c5aef10b/backend/src/main/java/es/webapp6/Padelante/controller/indexController.java


- **Rubén Catalán:**

  - Descripción de tareas:
    
    En lo que respecta a los HTML, me he encargado de la mayor parte de ediciones de HTML referentes al formato en CSS, de la creacion de HTMLs especificos para los import de JS, de añadir los elementos necesarios en Mustache para mostrar los datos que iba creando en los Controllers y de los AJAX en la pantalla de admin, perfil del usuario y usuarios a la hora de inscribirse en un torneo.
    
    En lo referente a java, me he encargado de toda la lógica sobre creación, bracket e inscripción de los torneos, así como la creación de equipos al inscribirse, dar como ganador a un equipo, pasar a un equipo de ronda, el acceso de los usuarios a sus partidos correspondientes y el almacenamiento de datos de los usuarios tras los partidos como sus victorias, resultados, sets, ... Además, he hecho la mayor parte de las llamadas JPQL en los repositorios, la migración de H2 a PosgreSQL, el correcto tratamiento de los datos del tipo Date de los formularios y pequeños arreglos cuando era necesario.

    Por último, me he encargado de actualizar las fotos de las pantallas y el Navegation Map de la fase anterior.

  - Commits más significativos:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/9a3273eb9ea11614d7ec4872153c232e33b1437d
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/c44ca988c1b7dea1a1ae283dd72013966ce48a35
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/c740379c83d0dc78099161788416a1fa963987f8
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/8a3e48d255f236ce9004d8f4b8f72572746bd0b8
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/c7b47b6682e35d8e3370323d0d20f056c617e240

  - Ficheros más relevantes:
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/service/TournamentService.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/service/MatchService.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/service/TeamService.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/model/Match.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/model/Team.java

- **Álvaro González:**

  - Descripción de tareas:
  
    En los HTML, me he encargado de incorporar la notación Mustache en aquellos sitios en los que correspondía. También, me he encargado del procesado de imágenes, así como de las tareas relaccionadas con dichas imágenes como puede ser la actualización, borrado o subida de imágenes. Por otro lado, también me he encargado de controlar la validez de los resultados que los usuarios metían en los partidos y de los formularios de actualización de datos de torneos y usuarios. A su vez, me encargué de la implementación de las páginas de error.
    
    En cuanto a los gráficos, me he encargado del correspondiente al número de victorias y derrotas, haciendo para ello uso de JavaScript. Por último, he sido el encargado de implementar la generación de un PDF para aquellos usuarios que participasen en un torneo.
    
  - Commits más significativos:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/e06fdb77ea31102b529522eab15f2ff1fb051315
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/99a520d2b024a0697d17eee668651eec91dd4a84
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/b0cf10a29e90605d3384470bdad9924490956e4f
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/1f0aaf2da6dd963b1cdb7755f37f2d5d4028d398
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/575b0c2690ebaa10f91e9aaf79bb8271ffb1e5d6
      
  - Ficheros más relevantes:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/controller/errorController.java
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/model/User.java
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/service/UserService.java
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/resources/static/assets/js/generatePDF.js
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/model/Tournament.java

- **Daniel Haro:**

  - Descripción de tareas:
    
    A nivel de Backend, implementé las clases iniciales de Torneo, Equipo y Partido, así como sus repositorios con las funcionalidades básicas. Implementé la clase que genera los datos de ejemplo para poder probar la aplicación. Fui, a su vez, responsable de la creación del algoritmo de puntuación de jugadores, conocido como karma, que se va modificando en función del nivel de los rivales contra los que se pierde o gana, así como del número de partidos que un jugador lleva. Asociado a la lógica del karma, también me encargue de la visualización del histórico del karma en el perfil del usuario.    

  - Commits más significativos:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/548c3f9c2bb47cc57722cca0efe65bae961ac088
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/088e8f5f8ff56871f6f2e414cdfe5673266c59ed?diff=split
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/70e61f9724bc1cbf6699a5fd6c2335134b3a54b1
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/56377530a30d09893822509a8aeb60c2776cafa4
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/b45d8f1311fd861a9c51628136ded2524a95344c

  - Ficheros más relevantes:

    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/Padelante/service/MatchService.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/Padelante/ExamplesGenerator.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/Padelante/service/UserService.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/resources/templates/match.html
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/resources/static/assets/js/karma.js

- **Silvia Ventura:**

  - Descripción de tareas:
 
    Me he encargado de la parte inicial de seguridad de la aplicación, creando todos los ficheros necesarios y modificando lo necesario. En los HTML, he implementado AJAX en la parte de mostrar mis torneos en la página de usuario y la de mostrar los torneos en la página de admin.
    
    En lo que respecta a los diagramas, he hecho el diagrama con las entidades de la base datos y el diagrama de clases y templates.

  - Commits más significativos:
  
     https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/51bc98d927cbc98eec443a711408ac56b654ab91
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/e86ebebd7ce58cec31a28554cbea1e62b200b9be
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/0b60d98071603ac9096c06050bf869853d8cc8c2
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/941d340390899c1e38c65a7dcf0d2af7462a0802
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/305221ad6bcee372f3cc1245d78fc689b29a2430

  - Ficheros más relevantes:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/Padelante/security/CSRFHandlerConfiguration.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/Padelante/security/RepositoryUserDetailsService.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/Padelante/security/WebSecurityConfig.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/Padelante/service/TournamentService.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/Padelante/controller/indexController.java
    
## Intrucciones de ejecución

  Para la aplicación se usa:
  - Java ```17```
  - PostgreSQL ```14.2``` 
  - SpringBoot ```2.6.3```

  Para clonar el repositorio de git, se debe de usar la siguiente sentencia:
  
  ```$ git clone https://github.com/CodeURJC-DAW-2021-22/webapp6```

  Por defecto, el proyecto require una base de datos PostgreSQL disponible en localhost con la siguiente configuración:
  - Esquema: ```padelante ```
  - Usuario: ```postgres ```
  - Contaseña:```password ```
  
  Se puede arrancar usando docker con el comando:
  
  ```$ docker run --rm -e POSTGRES_PASSWORD=password -e POSTGRES_DB=padelante -p 5432:5432 -d postgres:14.2```
  
  La aplicación se ejecuta con el comando:
  
  ```mvn spring-boot:run```  
  
  Los usuarios principales son:
  - Usuario: ```user```, Contraseña: ```pass```
  - Usuario: ```admin```, Contraseña: ```adminpass```

# FASE 3

## URL Heroku

https://codeurjc-daw-2021-22-webapp6.herokuapp.com/

Los usuarios principales son:
  - Usuario: ```user```, Contraseña: ```pass```
  - Usuario: ```admin```, Contraseña: ```adminpass```

## Documentación API REST

- Fichero OpenAPI YAML:

https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/api-docs/api-docs.yaml

- Fichero OpenAPI HTML:

https://rawcdn.githack.com/CodeURJC-DAW-2021-22/webapp6/c908eef6aae7103bd57f908b9c72c84c37ee0d50/backend/api-docs/api-docs.html

## Diagrama de clases con REST Controllers

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/ClassDiagramUpdate.png" width="1000"></img>
## Ejecución de la aplicación dockerizada

  Para la ejecución del archivo ```docker-compose.yml``` se usa:
  - Docker
  - Docker Compose
  - Maven ```3.8.5```

  Se deberá haber clonado el repositorio de git previamente usando la siguiente sentencia:
  
  ```$ git clone https://github.com/CodeURJC-DAW-2021-22/webapp6```

  El usuario deberá ejecutar las siguientes sentencias en el terminal después de haberse situado con la sentencia ```cd``` en la carpeta del proyecto ```webapp6```:
  
  ```$ cd ./backend/docker```
  
  Por último, se deberá ejecutar el siguiente comando:
  
  ```$ docker-compose up```
  
  Cuando se termine de ejecutar la sentencia, el usuario debería poder acceder a la aplicación desde la URL:
  
  https://localhost:8443/
  
  __Nota:__ El archivo ```docker-compose.yml``` está configurado con la imagen creada con el usuario de Docker ```rubencatalan```. Si ha creado su propia imagen como se dice en el siguiente punto, se deberá cambiar en la linea 4 de ese archivo el nombre anterior por el nombre de usuario de Docker que se haya puesto en la nueva imagen.

## Construcción de la imagen docker

  Para la construcción de la imagen docker se usa:
  - Docker
  - Maven ```3.8.5```
  - Usuario registrado en Docker Hub

  Se deberá haber clonado el repositorio de git previamente usando la siguiente sentencia:
  
  ```$ git clone https://github.com/CodeURJC-DAW-2021-22/webapp6```
  
  El usuario deberá ejecutar las siguientes sentencias en el terminal después de haberse situado con la sentencia ```cd``` en la carpeta del proyecto ```webapp6```:
  
  ```$ cd ./backend/docker```
  
  ```$ ./create_image.sh <usuario_docker>```
  
  donde ```<usuario_docker>``` es el nombre de su usuario en Docker Hub
  
  De esta forma, se habrá creado la imagen docker y se habrá publicado en Docker Hub desde su usuario.
  
## Despliegue en Heroku

Para el despliegue se usa:
- Docker
- Java ```17.0.2```
- Maven ```3.8.5```
- SpringBoot ```2.6.3```
- Heroku Client
- Usuario registrado en Heroku

Se deberá haber clonado el repositorio de git previamente usando la siguiente sentencia:
  
```$ git clone https://github.com/CodeURJC-DAW-2021-22/webapp6```
  
El usuario deberá ejecutar las siguientes sentencias en el terminal después de haberse situado con la sentencia ```cd``` en la carpeta del proyecto ```webapp6```:

```$ cd ./backend```
  
```$ heroku login```

e introducir las credenciales del usuario en Heroku.

```$ heroku create <app-id>```

donde ```<app-id>``` es la id que se le quiera asignar a la aplicación. Esta id no debe existir previamente en Heroku para funcionar. En nuestro caso se usó ```codeurjc-daw-2021-22-webapp6```.

Se genera la imagen docker para Heroku con:

```$ mvn spring-boot:build-image -Dspring-boot.build-image.imageName=registry.heroku.com/<app-id>/web```

Se publica en el registro de Heroku:

```$ heroku container:login```

```$ docker push registry.heroku.com/<app-id>/web```

Se despliega la aplicación:

```$ heroku container:release web --app <app-id>```

Por último, para poder configurar la base de datos PosgreSQL en Heroku y terminar de configurar la aplicación hacemos lo siguiente:

```$ heroku config:set SERVER_SSL_ENABLED=false --app <app-id>```

```$ heroku config:set SPRING_JPA_HIBERNATE_DDL-AUTO=update --app <app-id>```

```$ heroku addons:create heroku-postgresql --app <app-id>```

```$ mvn spring-boot:build-image -Dspring-boot.build-image.imageName=registry.heroku.com/<app-id>/web```

```$ docker push registry.heroku.com/<app-id>/web```

```$ heroku container:release web --app <app-id>```

Y ya estaría desplegada la aplicación en Heroku y estará accesible a traves de la URL:

```https://<app-id>.herokuapp.com/```

## Participación de los miembros

- **Rubén Catalán:** 

  - Descripción de tareas:
    
    Me he encargado con la ayuda de Álvaro de todo lo relacionado con Docker, Docker Compose y Heroku. Posteriormente, me he encargado de toda la documentación relativa a lo anteriormente mencionado y del script correspondiente.
    Por otra parte, me he encargado de crear distintas API REST, implementar la paginación en estas, de ampliar los distintos modos de estado de las peticiones y de limitar la ejecución de las API REST para usuarios no registrados, registrados y admin en función de cada una.
    
  - Commits más significativos:
     
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/8f96cac2b95b02889c6074ef69c7ec046fb16299
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/4f651bebfdd36bf82bf503e9990ac66df5d93c4a
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/86c47a8150c75222520a674eccc6b23e6eb9ee2d
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/3e89900b11a6bd67927d1dfad7c63d15f6efe23b
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/524560e96e62abc9726ff1d06ca21c22a28e9482
    
  - Ficheros más relevantes:
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/docker/Dockerfile
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/docker/docker-compose.yml
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/padelante/controller/TournamentRestController.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commits/main/backend/src/main/java/es/webapp6/padelante/controller/UserRestController.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/padelante/security/RestSecurityConfig.java
  
  
- **Álvaro González:** 

  - Descripción de tareas:
  
    Me he encargado de implementar parte de las apis Rest, así como de ayudar a Rubén con Docker y heroku. Posteriormente, me he encargado de incorporar el tema de la    seguridad para las api Rest.
    Por otra parte, me he encargardo de algunas de las peticiones de Postman, asi como del fichero .json de las peticiones de Postman.
    
  - Commits más significativos:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/a65c1b51de9de4ae9e59c5bdd4bb2eef7f800f38

    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/8c0473fd80da3c8f364ec723ab98cebdcbf4a821

    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/128457ebc4dfe54ba7756910c6a29753ffd232f8 

    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/4d7b2dedbf34956068658353db3b418011c40dcd

    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/0b16aefd80632a66774360e424c567316ed4ca6c 
    
  - Ficheros más relevantes:
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/padelante/controller/LoginRestController.java

    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/padelante/controller/MatchRestController.java

    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/padelante/controller/UserRestController.java

    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/padelante/controller/TournamentRestController.java

    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/padelante/security/RestSecurityConfig.java
 
 
- **Daniel Haro:** 

  - Descripción de tareas:
  
    Me he encargado principalmente de documentar las API REST en todas las clases necesarias, así como de su análisis y alguna corrección menor. Por otro lado he analizado las peticiones con Postman a diferentes endpoints, comprobando que devolvían la respuesta esperada.
    
  - Commits más significativos:
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/050346013813d14cd161b6de64719c1ab80b6df3
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/884345ad04e5f27af24d916c13e4198003c274f8
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/d6a0bb99ba52af905772bcb617e7e951ec38a4e4
    
  - Ficheros más relevantes:

    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/controller/LoginRestController.java
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/controller/MatchRestController.java
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/controller/TournamentRestController.java
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/controller/TournamentRestController.java
  
  
- **Silvia Ventura:** 

  - Descripción de tareas: 
  
    Me he encargado de cambiar el diagrama y actualizarlo. También me he encargado de ocultar los datos sensibles que no debrían verse al hacer una petición para los     Users y Tournaments
    
  - Commits más significativos:
  
     https://github.com/CodeURJC-DAW-2021-22/webapp6/tree/2eabb8a5567961f3f22d4cf7a69d21870786e6b8
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/tree/80c1d1101fdfe8b9111167b7b83bd8983a483184
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/tree/c6f7fb4532d42a8ea840cd15864fd06ee24571f6
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/tree/4ed6da39fba4f52a997466c8ae98351c89295ea0
     
     https://github.com/CodeURJC-DAW-2021-22/webapp6/tree/c2142a2cbba41af0f21f3c83868933ac19eaea99
    
  - Ficheros más relevantes:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/controller/PageImplJacksonSerializer.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/controller/TournamentRestController.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/controller/UserRestController.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/model/User.java
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/backend/src/main/java/es/webapp6/padelante/model/Team.java
    
    
# FASE 4

## Preparación del entorno de desarrollo

  Para esta fase se usa:
  - Node.js
  - Angular CLI ```13.3.1```
  - Maven ```3.8.5```
  - Docker
  - Usuario registrado en Docker Hub
  - Docker Compose

  __Nota:__ Esta preparación está pensada para que el archivo ```docker-compose.yml``` esté configurado con la imagen creada con el usuario de Docker ```rubencatalan```. Se deberá cambiar en la linea 4 de ese archivo el nombre anterior por el nombre de usuario de Docker que se vaya a utilizar a continuación.

  Se deberá haber clonado el repositorio de git previamente usando la siguiente sentencia:
  
  ```$ git clone https://github.com/CodeURJC-DAW-2021-22/webapp6```
  
  El usuario deberá ejecutar las siguientes sentencias en el terminal después de haberse situado con la sentencia ```cd``` en la carpeta del proyecto ```webapp6```:
  
  ```$ cd ./backend/docker```
  
  ```$ ./create_image.sh <usuario_docker>```
  
  donde ```<usuario_docker>``` es el nombre de su usuario en Docker Hub. De esta forma se habrá creado la imagen Docker correspondiente.
  
  Por último, deberá ejecutar:
  
  ```$ docker-compose up```
  
  Cuando se termine de ejecutar la sentencia, el usuario debería poder acceder a la web con arquitectura tradicional (MVC) desde la URL:
  
  https://localhost:8443/
  
  y a la web SPA desde la URL:
  
  https://localhost:8443/new/
  
## Diagrama de clases y templates de la SPA

<img src="https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/PadelantePhotos/Diagrams phase 4.png" width="1000"></img>

## Participación de los miembros

- **Rubén Catalán:**

  - Descripción de tareas: 
  
    Me he encargado de los componentes header, main, tournament y match principalmente, además de implementar sus funcionalidades y todos los AJAX de la aplicación en TypeScript. Por otra parte, he contribuido en la modularización de código similar y de "mapear" las respuestas y errores de las API en los servicios. Por último, he modificado el script para dockerizar la aplicación, la he publicado en Heroku y he desarrollado la documentación sobre la preparación del entorno de desarrollo.
    
  - Commits más significativos:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/821b6a829a1396f29d5ef5c4117b928ad39d0e5e
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/184d9ee3a43935a66b16573aff62d9a1ce7a9eb2
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/a81a2edd23a191a376ef8e67dcd56aa40c7e54dc
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/673bc8f9b77e445a64e92dcaee44290b7b3f2cfe
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/51c60d542b5d7ed9b8dee78e5a89c20c11c8130e
    
  - Ficheros más relevantes:
   
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/frontend/src/app/components/tournament/tournament.component.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/frontend/src/app/components/match/match.component.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/frontend/src/app/services/tournament.service.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/frontend/src/app/services/user.service.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/frontend/src/app/services/match.service.ts

- **Álvaro González:**  

  - Descripción de tareas: 
  
    Me he encargado de lo que viene a ser la generación del pdf, y de las amiganes. Además de la preparacion de los formularios de registro de nuevos usuarios y creación de torneos. También me he encargado del perfil del usuario. Por otro lado, he intentado ayudar en todo lo que he podido a mis compañeros.
    
    
  - Commits más significativos:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/e64b4e140dc9218e7c4d9cfe5dd495ba0874fa7b
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/8d453af0b21013b05de5ca6fe44a17f3004c006f
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/33e1be206ff55c6c6d2dd7f2b1eb178e97ff2934
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/630b1b8c05ed83fa78ee930715a3ad7de9c9abaf
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/c7b810803901ecbb4157fc895cbb0f23a15512e3
     
  - Ficheros más relevantes:

    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/frontend/src/app/components/admin/admin.component.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/frontend/src/app/components/register/register.component.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/frontend/src/app/components/userProfile/userProfile.component.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/frontend/src/app/models/user.model.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/frontend/src/app/services/user.service.ts
  
- **Daniel Haro:**  

  - Descripción de tareas: 
  
  Me he encargado principalmente de la edición de las imágenes (subida y borrado), así como de poder dar de baja usuarios y torneos de la aplicación. 
    
    
    
  - Commits más significativos:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/4565d0be8dcc972df46dd5d0f93ffd3b0b8b4c1e
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/ba5bac2e2f90bcde34e2d5bf51f8786b783d475d
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/a43ddf1cddd14e357e562269007d29447af43eef
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/0aaf7fd02f516a040c410a052d2d83cc7ac866b2
     
    
    
  - Ficheros más relevantes:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/backend/src/main/java/es/webapp6/padelante/controller/UserRestController.java
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/frontend/src/app/components/admin/admin.component.ts
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/frontend/src/app/components/userProfile/userProfile.component.html
    
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blame/main/frontend/src/app/services/tournament.service.ts
    
    


- **Silvia Ventura:** 

  - Descripción de tareas: 
    Me he encargado de hacer la gráfica de las derrotas y vistorias del usuario y del karma a lo largo del tiempo. También me he encargado de hacer los diagramas que se necesitan para esta iteración
    
  - Commits más significativos:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/12c5bd794d0b31def1f3848aeb348b80d299f802
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/dee397e92159a0446c17cfb13fa60201a9f1cd08
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/fa5ba30d59157d764ff48b5071c45b620642f826
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/19b585be3a3d97c0ae21ca9b6a4a66337b391c70
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/commit/27f95aaa975cafe61085ddac60ecc5b194380a06
  
  - Ficheros más relevantes:
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/tree/main/frontend/src/app/components/doughnutchart
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/tree/main/frontend/src/app/components/linechart
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/frontend/src/app/models/team.model.ts
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/frontend/src/app/models/match.model.ts
  
    https://github.com/CodeURJC-DAW-2021-22/webapp6/blob/main/frontend/src/app/appconfig.ts
    
## Vídeo

https://www.youtube.com/watch?v=5Bwm3sY_KyU
