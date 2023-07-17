enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "multi-module-arch-hilt"

include(
    ":app",
    ":core:navigation",
    ":core:network:data",
    ":core:network:domain",
    ":core:ui",

    ":core:dispatchers:data",
    ":core:dispatchers:domain",
    ":core:session:data",
    ":core:session:domain",
    ":core:images:domain",
    ":core:images:data",

    ":feature:account:data",
    ":feature:account:domain",
    ":feature:account:presentation",
    ":feature:movies:data",
    ":feature:movies:domain",
    ":feature:movies:presentation",
    ":feature:login:data",
    ":feature:login:domain",
    ":feature:login:presentation",
    ":feature:search:data",
    ":feature:search:domain",
    ":feature:search:presentation"
)
