package main

import java.nio.charset.Charset

val days = hashMapOf(1 to ::day1)

fun main(args: Array<String>) {
    if (args.count() != 1) {
        println("invalid args")
        return
    }

    val day = Integer.parseInt(args[0])
    val input = loadResource(day)

    val implementation = days[day]

    if (implementation == null || input == null) {
        println("no implementation for day $day")
        return
    }

    implementation(input)
}

fun loadResource(day: Int): String? {
    val path = "input/${day}.txt"
    val resource = Thread.currentThread().contextClassLoader.getResource(path)
    return resource?.readText(Charset.forName("UTF-8"))
}
