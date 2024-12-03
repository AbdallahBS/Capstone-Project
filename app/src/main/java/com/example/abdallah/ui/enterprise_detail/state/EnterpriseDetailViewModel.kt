package com.example.abdallah.ui.enterprise_detail.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abdallah.common.Constants
import com.example.abdallah.common.Resource
import com.example.abdallah.domain.use_cases.GetEnterpriseDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EnterpriseDetailViewModel @Inject constructor(
    private val getEnterpriseDetailUseCase:GetEnterpriseDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(EnterpriseDetailState())
    val state: State<EnterpriseDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ENTERPRISE_ID)?.let {
            getEnterpriseDetail(it)
        }
    }

    private fun getEnterpriseDetail(enterpriseId:String) {
        getEnterpriseDetailUseCase(enterpriseId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = EnterpriseDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = EnterpriseDetailState(
                        enterpriseDetail = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = EnterpriseDetailState(
                        error = result.message ?: "Unexpected error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}