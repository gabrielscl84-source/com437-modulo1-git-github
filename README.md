# MedAlert - COM 437

## Descripción

MedAlert es una aplicación Android desarrollada como proyecto progresivo
para COM 437: Desarrollo de aplicaciones móviles, Saint Leo University.

Su propósito es apoyar la organización personal de medicamentos y
facilitar el acceso rápido a información básica relevante para
situaciones de emergencia.

## Problema que aborda

La aplicación busca reducir las dificultades relacionadas con la
organización de medicamentos, dosis y horarios, además de mantener
disponible información básica como alergias y antecedentes relevantes.

## Objetivos de MedAlert

- Registrar medicamentos, dosis y horarios.
- Consultar los medicamentos registrados.
- Mantener una ficha básica de información de emergencia.
- Facilitar el acceso organizado a información relevante del usuario.
- Incorporar progresivamente nuevas funciones durante COM 437.
- Mantener un desarrollo documentado mediante Git y GitHub.

## Funciones planificadas

La primera versión de MedAlert tendrá una estructura sencilla que podrá
ampliarse conforme avance el curso.

Entre las funciones previstas se encuentran:

- Registro de medicamentos.
- Registro de dosis y horarios.
- Consulta y modificación de medicamentos.
- Ficha básica de emergencia.
- Registro de alergias y antecedentes relevantes.
- Recordatorios de medicamentos.
- Almacenamiento local de información.
- Evaluación futura de servicios en la nube.
- Posible incorporación de ubicación y mapas.

## Módulo 1 - Git y GitHub

Durante el Módulo 1 se establecieron las bases para administrar el
desarrollo del proyecto mediante control de versiones.

Se realizaron las siguientes actividades:

- Creación del repositorio en GitHub.
- Creación y actualización del archivo README.md.
- Uso de commits para registrar cambios.
- Creación de una rama independiente.
- Modificación del proyecto dentro de la rama.
- Creación de un pull request.
- Revisión y fusión de los cambios con la rama `main`.

Este flujo permitirá conservar una historia verificable de la evolución
de MedAlert durante el curso.

## Módulo 2 - Diseño de aplicaciones Android

Durante el Módulo 2 se inicia la organización de la arquitectura y
navegación de MedAlert mediante conceptos fundamentales de Android.

### Activities

Las Activities representarán puntos principales de interacción con el
usuario. La estructura preliminar contempla una pantalla principal desde
la cual se podrá acceder a las distintas funciones de MedAlert.

### Fragments

Los Fragments permitirán organizar y reutilizar partes de la interfaz de
usuario dentro de las Activities.

Se contempla su utilización para secciones como:

- Lista de medicamentos.
- Información de emergencia.
- Visualización de información del usuario.

### Intents

Los Intents permitirán establecer la navegación y comunicación entre
diferentes componentes de la aplicación.

Por ejemplo, podrán utilizarse para pasar desde la pantalla principal
hacia una pantalla destinada al registro o consulta de medicamentos.

## Estructura preliminar de navegación

La navegación inicial propuesta es:

`Pantalla principal`

→ `Medicamentos`

→ `Agregar medicamento`

→ `Detalle del medicamento`

→ `Ficha de emergencia`

Esta estructura podrá modificarse conforme se desarrollen y prueben las
funciones de la aplicación.

## Tecnologías y herramientas

El proyecto utilizará progresivamente:

- Android Studio
- Git
- GitHub
- Activities
- Fragments
- Intents
- Material Design
- Almacenamiento local
- Servicios en la nube cuando sean necesarios

## Control de versiones

Cada avance significativo será registrado mediante Git.

El flujo de trabajo utilizado será:

1. Crear o seleccionar una rama de trabajo.
2. Realizar las modificaciones.
3. Revisar los cambios.
4. Crear un commit.
5. Publicar los cambios en GitHub.
6. Crear un pull request cuando corresponda.
7. Integrar los cambios aprobados en `main`.

## Estado del proyecto

**Módulo 1:** completado.

**Módulo 2:** definición de la aplicación y diseño preliminar de
Activities, Fragments e Intents.

**Próximo avance:** creación de la estructura inicial de MedAlert en
Android Studio y publicación progresiva del código en GitHub.

## Autor

Gabriel Antonio Solórzano García  
Saint Leo University  
COM 437 - Desarrollo de aplicaciones móviles  
2026
