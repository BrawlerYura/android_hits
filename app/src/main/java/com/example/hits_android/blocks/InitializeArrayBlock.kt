package com.example.hits_android.blocks

import com.example.hits_android.expressionParser.*

// Блок создания массива
class InitializeArrayBlock(
    override var previousID: Int = -1,
    override var nextID: Int = -1,
    override val key: String,
    override val title:String = "InitArray",
    override val isDragOverLocked:Boolean = false
):Block {
    // Название блока
    companion object {
        val BLOCK_NAME = "initArrayBlock"
    }
    override val blockName = BLOCK_NAME

    // Добавление блока в список блоков
    init {
        blockList.add(this)
    }

    var arrayName = ""   // Название массива
    var arrayType = ""   // Тип элементов массива
    var arraySize = ""   // Размер массива

    // Создание массива
    override fun runCodeBlock() {
        // Пересоздание массива
        if (variables[arrayName] != null) {
            throw Error("Чел, ты пересоздаешь переменную")
        }

        // Создание массива с элементами типа Int
        if (arrayType == Type.INT) {
            val expression = ParsingFunctions(LexicalComponents(arraySize).getTokensFromCode())
            variables[arrayName] = Variable(
                arrayName,
                "ArrayInt",
                Array(expression.parseExpression()!!.value.toString().toInt()) { 0 })
        }

        // Создание массива с элементами типа Double
        else if (arrayType == Type.DOUBLE) {
            val expression = ParsingFunctions(LexicalComponents(arraySize).getTokensFromCode())
            variables[arrayName] = Variable(
                arrayName,
                "ArrayDouble",
                Array(expression.parseExpression()!!.value.toString().toInt()) { 0.0 })
        }

        // Выполнение следующих блоков
        blockIndex++
    }

    // Тестирование без UI
    fun testBlock(name: String, type: String, size: String) {
        arrayName = name
        arrayType = type
        arraySize = size
    }

    // Возврат названия блока
    override fun getNameOfBlock(): String {
        return blockName
    }
}