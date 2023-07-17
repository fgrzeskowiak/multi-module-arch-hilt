# Multi-module architecture with Hilt 

A simple project for browsing movies from [TMDB](https://www.themoviedb.org/), illustrating a multi-module Clean Architecture.
In this repository, modules are split by `core` and `feature`, and each module is further split into `domain`, `data`, and optionally `presentation` modules.
`data` and `presentation` modules communicate only through `domain` module and similarly, different `feature` od `core` modules can also communicate only through `domain`.

Project also uses Gradle Convention plugins to simplify builds configuration and [Arrow](https://arrow-kt.io/) library for validation and error handling.

## Setup
In order to build a project, a `secret.properties` file must be placed inside root directory containing:
```
MoviesAccessToken=
MoviesProdBaseUrl=
MoviesStagingBaseUrl=
```
This data can be obtained after creating an account on [TMDB](https://www.themoviedb.org/), in [Account Settings](https://www.themoviedb.org/settings/api)

## Architecture Graph
![Project Graph](project_graph.png?raw=true "Project Dependency Graph")
