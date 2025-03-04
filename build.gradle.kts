import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    `java-library`
    java
    distribution
    kotlin("jvm") version Versions.Kotlin apply false
}

group = "com.github.dawndev"
version = "1.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

apply(plugin = "idea")
apply(plugin = "java")
apply(plugin = "kotlin")
apply(plugin = "java-library")

dependencies {
    api(Deps.Kotlin.Reflect)
    api(Deps.Kotlin.Jvm)
    implementation(Deps.CoroutinesJdk8)
    implementation(Deps.CoroutinesCore)

    api(Deps.Slf4j.Api)
    api(Deps.Slf4j.Impl)

    testImplementation(Deps.Junit.JupiterEngine)
    testImplementation(Deps.Junit.JupiterApi)
    testImplementation(Deps.Junit.Jupiter)
}

tasks {
    withType<JavaCompile>() {
        options.isFork = true
        options.encoding = "UTF-8"
    }

    withType<Javadoc> {
        options.encoding = "UTF-8"
    }

    withType<KotlinCompile> {
        kotlinOptions {
            javaParameters = true
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

configurations {
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}
