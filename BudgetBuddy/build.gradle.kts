plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")

    implementation("com.google.guava:guava:33.2.1-jre")

    implementation("com.mysql:mysql-connector-j:8.4.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("me.techii.Main")
}