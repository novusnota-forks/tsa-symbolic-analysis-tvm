import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("tsa.kotlin-conventions")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation(project(":tsa-core"))
    implementation(project(":tsa-sarif"))

    implementation("org.ton:ton-kotlin-crypto:0.3.1")
    implementation("org.ton:ton-kotlin-tvm:0.3.1")
    implementation("org.ton:ton-kotlin-tonapi-tl:0.3.1")
    implementation("org.ton:ton-kotlin-tlb:0.3.1")
    implementation("org.ton:ton-kotlin-tl:0.3.1")
    implementation("org.ton:ton-kotlin-hashmap-tlb:0.3.1")
    implementation("org.ton:ton-kotlin-contract:0.3.1")
    implementation("org.ton:ton-kotlin-block-tlb:0.3.1")
    implementation("org.ton:ton-kotlin-bitstring:0.3.1")
    implementation("org.ton:ton-kotlin-bigint:0.3.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinx_serialization}")
    implementation("ch.qos.logback:logback-classic:${Versions.logback}")

    // todo: remove ksmt-core exclude after upgrading ksmt version in USVM
    implementation(group = Packages.ksmtBv2Int, name = "ksmt-core", version = Versions.ksmtBv2Int)
    implementation(group = Packages.usvm, name = "usvm-core", version = Versions.usvm) {
        exclude(group = "io.ksmt", module = "ksmt-core")
    }

    implementation("com.github.ajalt.clikt:clikt:${Versions.clikt}")
}

val mainClassName = "JettonWalletPropertiesAnalyzerKt"

tasks.register<JavaExec>("run") {
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass = mainClassName
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = mainClassName
    }
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("")
    val implementation = project.configurations["implementation"].dependencies.toSet()
    val runtimeOnly = project.configurations["runtimeOnly"].dependencies.toSet()
    val dependencies = (implementation + runtimeOnly)
    project.configurations.shadow.get().dependencies.addAll(dependencies)

    // Hack to make a dir for resources in the JAR
    from("src/main/resources") {
        into("resources")
        exclude("META_INF/**", mainClassName)
    }
}
