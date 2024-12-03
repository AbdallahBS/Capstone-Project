package com.example.abdallah.ui.enterprise_list.state


import com.example.abdallah.domain.models.Enterprise

data class EnterpriseListState(
    val enterprise:  List<Enterprise> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)