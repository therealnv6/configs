import io.github.devrawr.configs.shared.ConfigCreator
import io.github.devrawr.configs.shared.annotation.Path
import io.github.devrawr.configs.shared.file.FileHandler
import io.github.devrawr.configs.shared.file.FileReader
import io.github.devrawr.configs.shared.file.FileWriter
import io.github.devrawr.configs.shared.file.gson.GsonFileHandler
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class ConfigCreationTest
{
    fun createConfig()
    {
        ConfigCreator
            .initializeWithHandler<GsonFileHandler>()
            .create<ConfigExample>("config.yml")
    }

    @Test
    fun testConfig()
    {
        this.createConfig()

        assertEquals(ConfigExample.noImplicitPath, "hey")
        assertEquals(ConfigExample.implicitPath, "bye")
    }
}

object ConfigExample
{
    // will be stored as "NO_IMPLICIT_PATH"
    var noImplicitPath = "hey"

    // will be stored as "SUPER_IMPLICIT_PATH"
    @Path("SUPER_IMPLICIT_PATH")
    var implicitPath = "hey"
}