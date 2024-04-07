@file:Suppress("UnstableApiUsage")

include(":baselineprofile")


include(":benchmark")


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
        maven { url  = uri("https://jitpack.io") }
    }
}

rootProject.name = "YassirMovieApp"
include(":app")
include(":navigation")
include(":design")
include(":common")
include(":network")
include(":feature")
include(":feature:movies")
include(":feature:moviedetails")

