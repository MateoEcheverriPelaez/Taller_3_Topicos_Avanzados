# TOPICOS AVANZADOS DE SOFTWARE - TALLER # 3

Este proyecto, desarrollado en Java 17, tiene como finalidad automatizar la validación del siguiente proceso:

1. Registrar un nuevo usuario a través de la API disponible en [DemoQA Swagger](https://demoqa.com/swagger/#/).
2. Confirmar que el usuario ha sido creado exitosamente mediante una verificación con `assert`.
3. Acceder con el usuario recién registrado en la página de [DemoQA Login](https://demoqa.com/login).
4. Tras autenticarse, proceder a eliminar el usuario desde la sección de perfil.
5. Intentar iniciar sesión de nuevo con el mismo usuario y comprobar que aparece el mensaje de error correspondiente, lo que indica que el usuario ha sido eliminado satisfactoriamente.

## Participantes
- Alejandro Realpe
- Anderson Aguilar
- Mateo Echeverri

## Requisitos previos

- Java 17
- Gradle

## Dependencias
El proyecto utiliza las siguientes dependencias en Gradle:

- [Rest Assured](https://rest-assured.io/) 
- [Selenium WebDriver](https://www.selenium.dev/documentation/) 