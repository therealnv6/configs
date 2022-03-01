package io.github.devrawr.configs.shared.file

abstract class FileHandler
{
    abstract val fileReader: FileReader
    abstract val fileWriter: FileWriter
}