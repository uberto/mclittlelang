import com.ubertob.mcll.DataPack
import com.ubertob.mcll.eval
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class cmlineTest {

    @Test
    fun test() {
        assertTrue(1+1==2)
//        assertTrue(1+1==3) fails (correctly)
    }

    private val world1Path = "saves_examples/w1"

    @Test
    fun `setWorld set the saved world folder`() {
        val curr = DataPack("", "")
       val (output, newDp ) = eval("setWorld $world1Path", curr)
        assertEquals(world1Path, newDp.worldPath )
        assertEquals("Successfully set world to $world1Path", output )
    }

    @Test
    fun `listDp returns the list of datapacks of a world`() {
        val curr = DataPack("", "./saves_examples/w1")
        val (output, newDp ) = eval("listDp", curr)
        assertEquals("./saves_examples/w1", newDp.worldPath )
        val expected = """DataPacks of w1:
            |dp1
            |dp2
        """.trimMargin()
        assertEquals(expected, output )
    }
}