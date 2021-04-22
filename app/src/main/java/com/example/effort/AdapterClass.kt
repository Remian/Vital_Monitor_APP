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

class AdapterClass(private var itemList: List<Item>): RecyclerView.Adapter<AdapterClass.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView_inflater = LayoutInflater.from(parent.context).
                inflate(R.layout.item,parent,false)

        return ItemViewHolder(itemView_inflater)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val currentItem = itemList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView.text = currentItem.text1


        holder.itemView.setOnClickListener {

            val pref = (holder.itemView.context as AppCompatActivity).
                getSharedPreferences("mark",Context.MODE_PRIVATE)

            var score: Int = pref.getInt("score",0)
            val editor = pref.edit()



            if(currentItem.state == 0){
                holder.checkBox.isChecked = true
                currentItem.state = 1
                score = score.plus(currentItem.mark)

            }
            else{
                holder.checkBox.isChecked = false
                currentItem.state = 0
                score = score.minus(currentItem.mark)

            }

            /**holder.itemView.visibility = View.GONE
            notifyDataSetChanged()
            itemList.drop(position)**/


            editor.putInt("score", score)
            editor.apply()

        }





    }

    override fun getItemCount() = itemList.size

    /**fun removeItem(position: Int) {
        itemList.remove(position)
        notifyDataSetChanged()
    }**/




    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.image_view
        val textView: TextView = itemView.text_view
        val checkBox: CheckBox = itemView.check_box
    }
}