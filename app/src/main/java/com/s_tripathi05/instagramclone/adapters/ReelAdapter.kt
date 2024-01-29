package com.s_tripathi05.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s_tripathi05.instagramclone.Models.reel
import com.s_tripathi05.instagramclone.R
import com.s_tripathi05.instagramclone.databinding.ReelDgBinding
import com.squareup.picasso.Picasso

class ReelAdapter(var context: Context,var reelList: ArrayList<reel>) :RecyclerView.Adapter<ReelAdapter.ViewHolder>(){

    inner class ViewHolder(var binding : ReelDgBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ReelDgBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
     return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Picasso.get().load(reelList.get(position).profileLink).placeholder(R.drawable.user).into(holder.binding.profileImage)
        holder.binding.caption.setText(reelList.get(position).caption)
        holder.binding.videoView.setVideoPath(reelList.get(position).reelUrl)
        holder.binding.videoView.setOnPreparedListener {
            holder.binding.progressBar.visibility=android.view.View.GONE
            holder.binding.videoView.start()
        }

    }
}