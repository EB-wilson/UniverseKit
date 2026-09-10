plugins {
    `java-library`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(
        listOf("--patch-module", "jdk.unsupported=" + file("src/main/java").path)
    )
}
