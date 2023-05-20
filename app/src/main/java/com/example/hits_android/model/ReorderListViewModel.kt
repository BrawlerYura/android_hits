package com.example.hits_android.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hits_android.blocks.AssignmentBlock
import com.example.hits_android.blocks.BeginBlock
import com.example.hits_android.blocks.Block
import com.example.hits_android.blocks.BreakBlock
import com.example.hits_android.blocks.ContinueBlock
import com.example.hits_android.blocks.ElseBlock
import com.example.hits_android.blocks.EndBlock
import com.example.hits_android.blocks.FinishProgramBlock
import com.example.hits_android.blocks.IfBlock
import com.example.hits_android.blocks.InitializeArrayBlock
import com.example.hits_android.blocks.InitializeVarBlock
import com.example.hits_android.blocks.MainBlock
import com.example.hits_android.blocks.OutputBlock
import com.example.hits_android.blocks.WhileBlock
import com.example.hits_android.blocks.blockList
import com.example.hits_android.ui.theme.MyAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.burnoutcrew.reorderable.ItemPosition

class ReorderListViewModel : ViewModel() {

    var _theme = MutableStateFlow(MyAppTheme.DarkGreen)

    val theme: StateFlow<MyAppTheme> = _theme.asStateFlow()

    var codeBlocksList by mutableStateOf(
        listOf(
            MainBlock(key = "0", isDragOverLocked = true, title = "Start program"),
            FinishProgramBlock(key = "1", isDragOverLocked = true, title = "End program")
        )
    )
    var blockSelectionList by mutableStateOf(
        listOf(
            InitializeVarBlock(key = "0"),
            WhileBlock(key = "1"),
            IfBlock(key = "2"),
            ElseBlock(key = "3"),
            ContinueBlock(key = "4"),
            BreakBlock(key = "5"),
            OutputBlock(key = "6"),
            InitializeArrayBlock(key = "7"),
            AssignmentBlock(key = "8")
        )
    )

    var keyCount = 1

    init {
        blockList.clear()
    }

    fun moveBlock(from: ItemPosition, to: ItemPosition) {
        Log.d("a", "${codeBlocksList}")
        codeBlocksList = codeBlocksList.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }

        blockList = codeBlocksList.slice(1..(codeBlocksList.size - 2)).toMutableList()
    }

    fun isDogDragOverEnabled(draggedOver: ItemPosition, dragging: ItemPosition) =
        codeBlocksList.getOrNull(draggedOver.index)?.isDragOverLocked != true

    fun addBlock(item: Block) {
        codeBlocksList = codeBlocksList
            .toMutableList()
            .apply {
                add(
                    codeBlocksList.size - 1,
                    when (item.blockName) {
                        "assignmentBlock" -> {
                            AssignmentBlock(key = "${++keyCount}")
                        }

                        "breakBlock" -> {
                            BreakBlock(key = "${++keyCount}")
                        }

                        "continueBlock" -> {
                            ContinueBlock(key = "${++keyCount}")
                        }

                        "ElseBlock" -> {
                            ElseBlock(key = "${++keyCount}")
                        }

                        "IfBlock" -> {
                            IfBlock(key = "${++keyCount}")
                        }

                        "initArrayBlock" -> {
                            InitializeArrayBlock(key = "${++keyCount}")
                        }

                        "initVarBlock" -> {
                            InitializeVarBlock(key = "${++keyCount}")
                        }

                        "outputBlock" -> {
                            OutputBlock(key = "${++keyCount}")
                        }

                        "WhileBlock" -> {
                            WhileBlock(key = "${++keyCount}")
                        }

                        else -> {
                            AssignmentBlock(key = "${++keyCount}")
                        }
                    }
                )
                if (item.blockName == "ElseBlock" ||
                    item.blockName == "IfBlock" ||
                    item.blockName == "WhileBlock"
                ) {
                    add(
                        codeBlocksList.size,
                        EndBlock(key = "${keyCount + 2}")
                    )
                    add(
                        codeBlocksList.size,
                        BeginBlock(key = "${keyCount + 1}")
                    )
                    keyCount += 2
                }
            }
    }

    fun setCurrentTheme(newTheme: MyAppTheme) {
        _theme.value = newTheme
    }
}