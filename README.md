# TOPICOS AVANZADOS DE SOFTWARE - TALLER # 3

## Participantes
- Alejandro Realpe
- Anderson Aguiar
- Mateo Echeverri

## Requisitos previos

- Java 17
- Gradle
- Postman

## Dependencias Gradle
El proyecto utiliza las siguientes dependencias en Gradle:

- [Rest Assured](https://rest-assured.io/) 
- [Selenium WebDriver](https://www.selenium.dev/documentation/)

## Reto 1
Este proyecto, desarrollado en Java 17 y utilizando Rest Assured, tiene como finalidad automatizar la validación del siguiente proceso:

1. Registrar un nuevo usuario a través de la API disponible en [DemoQA Swagger](https://demoqa.com/swagger/#/).
2. Confirmar que el usuario ha sido creado exitosamente mediante una verificación con `assert`.
3. Acceder con el usuario recién registrado en la página de [DemoQA Login](https://demoqa.com/login).
4. Tras autenticarse, proceder a eliminar el usuario desde la sección de perfil.
5. Intentar iniciar sesión de nuevo con el mismo usuario y comprobar que aparece el mensaje de error correspondiente, lo que indica que el usuario ha sido eliminado satisfactoriamente.

## Reto 2
El reto 2 consiste en seleccional un API de la página de la [NASA](https://api.nasa.gov/) y seleccionar 2 API docuemntadas en el sitio con el fin de realizar el consumo de las mismas.

En el repositorio, en la carpeta _Reto2_Postman_ se encuentran los siguientes archivos:

- _Taller3Topicos_Reto2.postman_collection.json_: Collection con cada una de los request implementados.
- _workspace.postman_globals.json_: archivo que contiene toda la definición de variables globales utilizadas en el reto.
- _EvidenciasTaller3_Postman.pdf_: Documento que describe el proceso realizado y todas las evidencias de ejecución en Postman.

Se realizaron 4 request en total, 2 para el API TechPort y 2 para el API Asteroids – NeoWs. Los request fueron los siguientes:

TechPort:
_Get projects_: request que me entrega los proyectos creados y finalizados de la NASA. Se debe parametrizar una fecha de inicio, la cual se tomará desde una variable global.
_Project information_: request que me entrega la información detallada del proyecto proporcionando un ID de proyecto.

Asteroids – NeoWs
_Neo feed_: request que recupera una lista de asteroides en función de su fecha de aproximación más cercana a la Tierra.
_Neo Lookup_: request que permite conocer la información específica de un asteroide según el ID de la NASA.