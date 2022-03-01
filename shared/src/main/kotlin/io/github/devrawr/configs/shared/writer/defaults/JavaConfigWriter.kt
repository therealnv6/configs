package io.github.devrawr.configs.shared.writer.defaults

import io.github.devrawr.configs.shared.ConfigCreator
import io.github.devrawr.configs.shared.annotation.Path
import io.github.devrawr.configs.shared.file.FileWriter
import io.github.devrawr.configs.shared.writer.ConfigWriter
import java.io.File
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.kotlinProperty

object JavaConfigWriter : ConfigWriter
{
    override fun write(
        origin: Any,
        writer: FileWriter,
        file: File
    )
    {
        val clazz = origin.javaClass
        val map = hashMapOf<String, String>()

        for (field in clazz.declaredFields)
        {
            if (field.name == "INSTANCE")
            {
                continue
            }

            field.isAccessible = true

            val annotation = field.kotlinProperty?.findAnnotation()
                ?: field.getAnnotation(Path::class.java)

            val path = annotation?.value
                ?: ConfigCreator.namingScheme.renameFieldName(field.name)

            val wrapper = ConfigCreator.retrieveWrapper(field.type)

            map[path] = wrapper.wrapCasted(field.get(origin))
        }

        writer.write(
            map,
            file
        )
    }
}