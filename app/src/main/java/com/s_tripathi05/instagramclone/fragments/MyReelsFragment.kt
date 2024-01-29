package com.s_tripathi05.instagramclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.s_tripathi05.instagramclone.Models.reel
import com.s_tripathi05.instagramclone.adapters.MyReelAdapter
import com.s_tripathi05.instagramclone.databinding.FragmentMyReelsBinding


class MyReelsFragment : Fragment() {

private lateinit var binding: FragmentMyReelsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding=FragmentMyReelsBinding.inflate(inflater, container, false)
        var reelList=ArrayList<reel>()
        var adapter= MyReelAdapter(requireContext(),reelList)
        binding.rv.layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ReelFragment).get().addOnSuccessListener {
            var tempList= arrayListOf<reel>()
            for(i in it.documents){
                var reel: reel =i.toObject<reel>()!!
                tempList.add(reel)

            }
         reelList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}