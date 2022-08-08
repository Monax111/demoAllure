import java.io.FileNotFoundException

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
            // Unit test not four Allure. Dir allure-results must be empty
            val allureResult = buildDir.resolve("allure-results")
            if (allureResult.listFiles()!!.count() > 1) { //исключаем executor.json
                logger.error("Allure create result for Unit test")
                throw FileAlreadyExistsException(allureResult)
            }

            allure {
                adapter {
                    frameworks {
                        junit5 {
                            enabled.set(true)
                        }
                    }
                }
            }
        }
    }

    val integrationTest = register("integrationTest", Test::class.java) {
        group = JavaBasePlugin.VERIFICATION_GROUP
        useJUnitPlatform {
            includeTags(integrationTag)
        }

        shouldRunAfter(test)

        doLast {
            // Integration test four Allure. Dir allure-results must be exist
            val allureResult = buildDir.resolve("allure-results")
            if (allureResult.listFiles()!!.count() < 2) { //исключаем executor.json
                logger.error("Allure not create result for Unit test")
                throw FileNotFoundException(allureResult.path)
            }
        }


    }

    check {
        dependsOn(integrationTest)
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

allure {
    adapter {
        aspectjWeaver.set(false)
        frameworks {
            junit5 {
                enabled.set(false)
            }
        }
    }
}
