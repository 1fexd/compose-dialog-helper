import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

fun PublishingExtension.publish(
    project: Project,
    group: String,
    artifact: String,
    version: String,
    publication: String
) {
    this.publications {
        register<MavenPublication>(publication) {
            this.groupId = group
            this.artifactId = artifact
            this.version = version

            project.afterEvaluate {
                from(components[publication])
            }
        }
    }
}
