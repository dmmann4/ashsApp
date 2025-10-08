package com.example.perfectfit_kpm.Models

import com.example.perfectfit_kpm.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationManager {
    private val stack = ArrayDeque<Screen>()
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen


    init {
        stack.addLast(Screen.Home)
    }

    fun navigateTo(screen: Screen) {
        stack.addLast(screen)
        _currentScreen.value = screen
    }

    fun canGoBack(): Boolean = stack.size > 1

    fun goBack() {
        if (stack.size > 1) {
            stack.removeLast()
            _currentScreen.value = stack.last()
        }
    }

    fun resetTo(screen: Screen) {
        stack.clear()
        stack.addLast(screen)
        _currentScreen.value = screen
    }
}