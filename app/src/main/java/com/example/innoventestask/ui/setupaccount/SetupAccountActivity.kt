package com.example.innoventestask.ui.setupaccount

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.innoventestask.R
import com.example.innoventestask.databinding.ActivitySetupAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupAccountActivity : AppCompatActivity() {

    private var _binding: ActivitySetupAccountBinding? = null

    private val mBinding get() = _binding!!

    private val mViewModel: SetupAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        _binding = ActivitySetupAccountBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        addOnClickListener()

        textWatcher()

        addDataObserver()

    }

    private fun addDataObserver() {

        mViewModel.isLoading.observe(this) { isLoading ->

            mBinding.progressBarLayout.visibility = if (isLoading) View.VISIBLE else View.GONE

        }

        mViewModel.isUserDetailsSaved.observe(this) { isUserDetailsSaved ->

            if (isUserDetailsSaved)
                userDetailsSaved()
            else
                Toast.makeText(
                    this,
                    R.string.feedback_please_try_again,
                    Toast.LENGTH_SHORT
                ).show()

        }

    }

    private fun textWatcher() {

        mBinding.inputPan.doAfterTextChanged {

            updateActionButton()

        }

        mBinding.inputDate.doAfterTextChanged {

            updateActionButton()

        }

        mBinding.inputMonth.doAfterTextChanged {

            updateActionButton()

        }

        mBinding.inputYear.doAfterTextChanged {

            updateActionButton()

        }

    }

    private fun updateActionButton() {

        mBinding.actionNext.isEnabled = mViewModel.isValidDate(
            txtDate = mBinding.inputDate.text.toString(),
            txtMonth = mBinding.inputMonth.text.toString(),
            txtYear = mBinding.inputYear.text.toString()
        ) && mViewModel.isValidPAN(
            pan = mBinding.inputPan.text.toString()
        )

    }

    private fun addOnClickListener() {

        mBinding.actionNext.setOnClickListener {

            /* mViewModel.saveUserDetails(
                 panNumber = mBinding.inputPan.text.toString(),
                 date = mBinding.inputDate.text.toString(),
                 month = mBinding.inputMonth.text.toString(),
                 year = mBinding.inputYear.text.toString()
             )*/

            userDetailsSaved()

        }

        mBinding.actionNoPan.setOnClickListener {

            finish()

        }

    }

    private fun userDetailsSaved() {

        Toast.makeText(
            this,
            R.string.feedback_details_submitted_success,
            Toast.LENGTH_SHORT
        ).show()

        finish()

    }

    companion object {

        fun getIntent(context: Context): Intent = Intent(context, SetupAccountActivity::class.java)

    }

}