package com.filippo.multimodule.utils

object JacocoUtil {

    val classExcludes = listOf(
        "**/R.class",
        "**/R\$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/*\$Lambda$*.*",
        "**/*\$inlined$*.*",
        "*/androidx/databinding/*",
        "*_ViewBinding*.*",
        "*/*MapperImpl*.*",
        "**/*\$ViewInjector*.*",
        "*/*\$ViewBinder*.*",
        "**/*Component*.*",
        "*/*BR*.*",
        "*/*Companion*.*",
        "**/*ModuleKt*",
        "**/*ConfigKt*",
        "**/Dagger*Component.*",
        "**/Dagger*Component\$Builder.*"
    )

    val classIncludes = listOf(
        "**/build/intermediates/javac/debug/*/classes/**",
        "**/build/tmp/kotlin-classes/debug/**"
    )

    val moduleReportsExecPattern = listOf(
        "**/build/jacoco/testDebugUnitTest.exec"
    )

    const val XML_REPORT_DESTINATION_SUFFIX = "/reports/jacoco/jacocoTestReport.xml"
    const val HTML_REPORT_DESTINATION_SUFFIX = "/reports/jacoco/html"
}
