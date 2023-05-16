package com.example.hits_android.blocks

import androidx.compose.ui.platform.InspectableModifier
import com.example.hits_android.expressionParser.scopes

// Блок выхода из цикла
class BreakBlock(
    override var previousID: Int = -1,
    override var nextID: Int = -1,
    override val key: String,
    override val title:String = "Break",
    override val isDragOverLocked:Boolean = true
): Block {
    // Название блока
    companion object{
        val BLOCK_NAME = "breakBlock"
    }
    override val blockName = BLOCK_NAME

    // Добавление блока в список блоков
    init {
        blockList.add(this)
    }

    // Выход из цикла
    override fun runCodeBlock() {
        // Поиск начала тела цикла
        while (blockList[blockIndex].getNameOfBlock() != WhileBlock.BLOCK_NAME) {
            try {
                blockIndex--

                if (blockList[blockIndex].getNameOfBlock() == BeginBlock.BLOCK_NAME) {
                    scopes.destoryScope()
                }
                else if (blockList[blockIndex].getNameOfBlock() == EndBlock.BLOCK_NAME) {
                    scopes.addScope(scopes.getScope())
                }
            }
            catch (e: ArrayIndexOutOfBoundsException) {
                throw Exception("Break не относится к циклу")
            }
        }

        // Выход из цикла
        (blockList[blockIndex] as WhileBlock).testBlock("0;")
        blockList[++blockIndex].runCodeBlock()
        blockIndex--
        skipBlock()
        blockIndex--
    }

    // Возврат названия блока
    override fun getNameOfBlock(): String {
        return blockName
    }
}