package io.github.devrawr.configs.shared

import io.github.devrawr.configs.shared.file.FileHandler
import io.github.devrawr.configs.shared.file.FileReader
import io.github.devrawr.configs.shared.file.FileWriter
import io.github.devrawr.configs.shared.loader.ConfigLoader
import io.github.devrawr.configs.shared.loader.defaults.JavaConfigLoader
import io.github.devrawr.configs.shared.naming.NamingScheme
import io.github.devrawr.configs.shared.naming.type.SnakeCaseNamingScheme
import io.github.devrawr.configs.shared.util.ObjectInstanceUtil.getOrCreateInstance
import io.github.devrawr.configs.shared.wrapper.ObjectWrapper

import io.github.devrawr.configs.shared.writer.ConfigWriter
import io.github.devrawr.configs.shared.writer.defaults.JavaConfigWriter
import java.io.File
import java.lang.Exception

object ConfigCreator
{
    val wrappers = hashMapOf<Class<out Any>, ObjectWrapper<*>>()

    var loader: ConfigLoader = JavaConfigLoader
    var writer: ConfigWriter = JavaConfigWriter

    var namingScheme: NamingScheme = SnakeCaseNamingScheme
    var createDefaultWrapper: ((Class<*>) -> ObjectWrapper<*>)? = null

    lateinit var fileReader: FileReader
    lateinit var fileWriter: FileWriter

    inline fun <reified K : FileReader, reified V : FileWriter> initialize(): ConfigCreator
    {
        return this.apply {
            this.fileReader = K::class.getOrCreateInstance()
            this.fileWriter = V::class.getOrCreateInstance()
        }
    }

    inline fun <reified T : FileHandler> initializeWithHandler(): ConfigCreator
    {
        return this.apply {
            val handler = T::class.getOrCreateInstance<T>()

            this.fileReader = handler.fileReader
            this.fileWriter = handler.fileWriter
        }
    }

    fun <T> retrieveWrapper(type: Class<T>): ObjectWrapper<T>
    {
        return (wrappers[type]
            ?: createDefaultWrapper?.let {
                it(type)
            }) as ObjectWrapper<T>
    }

    inline fun <reified T : Any> create(
        name: String? = null
    ): ConfigCreator
    {
        return this.apply {
            val config = T::class.getOrCreateInstance<T>()
            val keys = hashMapOf<String, String>()

            try
            {
                keys["name"] = T::class.java.getDeclaredField("configName").get(config) as String
            } catch (ignored: Exception)
            {
                keys["name"] = name!!
            }

            val file = File(keys["name"]!!)

            this.loader.load(
                origin = config,
                reader = this.fileReader,
                file = file
            )

            this.writer.write(
                origin = config,
                writer = this.fileWriter,
                file = file
            )
        }
    }
}