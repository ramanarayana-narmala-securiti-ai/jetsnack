/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
val snapshotVersion : String? = System.getenv("COMPOSE_SNAPSHOT_ID")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/org/jetbrains/kotlin/kotlin-compose-compiler-plugin/2.0.0-RC2-200/") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        snapshotVersion?.let {
            println("https://androidx.dev/snapshots/builds/$it/artifacts/repository/") 
            maven { url = uri("https://androidx.dev/snapshots/builds/$it/artifacts/repository/") }
            maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev/org/jetbrains/kotlin/kotlin-compose-compiler-plugin/2.0.0-RC2-200/") }
        }

        google()
        mavenCentral()
        maven {
            //url = uri("https://cdn-dev-intg-2.securiti.xyz/consent/maven")
            url = uri("https://cdn-qa.securiti.xyz/consent/maven")
        }
    }
}
rootProject.name = "Jetsnack"
include(":app")
