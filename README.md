# MedAlert - COM 437

## Descripción del proyecto

MedAlert es una aplicación Android desarrollada como proyecto académico para COM 437: Desarrollo de aplicaciones móviles de Saint Leo University.

Su propósito es facilitar la organización personal de medicamentos mediante una interfaz sencilla que permite registrar, consultar, modificar y eliminar información básica relacionada con los tratamientos del usuario.

La aplicación también incorpora una ficha de emergencia para almacenar información básica que puede resultar útil en una situación de atención médica.

## Problema identificado

Las personas que utilizan varios medicamentos pueden tener dificultades para recordar nombres, dosis y horarios. Asimismo, información como alergias, antecedentes médicos y datos de contacto puede no encontrarse disponible rápidamente durante una emergencia.

MedAlert busca centralizar esta información en una aplicación móvil sencilla y accesible.

## Objetivo general

Desarrollar una aplicación Android funcional que permita gestionar localmente información personal sobre medicamentos y mantener una ficha básica de emergencia.

## Funcionalidades implementadas

La versión actual de MedAlert incluye:

- Pantalla principal de navegación.
- Registro de medicamentos.
- Consulta de medicamentos registrados.
- Modificación de medicamentos.
- Eliminación de medicamentos.
- Almacenamiento local de la información.
- Visualización del número de medicamentos registrados.
- Ficha de emergencia.
- Registro de alergias.
- Registro de antecedentes.
- Registro de contacto de emergencia.
- Interfaz desarrollada con Jetpack Compose.
- Navegación entre las principales funciones de la aplicación.

## CRUD de medicamentos

MedAlert implementa las operaciones fundamentales de un sistema CRUD:

- **Create:** agregar un medicamento.
- **Read:** consultar los medicamentos almacenados.
- **Update:** modificar la información de un medicamento.
- **Delete:** eliminar un medicamento.

La información se mantiene localmente en el dispositivo.

## Ficha de emergencia

La aplicación dispone de una sección independiente para almacenar información básica de emergencia:

- Alergias.
- Antecedentes.
- Contacto de emergencia.

Esta funcionalidad complementa la gestión de medicamentos sin requerir servicios externos.

## Arquitectura actual

MedAlert utiliza una arquitectura sencilla adecuada al alcance académico del proyecto.

La aplicación está desarrollada en Kotlin y utiliza Jetpack Compose para construir la interfaz de usuario.

La lógica implementada separa conceptualmente:

- Interfaz de usuario.
- Gestión de medicamentos.
- Persistencia local.
- Ficha de emergencia.
- Navegación entre pantallas.

## Tecnologías utilizadas

- Android Studio
- Kotlin
- Jetpack Compose
- Material Design / Material 3
- Android SDK
- Persistencia local
- Git
- GitHub

## Compatibilidad

El proyecto fue configurado con:

- Minimum SDK: API 24
- Android 7.0 Nougat o superior
- Kotlin DSL para la configuración de Gradle

## Privacidad

MedAlert fue diseñado siguiendo un enfoque local-first.

Los datos introducidos por el usuario se almacenan localmente en el dispositivo y la versión académica actual no requiere autenticación, servicios en la nube ni transmisión de información médica a servidores externos.

## Funcionalidades consideradas como futuras mejoras

Las siguientes características se consideran posibles extensiones y no forman parte del núcleo actual:

- Recordatorios y notificaciones de medicamentos.
- Envío controlado de información mediante SMS.
- Sincronización en la nube.
- Autenticación de usuarios.
- Integración con servicios externos.
- Mapas o geolocalización.

Estas funcionalidades se mantienen fuera del alcance principal para conservar una aplicación académica sencilla, funcional y verificable.

## Estado del proyecto

**Estado actual: versión funcional en desarrollo final.**

La aplicación puede ejecutarse en el emulador Android y dispone de las funciones principales previstas para el proyecto académico: gestión CRUD de medicamentos, persistencia local y ficha de emergencia.

El trabajo restante se concentra principalmente en pruebas, validación, mejoras menores de interfaz y documentación final.

## Próximos pasos

1. Realizar pruebas funcionales completas.
2. Verificar persistencia después de reiniciar la aplicación.
3. Revisar validaciones de entrada.
4. Corregir posibles errores.
5. Realizar mejoras finales de interfaz.
6. Preparar documentación y evidencias de la entrega final.
7. Consolidar la versión final del proyecto.

## Control de versiones

El proyecto utiliza Git y GitHub para mantener el historial de desarrollo.

El repositorio contiene tanto la documentación académica de las primeras etapas como el código fuente actual de MedAlert.

## Autor

**Gabriel Solórzano García**  
COM 437 - Desarrollo de aplicaciones móviles  
Saint Leo University