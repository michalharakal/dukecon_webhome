package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

open class DukeconGitPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        tasks.register<GitSubmoduleTask>("gitsubmodules") {
        }
    }
}