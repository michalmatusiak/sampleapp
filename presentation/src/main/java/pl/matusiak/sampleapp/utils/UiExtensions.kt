package pl.matusiak.sampleapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView

fun AutoCompleteTextView.onTextChanged(onTextChanged: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged(p0.toString())
        }
    })
}