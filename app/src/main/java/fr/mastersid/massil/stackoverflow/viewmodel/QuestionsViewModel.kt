package fr.mastersid.massil.stackoverflow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.massil.stackoverflow.db.Question
import fr.mastersid.massil.stackoverflow.data.QuestionsResponse
import fr.mastersid.massil.stackoverflow.repository.QuestionsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val questionsRepository: QuestionsRepository
) : ViewModel() {

    private val _questionsList: MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    val questionsList: LiveData<List<Question>> = _questionsList

    private val _isUpdating = MutableLiveData(false)
    val isUpdating: LiveData<Boolean> = _isUpdating

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun updateQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            questionsRepository.updateQuestionsInfo()
        }
    }

    fun clearError() {
        _errorMessage.postValue(null)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            questionsRepository.questionsResponse.collect { response ->
                when (response) {
                    is QuestionsResponse.Pending -> _isUpdating.postValue(true)
                    is QuestionsResponse.Success -> {
                        _questionsList.postValue(response.list)
                        _isUpdating.postValue(false)
                    }
                    is QuestionsResponse.Error -> {
                        _errorMessage.postValue(response.message)
                        _isUpdating.postValue(false)
                    }
                }
            }
        }
    }
}