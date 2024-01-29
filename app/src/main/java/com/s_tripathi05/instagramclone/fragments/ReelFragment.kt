package com.s_tripathi05.instagramclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.s_tripathi05.instagramclone.Models.reel
import com.s_tripathi05.instagramclone.adapters.ReelAdapter
import com.s_tripathi05.instagramclone.databinding.FragmentReelBinding
import com.s_tripathi05.instagramclone.utils.REEL


class ReelFragment : Fragment() {
  private lateinit var binding: FragmentReelBinding
lateinit var  adapter:ReelAdapter
var reelList=ArrayList<reel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentReelBinding.inflate(inflater, container, false)
        adapter= ReelAdapter(requireContext(),reelList)
        binding.viewPager.adapter=adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {
            var tempList=ArrayList<reel>()
            reelList.clear()
            for (i in it.documents){
                var reel=i.toObject<reel>()!!
                tempList.add(reel)
                reelList.reverse()
                adapter.notifyDataSetChanged()
            }
            reelList.addAll(tempList)
        }


        return binding.root
    }

    companion object {

    }
}