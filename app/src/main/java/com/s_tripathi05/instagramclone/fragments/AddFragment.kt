package com.s_tripathi05.instagramclone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.s_tripathi05.instagramclone.Post.PostActivity
import com.s_tripathi05.instagramclone.Post.ReelsActivity
import com.s_tripathi05.instagramclone.R
import com.s_tripathi05.instagramclone.databinding.FragmentAddBinding
import com.s_tripathi05.instagramclone.databinding.FragmentProfileBinding


class AddFragment : BottomSheetDialogFragment() {
  private lateinit var binding: FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener{
            activity?.startActivity(Intent(requireContext(),PostActivity::class.java))
            activity?.finish()
        }
        binding.reel.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),ReelsActivity::class.java))
        }
        return binding.root




    }

    companion object {

    }
}