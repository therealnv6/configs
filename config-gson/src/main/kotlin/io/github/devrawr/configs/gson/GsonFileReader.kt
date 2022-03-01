package io.github.devrawr.configs.gson

import com.google.gson.*
import io.github.devrawr.configs.shared.file.FileReader
import java.io.File
import java.nio.file.Files

object GsonFileReader : FileReader
{
    override fun read(file: File): Map<String, String>
    {
        if (file.parentFile != null && !file.parentFile.exists())
        {
            file.mkdirs()
        }

        if (!file.exists())
        {
            file.createNewFile()
        }


        val reader = Files.newBufferedReader(file.toPath())
        val map = hashMapOf<String, String>()

        val element = JsonParser.parseReader(reader)

        if (element != null && element.isJsonObject)
        {
            for (mutableEntry in element.asJsonObject.entrySet())
            {
                map[mutableEntry.key] = mutableEntry.value.asString
            }
        }

        reader.close()

        return map
    }
}