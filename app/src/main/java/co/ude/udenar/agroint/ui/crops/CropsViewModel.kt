package co.ude.udenar.agroint.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CropsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Crops"
    }
    val text: LiveData<String> = _text
}