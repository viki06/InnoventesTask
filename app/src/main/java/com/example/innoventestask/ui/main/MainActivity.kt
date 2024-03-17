package com.example.innoventestask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.innoventestask.databinding.ActivityMainBinding
import com.example.innoventestask.ui.setupaccount.SetupAccountActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mBinding.actionSetupBankAccount.setOnClickListener {

            startActivity(
                SetupAccountActivity.getIntent(context = this)
            )

        }

    }

}