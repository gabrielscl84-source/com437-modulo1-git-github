# MedAlert - COM 437

## Descripción del proyecto

**MedAlert** es una aplicación Android desarrollada como proyecto
académico para **COM 437: Desarrollo de aplicaciones móviles**. Su
propósito es facilitar la organización personal de medicamentos mediante
una interfaz sencilla, permitiendo registrar y consultar información
básica como nombre del medicamento, dosis y horario. También contempla
una ficha básica de emergencia con información seleccionada por el
usuario.

El proyecto mantiene un alcance deliberadamente simple y realizable
durante el curso: una aplicación Android con almacenamiento local y
operaciones CRUD. Funcionalidades más complejas, como sincronización en
la nube, mapas o autenticación remota, se consideran posibles mejoras
futuras y no forman parte del núcleo actual.

## Problema que busca resolver

Las personas que utilizan varios medicamentos pueden necesitar una forma
rápida de organizar su información terapéutica básica. MedAlert propone
centralizar estos datos en el dispositivo móvil para facilitar su
consulta y actualización.

## Objetivo general

Desarrollar una aplicación Android funcional que permita administrar
localmente medicamentos y datos básicos de emergencia mediante una
interfaz clara, coherente y fácil de utilizar.

## Objetivos específicos

-   Registrar medicamentos con nombre, dosis y horario.
-   Consultar los medicamentos almacenados.
-   Editar y eliminar registros existentes.
-   Mantener una ficha básica de emergencia.
-   Aplicar principios de Material Design y navegación comprensible.
-   Persistir información estructurada mediante almacenamiento local.
-   Documentar progresivamente el desarrollo mediante Git, GitHub,
    README, Wiki y CHANGELOG.

## Alcance funcional actual

El alcance del proyecto se concentra en:

1.  **Gestión de medicamentos:** creación, lectura, actualización y
    eliminación de registros.
2.  **Ficha de emergencia:** almacenamiento de información básica
    seleccionada por el usuario.
3.  **Interfaz Android:** pantallas simples, navegación clara y
    componentes coherentes con Material Design.
4.  **Persistencia local:** organización de los datos en el dispositivo.
5.  **Documentación:** README, Wiki y registro de cambios del proyecto.

## Diseño y wireframes

Antes de implementar las pantallas se definió un flujo sencillo
orientado a las tareas principales del usuario. Los wireframes sirven
como guía para organizar:

-   Pantalla principal.
-   Lista de medicamentos.
-   Formulario para agregar o editar medicamentos.
-   Ficha de emergencia.

La prioridad de diseño es reducir pasos innecesarios y mantener visibles
las funciones principales.

## Arquitectura propuesta

El proyecto separa conceptualmente la interfaz de usuario de la gestión
de datos. La aplicación se organiza alrededor de una actividad principal
y pantallas/componentes destinados a mostrar y modificar la información.
La persistencia local se mantiene separada de la presentación para
facilitar mantenimiento y futuras mejoras.

## Base de datos y almacenamiento

MedAlert requiere conservar información después de cerrar la aplicación.
Por ello, los medicamentos y datos de emergencia se modelan como
información estructurada para almacenamiento local. Las operaciones
principales corresponden al modelo CRUD:

-   **Create:** agregar un medicamento.
-   **Read:** consultar medicamentos.
-   **Update:** modificar dosis, horario u otros datos.
-   **Delete:** eliminar un registro.

## Material Design

La interfaz busca aplicar principios de Material Design mediante
jerarquía visual, botones claramente identificables, formularios
simples, tarjetas o listas para presentar información y
retroalimentación visual ante las acciones del usuario.

## SMS y comunicación

El módulo 6 introduce el uso de SMS en Android. Para MedAlert, esta
tecnología se documenta como una posible extensión controlada para
compartir información seleccionada por el usuario con un contacto. No se
considera indispensable para el núcleo CRUD y cualquier implementación
deberá respetar permisos, privacidad y minimización de datos.

## Tecnologías y herramientas

-   Android Studio
-   Kotlin
-   Kotlin DSL
-   Android SDK
-   API mínima: Android 7.0 Nougat (API 24)
-   Git
-   GitHub
-   Emulador Android / Pixel estándar

## Control de versiones

El repositorio utiliza Git y GitHub para documentar el progreso del
proyecto. Los cambios relevantes se registran mediante commits
descriptivos y se resumen en `CHANGELOG.md`.

## Estado del proyecto

**En desarrollo - avance del módulo 6.**

Hasta este punto se han trabajado los fundamentos de Android,
arquitectura, Activities/Fragments/Intents, wireframes, diseño de
interfaz, Material Design, almacenamiento local/bases de datos y
conceptos de SMS. El proyecto continúa enfocado en completar una versión
sencilla y funcional antes de la entrega final del módulo 8.

## Próximos pasos

-   Consolidar las pantallas principales.
-   Completar el CRUD local de medicamentos.
-   Validar navegación y formularios.
-   Revisar la ficha de emergencia.
-   Realizar pruebas en el emulador.
-   Mantener actualizado el README, Wiki y CHANGELOG.
-   Preparar el código para la entrega final del módulo 8.

## Repositorio

Repositorio del proyecto:\
https://github.com/gabrielscl84-source/com437-modulo1-git-github

Wiki del proyecto:\
https://github.com/gabrielscl84-source/com437-modulo1-git-github/wiki

## Autor

**Gabriel Antonio Solórzano García**\
Saint Leo University\
COM 437 - Desarrollo de aplicaciones móviles
