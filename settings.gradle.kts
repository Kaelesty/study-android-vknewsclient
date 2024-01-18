pluginManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
		maven {
			url = java.net.URI.create("https://artifactory-external.vkpartner.ru/artifactory/vkid-sdk-andorid/")
		}
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "vknewsclient"
include(":app")
 