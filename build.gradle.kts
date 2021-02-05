import home.dj.excel.parser.BusinessRuleParser
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
    application
}

group = "home.dj"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets.getByName("main") {
    java.srcDir("build/generated/src/main/kotlin")
}

dependencies {
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "14"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
}

application {
    mainClass.set("home.dj.engine.AppKt")
}

tasks.register<BusinessRuleParser>("parseRulesFromExcel")