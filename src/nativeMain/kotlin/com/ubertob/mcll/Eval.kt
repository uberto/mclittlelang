package com.ubertob.mcll

import com.ubertob.commands.Command
import com.ubertob.commands.ListDp
import com.ubertob.commands.SetWorld

typealias EvalResult = Pair<String, DataPack>

fun eval(input: String, currentDp: DataPack): EvalResult {
    val words = input.split(" ").map(String::trim)
    if (words.isNotEmpty()) {
        val command = words.first()
        val cmd: Command = when (command.lowercase()) {
            "fun" ->TODO()
            "createdp" ->TODO()
            "listdp" ->ListDp(currentDp)
            "setworld" -> SetWorld(currentDp)
            "setdp" ->TODO()
            else -> return "Unknown command: $command" to currentDp
        }
        return cmd(words.drop(1))
    } else {
        return "?" to currentDp
    }

}