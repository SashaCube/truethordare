buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        BuildPlugins.run {
            classpath(androidGradlePlugin)
            classpath(kotlinGradlePlugin)
            classpath(googleServices)
            classpath(BuildPlugins.Nav.saveArgs)
        }
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean").configure {
    delete("build")
}