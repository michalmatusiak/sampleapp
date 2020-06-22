package pl.matusiak.sampleapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter


object DataBindingAdapters {

    @BindingAdapter("imageResource")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}