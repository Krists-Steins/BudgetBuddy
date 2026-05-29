plugins {
    java
    application
	id("com.gradleup.shadow") version "8.3.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.2.1-jre")
    implementation("com.mysql:mysql-connector-j:8.4.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

application {
    mainClass.set("me.techii.Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "me.techii.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}