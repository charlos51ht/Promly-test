package com.example.promly

import android.app.Application
import android.view.WindowManager
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPhotoAdapter


class UnsplashApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        var photo_picker = UnsplashPhotoPicker.init(
            this, // application
            "ozn6JHWcOkRQZJBTfIAMh0hNON4BIn7dG8HgXc-DouE",
            "fDnCs3skmSjLQHnUOH-6dtIVRd3cHfR_SN6tEq6v07M"
        )
    }
}