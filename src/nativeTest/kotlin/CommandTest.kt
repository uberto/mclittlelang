import com.ubertob.mcll.DataPack
import com.ubertob.mcll.eval
import okio.Path.Companion.toPath
import kotlin.test.Test
import kotlin.test.assertEquals

class CommandTest {

    private val world1Path = "saves_examples/w1".toPath()

    @Test
    fun `setWorld set the saved world folder`() {
        val curr = DataPack("".toPath(), "", "")
       val (output, newDp ) = eval("setWorld $world1Path", curr)
        assertEquals(world1Path, newDp.worldPath )
        assertEquals("Successfully set world to $world1Path", output )
    }

    @Test
    fun `listDp returns the list of datapacks of a world`() {
        val curr = DataPack(world1Path, "", "")
        val (output, newDp ) = eval("listDp", curr)
        assertEquals(world1Path, newDp.worldPath )
        val expected = """DataPacks of w1:
            |dp1
            |dp2
        """.trimMargin()
        assertEquals(expected, output )
    }

    @Test
    fun `setDp select a datapack as current`() {
        val curr = DataPack(world1Path, "", "")
        val (output, newDp ) = eval("setDp dp1", curr)
        assertEquals(world1Path, newDp.worldPath )
        assertEquals("dp1", newDp.name )
        val expected = """Successfully set current datapack to dp1 - Data Pack 1""".trimMargin()
        assertEquals(expected, output )
    }

    @Test
    fun `listFun returns the list of functions of a datapack including namespace`() {
        val curr = DataPack(world1Path, "", "")
        val (_, newDp ) = eval("setDp dp1", curr)
        val (output, _ ) = eval("listFun", newDp)
        val expected = """Functions of dp1:
            |ns1:water
            |ns2:air
            |ns2:stone
        """.trimMargin()
        assertEquals(expected, output )
    }
}