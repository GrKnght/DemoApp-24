pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ITIS-DemoApp24"

include(":app")
// Core
include(":core:data")
include(":core:domain")
include(":core:base")
include(":core:network")

// Feature
include(":feature:mainpage")
include(":feature:search")
include(":feature:song-details")
include(":core:utils")
include(":core:base-feature")
