package com.example.effort

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_startup.view.*

class AdapterClass_Startup(private val list_startup: List<Item_startup>):
    RecyclerView.Adapter<AdapterClass_Startup.AdapterClass_Startup_ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterClass_Startup_ViewHolder {

        val itemViewStartup = LayoutInflater.from(parent.context).
                inflate(R.layout.item_startup,parent,false)

        return AdapterClass_Startup_ViewHolder(itemViewStartup)

    }

    override fun onBindViewHolder(holder: AdapterClass_Startup_ViewHolder, position: Int) {

        val currentItem_startup = list_startup[position]

        holder.imageView_startup_1.setImageResource(currentItem_startup.imageResource_startup_1)
        holder.textView_startup_1.text = currentItem_startup.text_startup_1

        holder.imageView_startup_2.setImageResource(currentItem_startup.imageResource_startup_2)
        holder.textView_startup_2.text = currentItem_startup.text_startup_2

        holder.card_view_1.setOnClickListener {

            if(currentItem_startup.text_startup_1.equals("Get Quick \n" +
                        "Assesment")){

                val fragment = Frag_1()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_1.equals("Check Risk \n")){

                val fragment = Frag_risk_1()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()

                "RR Rate \n Measurement"
            }

            if(currentItem_startup.text_startup_1.equals("Respiratory Rate\n")){

                val fragment = Frag_rr_main()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()


            }

            if(currentItem_startup.text_startup_1.equals("Temperature records\n")){

                val fragment = Frag_temp()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_1.equals("Oxygen Saturation Records\n")){

                val fragment = Frag_sp()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_1.equals("Graphs\n")){

                val fragment = Frag_graph()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_1.equals("About\n")){

                val fragment = Frag_about()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_1.equals("Contact\n")){

                val fragment = Frag_contact()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }


        }

        holder.card_view_2.setOnClickListener {

            if(currentItem_startup.text_startup_2.equals("Get Quick \n" +
                        "Assesment")){

                val fragment = Frag_1()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_2.equals("Check Risk \n")){

                val fragment = Frag_risk_1()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()

                "RR Rate \n Measurement"
            }

            if(currentItem_startup.text_startup_2.equals("Respiratory Rate\n")){

                val fragment = Frag_rr_main()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()


            }

            if(currentItem_startup.text_startup_2.equals("Temperature records\n")){

                val fragment = Frag_temp()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_2.equals("Oxygen Saturation Records\n")){

                val fragment = Frag_sp()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_2.equals("Graphs\n")){

                val fragment = Frag_graph()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_2.equals("About\n")){

                val fragment = Frag_about()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }

            if(currentItem_startup.text_startup_2.equals("Contact\n")){

                val fragment = Frag_contact()
                val manager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val transection = manager.beginTransaction()
                transection.replace(R.id.frag_Loader,fragment)
                transection.addToBackStack(null)
                transection.commit()
            }


        }



    }

    override fun getItemCount(): Int = list_startup.size

    class AdapterClass_Startup_ViewHolder(itemViewStartup: View) : RecyclerView.ViewHolder(itemViewStartup){

        val imageView_startup_1 : ImageView = itemViewStartup.image_view_startup_1
        val textView_startup_1 : TextView = itemViewStartup.text_view_startup_1
        val card_view_1 : CardView = itemViewStartup.card_1

        val imageView_startup_2 : ImageView = itemViewStartup.image_view_startup_2
        val textView_startup_2 : TextView = itemViewStartup.text_view_startup_2
        val card_view_2 : CardView = itemViewStartup.card_2
    }

}