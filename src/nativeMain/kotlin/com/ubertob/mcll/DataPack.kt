package com.ubertob.mcll

import okio.Path
import okio.Path.Companion.toPath

data class DataPack(val worldPath: Path, val name: String, val description: String) {
    companion object {
       val default = DataPack(".".toPath(), "", "")
    }
}
