package com.victor.androidjobtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.victor.androidjobtest.databinding.ItemImageBinding

class ImgurImageAdapter(
    private var list: List<String>
): RecyclerView.Adapter<ImgurImageAdapter.ImgurImageViewHolder>() {

    fun addList(list: MutableList<String>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class ImgurImageViewHolder(val binding: ItemImageBinding): ViewHolder(binding.root){

        fun bind(url: String){
            Picasso.get()
                .load(url)
                .resize(TARGET_WIDTH, TARGET_HEIGHT)
                .centerCrop()
                .into(binding.imgCatItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgurImageViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = ItemImageBinding.inflate(
            layoutInflater,parent,false
        )



        return ImgurImageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImgurImageViewHolder, position: Int) {
        val url = list[position]
        holder.bind(url)
    }

    companion object{
        const val TARGET_WIDTH = 120
        const val TARGET_HEIGHT = 150
    }

}