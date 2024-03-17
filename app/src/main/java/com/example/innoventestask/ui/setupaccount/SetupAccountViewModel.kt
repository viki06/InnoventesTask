package com.example.innoventestask.ui.setupaccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innoventestask.model.Model
import com.example.innoventestask.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SetupAccountViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _isUserDetailsSaved: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val isUserDetailsSaved get() = _isUserDetailsSaved

    private val _isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val isLoading get() = _isLoading

    fun isValidDate(txtDate: String, txtMonth: String, txtYear: String): Boolean {

        if (txtDate.isEmpty() || txtMonth.isEmpty() || txtYear.isEmpty())
            return false

        val date = txtDate.toInt()

        val month = txtMonth.toInt()

        val year = txtYear.toInt()

        if (month < 1 || month > 12) return false

        val maxDays = when (month) {
            2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }

        return date in 1..maxDays

    }

    fun isValidPAN(pan: String): Boolean =
        Regex("[A-Z]{5}[0-9]{4}[A-Z]").matches(pan)

    fun saveUserDetails(
        panNumber: String,
        date: String,
        month: String,
        year: String
    ) {

        _isLoading.postValue(true)

        viewModelScope.launch {

            try {

                _isUserDetailsSaved.postValue(
                    repository.savePanDetails(
                        userDetails = Model.UserDetails(
                            panNumber = panNumber,
                            birthdate = "$date/$month/$year"
                        )
                    )
                )

                _isLoading.postValue(false)

            }catch (e : Exception){

                _isUserDetailsSaved.postValue(false)

            }



        }

    }

}