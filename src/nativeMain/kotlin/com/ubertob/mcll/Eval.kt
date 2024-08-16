package com.ubertob.mcll

import com.ubertob.commands.*

typealias EvalResult = Pair<String, DataPack>

fun eval(input: String, currentDp: DataPack): EvalResult {
    val words = input.split(" ").map(String::trim)
    if (words.isNotEmpty()) {
        val firstWord = words.first()
        val cmd: Command = when (firstWord.lowercase()) { //!!! command name and description should come from commands themselves
            "show" -> Show(currentDp)
            "listdp" ->ListDp(currentDp)
            "listfun" -> ListFun(currentDp)
            "fun" ->TODO()
            "createdp" ->TODO()
            "setworld" -> SetWorld(currentDp)
            "setdp" -> SetDp(currentDp)
            else -> return "Unknown command: $firstWord" to currentDp
        }
        return cmd(words.drop(1))
    } else {
        return "?" to currentDp
    }

}