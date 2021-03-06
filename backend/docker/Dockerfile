#################################################
# Imagen base para el contenedor de compilación
#################################################
FROM maven:3.8.4-openjdk-17 as builder

# Define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

# Copia las dependencias del proyecto
COPY pom.xml /project/

# Descarga las dependencias del proyecto
RUN mvn clean verify --fail-never

# Copia el código del proyecto
COPY src/main/resources /project/src/main/resources
COPY src/main/java /project/src/main/java

# Compila proyecto
RUN mvn package -o -DskipTests=true

#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM openjdk:17.0.2

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/app/

# Copia el JAR del contenedor de compilación
COPY --from=builder /project/target/*.jar /usr/app/

# Indica el puerto que expone el contenedor
EXPOSE 8443

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "padelante-0.0.1-SNAPSHOT.jar" ]
