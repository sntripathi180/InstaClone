package com.s_tripathi05.instagramclone.Post

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.s_tripathi05.instagramclone.HomeActivity
import com.s_tripathi05.instagramclone.Models.User
import com.s_tripathi05.instagramclone.Models.reel
import com.s_tripathi05.instagramclone.databinding.ActivityReelsBinding
import com.s_tripathi05.instagramclone.utils.REEL
import com.s_tripathi05.instagramclone.utils.REEL_FOLDER
import com.s_tripathi05.instagramclone.utils.USER_NODE
import com.s_tripathi05.instagramclone.utils.uploadVideo

class ReelsActivity : AppCompatActivity() {

    val binding by lazy {

        ActivityReelsBinding.inflate(layoutInflater)
    }
    private lateinit var videoUrl: String
    lateinit var progressDialog:ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { url ->
            uploadVideo(uri, REEL_FOLDER,progressDialog) { url ->
                if (url != null) {

                    videoUrl = url
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog=ProgressDialog(this)


        binding.selectReel.setOnClickListener {
            launcher.launch("video/*")
        }

        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }
        binding.postButton.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:User=it.toObject<User>()!!
                val reel: reel = reel(videoUrl!!, binding.Caption.editText?.text.toString(),user.image!!)
                Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document()
                        .set(reel).addOnSuccessListener {

                            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
                            finish()
                        }

                }
            }


        }
    }
}