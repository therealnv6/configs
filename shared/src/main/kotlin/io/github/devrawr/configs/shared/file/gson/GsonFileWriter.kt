package io.github.devrawr.configs.shared.file.gson

import com.google.gson.GsonBuilder
import io.github.devrawr.configs.shared.file.FileWriter
import java.io.File
import java.nio.file.Files

object GsonFileWriter : FileWriter
{
    private val reader = GsonFileReader
    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    override fun write(keys: Map<String, String>, file: File)
    {
        if (file.parentFile != null && !file.parentFile.exists())
        {
            file.mkdirs()
        }

        if (!file.exists())
        {
            file.createNewFile()
        }

        val writer = Files.newBufferedWriter(file.toPath())

        gson.toJson(keys, writer)

        writer.flush()
        writer.close()
    }
}