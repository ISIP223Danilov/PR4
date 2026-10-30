plugins {
    id("java-library")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
tasks.withType<JavaExec> {
    jvmArgs("-Dfile.encoding=UTF-8")
}
