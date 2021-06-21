// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.5.0")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(ClassPaths.ANDROID_GRADLE)
        classpath (ClassPaths.KOTLIN_GRADLE)
        classpath(ClassPaths.HILT_GRADLE)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

tasks.register(name = "type",type = Delete::class) {
    delete(rootProject.buildDir)
}