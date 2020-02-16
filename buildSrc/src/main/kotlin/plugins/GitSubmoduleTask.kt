package plugins

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.submodule.SubmoduleWalk
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import java.io.File

open class GitSubmoduleTask : DefaultTask() {

    init {
        group = "prepare"
        description = "Update git submodules"
    }

    @TaskAction
    fun run() {
        val gitSubmoduleUpdater = GitSubmoduleUpdater(project)
        gitSubmoduleUpdater.update()
    }

    class GitSubmoduleUpdater(private val project: Project) {
        fun update() {
            println("Root ${project.rootDir}")
            val git = Git.open(project.rootDir)
            val walk = SubmoduleWalk.forIndex(git.repository)
            try {
                while (walk.next()) {
                    val submodule = walk.repository
                    if (submodule != null) {
                        println(submodule.branch)
                        Git.wrap(submodule).fetch().call()
                        submodule.close()
                    }
                }
                //   git.submoduleSync().call()
                // git.submoduleUpdate().call()

            } catch (e: Exception) {
                println(e.printStackTrace())
            }
        }
    }
}
