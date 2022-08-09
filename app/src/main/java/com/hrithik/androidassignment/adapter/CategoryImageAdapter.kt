package com.hrithik.androidassignment.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hrithik.androidassignment.databinding.LayoutItemCategoryImageBinding
import com.hrithik.androidassignment.models.Theme

class CategoryImageAdapter(
    private val themes: List<Theme>,
    private val thumbNailSmall: String,
    private val context: Context
) : RecyclerView.Adapter<CategoryImageAdapter.ImageViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val b = LayoutItemCategoryImageBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ImageViewHolder(b)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val theme = themes[position]
        val name = theme.Theme_Name
        val url = "$thumbNailSmall${theme.Thumnail_Small}"
        holder.bind(name, url, context)
    }

    override fun getItemCount(): Int = themes.size


    class ImageViewHolder(binding : LayoutItemCategoryImageBinding) : RecyclerView.ViewHolder(binding.root){
        private val b = binding
        fun bind(imgName : String, imgUrl : String, context: Context){

            b.tvImageName.text = imgName

            Glide.with(context)
                .load(imgUrl)
                .into(object : CustomTarget<Drawable>(){
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        b.ivThumbNail.background = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        Log.d("","Loading Image: onLoadCleared")
                    }
                    override fun onLoadFailed(errorDrawable: Drawable?) {}
                })
        }
    }

}