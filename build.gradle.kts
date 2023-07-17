import io.gitlab.arturbosch.detekt.Detekt
import com.filippo.multimodule.utils.JacocoUtil

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    jacoco
}

jacoco {
    version = "0.8.7"
}

@Suppress("UnusedPrivateMember")
tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }

    val detektAll by registering(Detekt::class) {
        description =
            "Runs detekt analysis over whole code base without the starting overhead for each module."
        parallel = true
        config.setFrom(file(path = "$rootDir/default-detekt-config.yml"))
        jvmTarget = "11"
        setSource(files(projectDir))
        include("**/*.kt", "**/*.kts")
        exclude("**/build/**", "**/resources/**")
        reports {
            xml.required.set(true)
            html.required.set(false)
            txt.required.set(false)
        }
    }

    val generateMergedJacocoReport by registering(JacocoReport::class) {
        executionData.setFrom(fileTree(projectDir) { include(JacocoUtil.moduleReportsExecPattern) })
        classDirectories.setFrom(
            fileTree(projectDir) {
                include(JacocoUtil.classIncludes)
                exclude(JacocoUtil.classExcludes)
            }
        )
        reports {
            xml.required.set(true)
            xml.outputLocation.set(file("${buildDir}${JacocoUtil.XML_REPORT_DESTINATION_SUFFIX}"))
            html.required.set(true)
            html.outputLocation.set(file("${buildDir}${JacocoUtil.HTML_REPORT_DESTINATION_SUFFIX}"))
        }
    }
}

apply(from = "buildsystem/dependencyGraph.gradle")
