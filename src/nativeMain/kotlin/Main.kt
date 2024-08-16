import com.ubertob.mcll.DataPack
import com.ubertob.mcll.eval
import okio.Path.Companion.toPath

// to run it, either from IntelliJ or
//./gradlew -q --console=plain runReleaseExecutableNative
// or
//./build/bin/native/debugExecutable/mclittlelang.kexe 1 2

fun main(args: Array<String>) {
    println("Hello to MineCraft Little Language REPL!")
//    println("Args are:") //you can specify world and dp here
//    args.onEach {
//        println(it)
//    }

    printCommandHelp()

    readLnLoop()
}

private fun readLnLoop() {
    var dp = DataPack(".".toPath(), "", "")
    while (true) {
//        print("> ")  // Prompt for input
        val input = readlnOrNull() ?: continue  // Read input from console

        println("got it")
        if (input == "exit") {
            println("Goodbye!")
            break
        }

        try {
            // Evaluate the input as an arithmetic expression
            val (result, newDp) = eval(input, dp)
            dp = newDp
            println(result)
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}

private fun printCommandHelp() {
    println("Possible commands are:")
    println("fun [file.mcll] -> will generate file.mcfunction in the current datapack")
    println("listDP -> list all datapack of current world")
    println("createDp [Datapack name] -> will generate a datapack folder structure with given name and made it current")
    println("setWorld [MC saved folder] -> store the MC current world")
    println("setDP [Datapack name] -> makes specified datapack as current")
    println("exit -> close this program")
}

