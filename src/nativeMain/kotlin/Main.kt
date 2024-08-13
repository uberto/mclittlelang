// to run it, either from IntelliJ or
//./gradlew -q --console=plain runReleaseExecutableNative
// or
//./build/bin/native/debugExecutable/mclittlelang.kexe 1 2

fun main(args: Array<String>) {
    println("Hello to MineCraft Little Language repl!")
    println("Args are:")
    args.onEach {
        println(it)
    }

    println("Possible commands are:")
    println("fun [file.mcll] -> will generate file.mcfunction in the current datapack")
    println("setWorld [MC saved folder] -> store the MC current world")
    println("listDP -> list all datapack of current world")
    println("dp [Datapack name] -> will generate a datapack folder structure with given name and made it current")
    println("setDP [Datapack name] -> makes specified datapack as current")
    println("exit -> close this program")

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
            val result = evaluateExpression(input)
            println(result)
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}

fun evaluateExpression(input: String): String {
    return "You wrote $input"
}
