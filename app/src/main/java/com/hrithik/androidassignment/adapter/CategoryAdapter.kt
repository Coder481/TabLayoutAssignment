package com.hrithik.androidassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hrithik.androidassignment.databinding.LayoutCategoryBinding
import com.hrithik.androidassignment.models.DTO
import com.hrithik.androidassignment.models.Data


class CategoryAdapter(private val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val list = ArrayList<Data>()
    private var dto = DTO()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = LayoutCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], dto, context)
    }

    override fun getItemCount(): Int = list.size

    fun setCardGroupData(themeDto: DTO) {
        list.clear()
        list.addAll(themeDto.data as ArrayList<Data>)
        dto = themeDto
        notifyDataSetChanged()
    }


    class ViewHolder(binding: LayoutCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        private val b = binding
        fun bind(data : Data, dto: DTO, context: Context){
            val catAdapter = CategoryImageAdapter(data.themes,dto.thumb_small_path, context)
            val layManager = GridLayoutManager(context,2)
            b.rv.apply{
                layoutManager = layManager
                adapter = catAdapter
            }
        }
    }

}
