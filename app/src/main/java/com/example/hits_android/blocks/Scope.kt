package com.example.hits_android.blocks

// Область видимости переменных
class Scope() {
    // Список всех областей видимости
    private var scopesList = mutableListOf<MutableList<String>>()

    // Добавление текущей области видимости
    fun addScope(scope: MutableList<String>) {
        scopesList.add(scope)
    }

    // Уничтожение текущей области видимости
    fun destoryScope() {
        scopesList.removeAt(scopesList.size - 1)
    }

    // Возврат текущей области видимости
    fun getScope(): MutableList<String> {
        return scopesList[scopesList.size - 1]
    }

    // Проверка наличия переменных в текущей области видимости
    fun isEmpty() : Boolean {
        return scopesList.size == 0
    }
}