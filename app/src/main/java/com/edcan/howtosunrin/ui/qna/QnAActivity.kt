package com.edcan.howtosunrin.ui.qna

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityQnaBinding

class QnAActivity : BaseActivity<ActivityQnaBinding>(R.layout.activity_qna) {
    val viewModel : QnAActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        binding.imgQnaPrev.setOnClickListener {
            finish()
        }

        binding.imgQnaEdcanIcon.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://edcan.kr"))
            startActivity(intent)
        }
    }
}