plugins {
    java
    id("io.qameta.allure") version "2.9.6"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

val integrationTag = "Integration"
tasks {
    // simple Unit test. Result check jacoco. Must not send to Allure.
    // But after execute folder 'build' contains 'allure-results'
    test {
        useJUnitPlatform {
            excludeTags(integrationTag)
        }

        doLast {
            val allureResult = buildDir.resolve("allure-results")
            if (allureResult.exists()) {
                logger.error("Allure create result for Unit test")
                throw FileAlreadyExistsException(allureResult)
            }
        }
    }

    register("integrationTest", Test::class.java) {
        group = JavaBasePlugin.VERIFICATION_GROUP
        useJUnitPlatform {
            includeTags(integrationTag)
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of("11"))
    }
}

repositories {
    mavenCentral()
}

