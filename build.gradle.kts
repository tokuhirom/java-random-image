plugins {
    java
    application
}

group = "com.exmaple"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClassName = "com.example.Benchmarker"
}

dependencies {
    implementation("org.openjdk.jmh:jmh-core:1.22")
    annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.22")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}