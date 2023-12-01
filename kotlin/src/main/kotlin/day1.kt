package main

val literalNumberMapping =
    mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9'
    )

fun day1(input: String) {
    val lines = input.split("\n")
    // 1a
    var total = 0
    lines.forEach { line ->
        val number = "${getFrontDigit(line)}${getBackDigit(line)}"

        total += number.toInt()
    }

    // 1b
    var fixedTotal = 0
    lines.forEach { line ->
        val number = "${getLiteralFrontDigit(line)}${getLiteralBackDigit(line)}"
        fixedTotal += number.toInt()
    }

    println("1a: ${total}")
    println("1b: ${fixedTotal}")
}

fun getFrontDigit(input: String): Char {
    for (char in input) {
        if (char.isDigit()) {
            return char
        }
    }

    return '0'
}

fun getBackDigit(input: String): Char {
    for (index in input.length - 1 downTo 0) {
        if (input[index].isDigit()) {
            return input[index]
        }
    }

    return '0'
}

fun getLiteralFrontDigit(input: String): Char {
    input.forEachIndexed { index, char ->
        if (char.isDigit()) {
            return char
        }

        literalNumberMapping.keys.forEach { key ->
            if (input.substring(index).startsWith(key)) {
                return literalNumberMapping[key] ?: '0'
            }
        }
    }

    return '0'
}

fun getLiteralBackDigit(input: String): Char {
    for (index in input.length - 1 downTo 0) {
        if (input[index].isDigit()) {
            return input[index]
        }

        literalNumberMapping.keys.forEach { key ->
            if (input.substring(0, index + 1).endsWith(key)) {
                return literalNumberMapping[key] ?: '0'
            }
        }
    }

    return '0'
}