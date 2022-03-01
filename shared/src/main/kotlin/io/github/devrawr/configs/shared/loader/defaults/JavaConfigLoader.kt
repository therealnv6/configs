package io.github.devrawr.configs.shared.loader.defaults

import io.github.devrawr.configs.shared.ConfigCreator
import io.github.devrawr.configs.shared.annotation.Path
import io.github.devrawr.configs.shared.loader.ConfigLoader
import io.github.devrawr.configs.shared.file.FileReader
import java.io.File
import java.lang.reflect.Field
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.kotlinProperty

object JavaConfigLoader : ConfigLoader
{
    override fun load(
        origin: Any,
        reader: FileReader,
        file: File,
    ): Map<Field, Any>
    {
        val map = hashMapOf<Field, Any>()
        val clazz = origin.javaClass

        val content = reader.read(file)

        for (field in clazz.declaredFields)
        {
            field.isAccessible = true
            
            val annotation = field.kotlinProperty?.findAnnotation()
                ?: field.getAnnotation(Path::class.java)

            val path = annotation?.value
                ?: ConfigCreator.namingScheme.renameFieldName(field.name)

            val data = content[path]

            if (data != null)
            {
                val wrapper = ConfigCreator.retrieveWrapper(field.type)

                field.set(
                    this,
                    wrapper.wrap(
                        data
                    )
                )
            }
        }

        return map
    }
}