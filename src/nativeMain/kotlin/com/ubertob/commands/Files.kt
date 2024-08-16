package com.ubertob.commands

import okio.FileSystem
import okio.Path


fun listFoldersInPath(directoryPath: Path): List<Path> {
    val fileSystem = FileSystem.SYSTEM

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

fun folderExists(path: Path): Boolean {
    val fileSystem = FileSystem.SYSTEM

    // Retrieve the metadata for the path and check if it exists and is a directory
    val metadata = fileSystem.metadataOrNull(path)
    return metadata?.isDirectory == true
}