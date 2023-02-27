package com.mattdag.f1.listening.exporter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class F1ListeningExporter

fun main(args: Array<String>) {
    runApplication<F1ListeningExporter>(args = args)
}
