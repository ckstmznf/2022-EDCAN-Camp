package com.edcan.howtosunrin.ui.userData

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityUserDataBinding
import com.edcan.howtosunrin.utill.user.UserUtil
import com.edcan.howtosunrin.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDataActivity : BaseActivity<ActivityUserDataBinding>(R.layout.activity_user_data) {
    val viewModel : UserDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        val majorArray = arrayOf("학과를 선택해주세요.", "정보보호과", "소프트웨어과", "IT경영과", "콘텐츠 디자인과")
        binding.spinnerUserDataChoiceMajor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, majorArray)

        binding.spinnerUserDataChoiceMajor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.major.value = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        binding.btnUserDataInputEnd.setOnClickListener {
            if(viewModel.name.value!!.isEmpty()){
                binding.edtUserDataInputName.error = "이름을 입력해주세요."
                return@setOnClickListener
            }
            else if (viewModel.major.value!! == 0){
                //todo 4 토스트 메시지로 학과를 선택해달라고 띄워주자
                Toast.makeText(this, "학과를 선택하세요.", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                val result = viewModel.saveUserData() //사용자의 정보를 저장한다. ->UserUtil.ResultSuccess

                withContext(Dispatchers.Main){
                    //todo 5 아래 코드를 이해하자
                    if(result == UserUtil.ResultSuccess){
                        Toast.makeText(this@UserDataActivity, "유저 등록에 성공했습니다.", Toast.LENGTH_LONG).show()
                        
                        val intent = Intent(this@UserDataActivity, MainActivity::class.java)
                        intent.putExtra("userData", viewModel.userData)
                        startActivity(intent)
                        finish()
                    }   else{
                        Toast.makeText(this@UserDataActivity, "유저 등록에 실패했습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}