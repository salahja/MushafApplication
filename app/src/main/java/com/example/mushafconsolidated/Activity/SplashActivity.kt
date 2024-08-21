package com.example.mushafconsolidated.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mushafconsolidated.R


class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 3000
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) !=
            PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED || (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_NETWORK_STATE
            )
                    != PackageManager.PERMISSION_GRANTED)
        ) { //Can add more as per requirement
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WAKE_LOCK,
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_NETWORK_STATE
                ),
                123
            )
        }
    }

    override fun onCreate(sis: Bundle?) {
        super.onCreate(sis)
        //set the content view. The XML file can contain nothing but an image, such as a logo or the       app icon
        setContentView(R.layout.splash_layout)
        //we want to display the splash screen for a few seconds before it automatically
        //disappears and loads the game. So we create a thread:
        Handler().postDelayed({ //request permissions. NOTE: Copying this and the manifest will cause the app to           crash as the permissions requested aren't defined in the manifest.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermission()
            }
            //    String lang =["ss"];
            //  Locale locale = new Locale(lang);
            //  Locale.setDefault(locale);
            val config = Configuration()
            //  config.locale = locale;
            this@SplashActivity.resources.updateConfiguration(
                config,
                this@SplashActivity.resources.displayMetrics
            )
            //after three seconds, it will execute all of this code.
//as such, we then want to redirect to the master-activity
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            // Intent intent = new Intent(Splash.this.getActivity(), MainActivity.class);
            // Intent mainIntent = new Intent(Splash.this, MainActivity.class);
            //  startActivity(mainIntent);
//then we finish this class. Dispose of it as it is longer needed
            finish()
            // Intent intent = new Intent(Splash.this.getActivity(), MainActivity.class);
            // Intent mainIntent = new Intent(Splash.this, MainActivity.class);
            //  startActivity(mainIntent);
//then we finish this class. Dispose of it as it is longer needed
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    public override fun onPause() {
        super.onPause()
        finish()
    }
}