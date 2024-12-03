package com.example.abdallah.ui.enterprise_detail.state

import com.example.abdallah.domain.models.EnterpriseDetail

data class EnterpriseDetailState(
    val enterpriseDetail:EnterpriseDetail ?= null,
    val isLoading:Boolean = false,
    val error:String = ""
)