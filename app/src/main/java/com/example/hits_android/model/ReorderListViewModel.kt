package com.example.hits_android.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hits_android.blocks.*
import org.burnoutcrew.reorderable.ItemPosition

class ReorderListViewModel: ViewModel() {
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

    init {
        blockList.clear()
    }

    fun moveBlock(from: ItemPosition, to: ItemPosition) {
        codeBlocksList = codeBlocksList.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
        println("s "+ "${codeBlocksList.size}")

        blockList = codeBlocksList.slice(1..(codeBlocksList.size - 2)).toMutableList()
    }

    fun isDogDragOverEnabled(draggedOver: ItemPosition, dragging: ItemPosition) = codeBlocksList.getOrNull(draggedOver.index)?.isDragOverLocked != true
}