package com.ubertob.mcll


fun evaluateExpression(input: String, currentDp: DataPack): Pair<String, DataPack> {
    val words = input.split(" ").map(String::trim)
    if (words.isNotEmpty()) {
        val command = words.first()
        val output = when (command.lowercase()) {
            "fun" -> "WIP"
            "createdp" -> "WIP"
            "listdp" -> "WIP"
            "setworld" -> "WIP"
            "setdp" -> "WIP"
            else -> "Unknown command: $command"
        }
        return output to currentDp
    } else {
        return "?" to currentDp
    }

}