package com.s_tripathi05.instagramclone.Post

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.s_tripathi05.instagramclone.HomeActivity
import com.s_tripathi05.instagramclone.Models.Post
import com.s_tripathi05.instagramclone.Models.User
import com.s_tripathi05.instagramclone.databinding.ActivityPostBinding
import com.s_tripathi05.instagramclone.utils.POST
import com.s_tripathi05.instagramclone.utils.POST_FOLDER
import com.s_tripathi05.instagramclone.utils.USER_NODE
import com.s_tripathi05.instagramclone.utils.uploadImage


class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageUrl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { url ->
            uploadImage(uri, POST_FOLDER) { url ->
                if (url != null) {
                    binding.selectImage.setImageURI(uri)
                    imageUrl = url
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }

        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }


        binding.postButton.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                .addOnSuccessListener {
                    var user = it.toObject<User>()!!

                    val post: Post = Post(
                        postUrl = imageUrl!!,
                        caption = binding.Caption.editText?.text.toString(),
                       name=user.name.toString(),
                        time = System.currentTimeMillis().toString()
                    )



                    Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document()
                            .set(post)
                            .addOnSuccessListener {

                                startActivity(Intent(this@PostActivity, HomeActivity::class.java))
                                finish()
                            }
                    }
                }
        }
    }
}