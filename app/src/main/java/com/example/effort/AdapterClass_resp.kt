package com.example.effort

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item_resp.view.*

class AdapterClass_resp(private var itemList: List<Item_resp>): RecyclerView.Adapter<AdapterClass_resp.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView_inflater = LayoutInflater.from(parent.context).
            inflate(R.layout.item_resp,parent,false)

        return ItemViewHolder(itemView_inflater)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val currentItem = itemList[position]

        //holder.imageView.setImageResource(currentItem.imageResource)
        //holder.textView.text = currentItem.text1

        holder.rr.text = currentItem.rr
        holder.analysis.text = currentItem.analysis

        if(currentItem.analysis.equals("Very High")){

            holder.respImg.setImageResource(R.drawable.ic_face_very_high)
        }

        if(currentItem.analysis.equals("Very Low") or currentItem.analysis.equals("High") ){

            holder.respImg.setImageResource(R.drawable.ic_high)
        }

        if(currentItem.analysis.equals("Slight Low") or currentItem.analysis.equals("Slight High") ){

            holder.respImg.setImageResource(R.drawable.ic_face)
        }

        if(currentItem.analysis.equals("Normal")){

            holder.respImg.setImageResource(R.drawable.ic_face_right)
        }




    }

    override fun getItemCount() = itemList.size


    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        //val imageView: ImageView = itemView.image_view
        //val textView: TextView = itemView.text_view
        //val checkBox: CheckBox = itemView.check_box
        val respImg: ImageView = itemView.respImg
        val rr_title: TextView = itemView.rr_title
        val rr: TextView = itemView.rr
        val rr_unit: TextView = itemView.rr_unit
        val analysis_title: TextView = itemView.analysis_title
        val analysis: TextView = itemView.analysis
    }
}