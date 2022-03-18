package com.example.promly

import android.app.Application
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker


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