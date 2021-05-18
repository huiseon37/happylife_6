package com.example.happylife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.happylife.databinding.ActivityNicknameBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NicknameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNicknameBinding

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var databaseReference: DatabaseReference = firebaseDatabase.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNicknameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSigninNickname.setOnClickListener {
            if(binding.etNickname.text.toString().trim().isNotEmpty()) {

                databaseReference =
                    firebaseDatabase.getReference("users") // DB 테이블 연결

                val userInfo = UserInfo(
                    intent.getStringExtra("id"),
                    intent.getStringExtra("pwd"),
                    intent.getStringExtra("name"),
                    intent.getStringExtra("phone"),
                    binding.etNickname.text.toString()
                )
                databaseReference.child(binding.etNickname.text.toString())
                    .setValue(userInfo) // 랜덤한 문자열을 key로 할당 후, 목록 생성

                val intent = Intent(this, JobActivity::class.java)

                // 다음 액티비티에 사용자 이름 넘겨줌
                intent.putExtra("nickname", binding.etNickname.text.toString())
                startActivity(intent)
            }
            else {
                Toast.makeText(applicationContext, "닉네임을 입력해주세요", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}