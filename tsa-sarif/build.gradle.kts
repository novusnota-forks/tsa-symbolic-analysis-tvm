plugins {
    id("tsa.kotlin-conventions")
}

dependencies {
    implementation(project(":tsa-core"))

    // https://mvnrepository.com/artifact/io.github.detekt.sarif4k/sarif4k
    implementation("io.github.detekt.sarif4k:sarif4k:0.6.0")
}
