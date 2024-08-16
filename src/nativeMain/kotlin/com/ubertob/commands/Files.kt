package com.ubertob.commands

import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath


fun listFoldersInPath(path: String): List<Path> {
    val fileSystem = FileSystem.SYSTEM
    val directoryPath: Path = path.toPath()

    // Ensure the path exists and is a directory
    if (fileSystem.metadataOrNull(directoryPath)?.isDirectory == true) {
        // List all files and directories in the path
        return fileSystem.list(directoryPath).filter {
            // Filter only directories
            fileSystem.metadata(it).isDirectory
        }
    } else {
        throw IllegalArgumentException("Path does not exist or is not a directory")
    }
}

fun folderExists(path: String): Boolean {
    val fileSystem = FileSystem.SYSTEM
    val folderPath: Path = path.toPath()

    // Retrieve the metadata for the path and check if it exists and is a directory
    val metadata = fileSystem.metadataOrNull(folderPath)
    return metadata?.isDirectory == true
}