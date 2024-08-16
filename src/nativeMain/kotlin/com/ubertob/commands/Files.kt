package com.ubertob.commands

import okio.FileSystem
import okio.Path
import okio.buffer
import okio.use

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

fun readTextFileAsLines(filePath: Path): List<String> {
    val fileSystem = FileSystem.SYSTEM

    // Ensure the file exists before attempting to read it
    if (fileSystem.exists(filePath)) {
        fileSystem.source(filePath).buffer().use { source ->
            return source.readUtf8().lines()
        }
    } else {
        throw IllegalArgumentException("File does not exist: ${filePath.name}")
    }
}

fun Path.absolute(): String = FileSystem.SYSTEM.canonicalize(this).toString()

fun listFilesWithExtension(dirPath: Path, extension: String): List<Path> {
    val fileSystem = FileSystem.SYSTEM

    if (fileSystem.exists(dirPath)) {
        return  fileSystem.list(dirPath).filter { file ->
            !fileSystem.metadata(file).isDirectory && file.name.endsWith(extension)
        }
    } else{
        throw IllegalArgumentException("Folder does not exist: ${dirPath.absolute()}")
    }

}


