package com.example.wheelview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wheelview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnShowBottomSheetDialog.setOnClickListener {
            BottomSheetDialogMenuFragment.getInstance()
                .show(supportFragmentManager, "TAG")
        }
    }
}