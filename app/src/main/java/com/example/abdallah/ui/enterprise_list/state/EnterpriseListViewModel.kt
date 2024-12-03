package com.example.abdallah.ui.enterprise_list.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abdallah.common.Resource
import com.example.abdallah.domain.use_cases.GetEnterprisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EnterpriseListViewModel @Inject constructor(
    private val getAllEnterprises: GetEnterprisesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(EnterpriseListState())
    val state : State<EnterpriseListState> = _state

    init {
        getEnterprises()
    }

    private fun getEnterprises() {
        getAllEnterprises().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = EnterpriseListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = EnterpriseListState(
                        enterprise = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = EnterpriseListState(
                        error = result.message ?: "Unexpected error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}