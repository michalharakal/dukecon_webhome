plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `maven-publish`
    `java`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    compileOnly(gradleApi())
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
    implementation("org.eclipse.jgit:org.eclipse.jgit:5.6.0.201912101111-r")
}

gradlePlugin {
    plugins {
        register("GitUpdater") {
            id = "git-updater"
            implementationClass = "plugins.DukeconGitPlugin"
        }
    }
}
