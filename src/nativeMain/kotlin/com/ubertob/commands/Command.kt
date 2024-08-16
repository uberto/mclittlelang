package com.ubertob.commands

import com.ubertob.mcll.DataPack
import com.ubertob.mcll.EvalResult
import okio.Path.Companion.toPath

sealed interface Command {
    val curr: DataPack
    operator fun invoke( args: List<String>): EvalResult
}

data class SetWorld(override val curr: DataPack): Command {
    override fun invoke( args: List<String>): EvalResult {
        val path = args.first()
        return if (folderExists(path))
            "Successfully set world to $path" to DataPack("", path)
        else
            "Error! not found path $path" to curr
    }
}

data class ListDp(override val curr: DataPack): Command {
    override fun invoke( args: List<String>): EvalResult {
        val dps = listFoldersInPath(curr.worldPath+"/datapacks")
            .joinToString(separator = "\n") { it.name }
        val worldName = curr.worldPath.toPath().segments.last()
        return "DataPacks of $worldName:\n$dps" to curr
    }
}
