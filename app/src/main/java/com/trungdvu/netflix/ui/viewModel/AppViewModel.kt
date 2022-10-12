package com.trungdvu.netflix.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
) : ViewModel() {
    val shouldShowAppBar = mutableStateOf(false)

    val updateAppBarVisibility: (value: Boolean) -> Unit = { value ->
        shouldShowAppBar.value = value
    }
}
