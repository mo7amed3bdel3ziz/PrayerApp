package com.example.prayerapplication.ui

import androidx.lifecycle.ViewModel
import com.example.prayerapplication.network.ApiService
import com.example.prayerapplication.network.wrapWithFlowApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class AladhanViewModel

@Inject constructor(
    private val api: ApiService
)

    : ViewModel() {

    fun AddLocationPointToUser(latitude: Double, longitude: Double, method: String) = wrapWithFlowApi {
        api.GetDetailsAPI(latitude, longitude, method)
    }.flowOn(Dispatchers.IO)

    fun GetQibla() = wrapWithFlowApi {
        api.GetQiblaAPI()
    }.flowOn(Dispatchers.IO)
}
