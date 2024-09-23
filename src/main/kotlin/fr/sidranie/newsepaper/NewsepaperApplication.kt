package fr.sidranie.newsepaper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewsepaperApplication

fun main(args: Array<String>) {
	runApplication<NewsepaperApplication>(*args)
}
