package com.edcan.howtosunrin.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityMainBinding
import com.edcan.howtosunrin.utill.user.User
import com.edcan.howtosunrin.ui.chat.ChatActivity
import com.edcan.howtosunrin.ui.qna.QnAActivity
import com.edcan.howtosunrin.ui.qna.QnAActivityViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    lateinit var currentUserData : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentUserData = intent.getSerializableExtra("userData") as User

        binding.btnMainGotoQnA.setOnClickListener {
            val intent = Intent(this, QnAActivity::class.java)
            //todo 6 currentUserData를 userData라는 이름으로 QnAActivity에 넘겨주자
            intent.putExtra("userData", currentUserData)
            startActivity(intent)

        }

        binding.btnMainGotoGroupChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("userData", currentUserData)
            startActivity(intent)
        }

        binding.imgMainIcon.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://edcan.kr"))
            startActivity(intent)
        }
    }
}