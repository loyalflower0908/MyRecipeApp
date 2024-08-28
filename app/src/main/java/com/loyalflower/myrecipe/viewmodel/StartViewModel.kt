package com.loyalflower.myrecipe.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

//시작 화면 뷰모델
@HiltViewModel
class StartViewModel@Inject constructor(): ViewModel() {
    private val _toHome = mutableStateOf(false)
    val toHome = _toHome

    init {
        // ViewModel이 초기화될 때 타이머를 시작
        startTimer()
    }

    //타이머가 다 되면 홈 스크린으로 가기 위한 트리거 true
    private fun startTimer() {
        viewModelScope.launch {
            delay(1600)  // 대기
            _toHome.value = true  // 상태 변경
        }
    }
}