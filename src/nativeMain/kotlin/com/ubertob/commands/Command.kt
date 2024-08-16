package com.ubertob.commands

import com.ubertob.mcll.DataPack
import com.ubertob.mcll.EvalResult
import okio.Path.Companion.toPath

sealed interface Command {
    val curr: DataPack
    operator fun invoke(args: List<String>): EvalResult
}

data class SetWorld(override val curr: DataPack) : Command {
    override fun invoke(args: List<String>): EvalResult {
        val path = args.joinToString(" ").toPath()
        return if (folderExists(path))
            "Successfully set world to $path" to DataPack(path, "", "")
        else
            "Error! not found path $path" to curr
    }
}

data class ListDp(override val curr: DataPack) : Command {
    override fun invoke(args: List<String>): EvalResult {
        val dps = listFoldersInPath(curr.worldPath / "datapacks".toPath())
            .joinToString(separator = "\n") { it.name }
        val worldName = curr.worldPath.segments.last()
        return "DataPacks of $worldName:\n$dps" to curr
    }
}

data class ListFun(override val curr: DataPack) : Command {
    //!!! handle namespaces!!!
    override fun invoke(args: List<String>): EvalResult {

        val nsPath = curr.worldPath / "datapacks/${curr.name}/data/".toPath()
        val nameSpaces = listFoldersInPath(nsPath)

        val funs = nameSpaces.flatMap { nsPath ->
            val dirPath = nsPath / "function".toPath()
            listFilesWithExtension(dirPath, "mcfunction")
                .map { "${nsPath.name}:${it.name.substringBefore(".")}" }
        }.joinToString(separator = "\n")
        return "Functions of ${curr.name}:\n$funs" to curr
    }
}

data class SetDp(override val curr: DataPack) : Command {
    override fun invoke(args: List<String>): EvalResult {
        val filename = args.first()
        val filePath = curr.worldPath / "datapacks/${filename}/pack.mcmeta".toPath()
        val jsonData = readTextFileAsLines(filePath).joinToString(separator = "")
        val description = jsonData.substringAfter(""""description": """")
            .substringBeforeLast(""""""")
        val dataPack = DataPack(curr.worldPath, filename, description)
        return "Successfully set current datapack to ${dataPack.name} - ${dataPack.description}" to dataPack
    }
}

data class Show(override val curr: DataPack) : Command {
    override fun invoke(args: List<String>): EvalResult {
        return "${curr.worldPath.absolute()} ${curr.name} ${curr.description}" to curr
    }

}

