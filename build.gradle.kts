import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

group = "org.goafabric"
version = "3.1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val dockerRegistry = "goafabric"
val nativeBuilder = "dashaun/builder:20230225"
val baseImage = "ibm-semeru-runtimes:open-17.0.6_10-jre-focal@sha256:739eab970ff538cf22a20b768d7755dad80922a89b73b2fddd80dd79f9b880a1"

plugins {
	java
	jacoco
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.graalvm.buildtools.native") version "0.9.22"
	id("com.google.cloud.tools.jib") version "3.3.1"
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	constraints {
		implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
		implementation("org.mapstruct:mapstruct:1.5.4.Final")
		annotationProcessor("org.mapstruct:mapstruct-processor:1.5.4.Final")
		implementation("io.github.resilience4j:resilience4j-spring-boot3:2.0.2")
	}
}

dependencies {
	//web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-graphql")

	//persistence
	//implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")

	if (!gradle.startParameter.taskNames.contains("nativeCompile") && (!gradle.startParameter.taskNames.contains("dockerImageNative"))) {
		developmentOnly("de.flapdoodle.embed:de.flapdoodle.embed.mongo.spring30x:4.6.2")
	}

	//monitoring
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus")

	//crosscuting
	implementation("org.springframework.boot:spring-boot-starter-aop")

	//code generation
	implementation("org.mapstruct:mapstruct")
	annotationProcessor("org.mapstruct:mapstruct-processor")

	//test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:testcontainers:1.17.6")
}

tasks.withType<Test> {
	useJUnitPlatform()
	exclude("**/*NRIT*")
	finalizedBy("jacocoTestReport")
}

jib {
	val amd64 = com.google.cloud.tools.jib.gradle.PlatformParameters(); amd64.os = "linux"; amd64.architecture = "amd64"; val arm64 = com.google.cloud.tools.jib.gradle.PlatformParameters(); arm64.os = "linux"; arm64.architecture = "arm64"
	from.image = baseImage
	to.image = "${dockerRegistry}/${project.name}:${project.version}"
	container.jvmFlags = listOf("-Xms256m", "-Xmx256m")
	from.platforms.set(listOf(amd64, arm64))
}

tasks.register("dockerImageNative") { setGroup("build") ; dependsOn("bootBuildImage") }
tasks.named<BootBuildImage>("bootBuildImage") {
	val nativeImageName = "${dockerRegistry}/${project.name}-native" + (if (System.getProperty("os.arch").equals("aarch64")) "-arm64v8" else "") + ":${project.version}"
	builder.set(nativeBuilder)
	imageName.set(nativeImageName)
	environment.set(mapOf("BP_NATIVE_IMAGE" to "true", "BP_JVM_VERSION" to "17", "BP_NATIVE_IMAGE_BUILD_ARGUMENTS" to "-J-Xmx4000m"))
	doLast {
		exec { commandLine("docker", "run", "--rm", nativeImageName, "-check-integrity") }
		exec { commandLine("docker", "push", nativeImageName) }
	}
}