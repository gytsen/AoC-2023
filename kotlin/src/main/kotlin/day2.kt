package main

import kotlin.math.max

enum class Cube(val color: String, val max: Int) {
    Red("red", 12),
    Green("green", 13),
    Blue("blue", 14)
}

data class ShownCube(val color: Cube, val amount: Int)

data class Reveal(val shownCubes: ArrayList<ShownCube>)

data class Game(val id: Int, val reveals: ArrayList<Reveal>) {
    fun valid(): Boolean {
        reveals.forEach { reveal ->
            reveal.shownCubes.forEach { cube ->
                if (cube.amount > cube.color.max) {
                    return false
                }
            }
        }

        return true
    }

    fun getPowerNumber(): Int {
        val maxAmount = hashMapOf(Cube.Red.color to 0, Cube.Green.color to 0, Cube.Blue.color to 0)

        reveals.forEach { reveal ->
            reveal.shownCubes.forEach { cube ->
                val currentAmount = maxAmount[cube.color.color] ?: 0
                maxAmount[cube.color.color] = max(currentAmount, cube.amount)
            }
        }

        return maxAmount.values.reduce(Int::times)
    }
}

fun day2(input: String) {
    val lines = input.trim().split("\n")
    val playedGames = ArrayList<Game>(lines.size)

    lines.forEach { line ->
        val game = line.split(": ")
        val gameId = game[0].split(" ")[1].toInt()
        val games = game[1].split("; ")

        val reveals = ArrayList<Reveal>(games.size)

        games.forEach { instance ->
            val cubes = instance.split(", ")

            val shownCubes = ArrayList<ShownCube>(cubes.size)

            cubes.forEach { cube ->
                val parts = cube.split(" ")
                val color = when (parts[1]) {
                    Cube.Red.color -> Cube.Red
                    Cube.Green.color -> Cube.Green
                    Cube.Blue.color -> Cube.Blue
                    else -> {
                        println("found unkown color ${parts[1]} in input ${cube}")
                        Cube.Red
                    }
                }
                val amount = parts[0].toInt()

                val shownCube = ShownCube(color, amount)
                shownCubes.add(shownCube)
            }
            reveals.add(Reveal(shownCubes))
        }
        playedGames.add(Game(gameId, reveals))
    }

    val sum: Int = playedGames.sumOf { playedGame ->
        if (playedGame.valid()) {
            playedGame.id
        } else {
            0
        }
    }

    val powerNumber = playedGames.sumOf { playedGame -> playedGame.getPowerNumber() }

    println("2a: ${sum}")
    println("2b: ${powerNumber}")
}