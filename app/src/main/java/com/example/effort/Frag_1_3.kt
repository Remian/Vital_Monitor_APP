package com.example.effort

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effort.databinding.Frag13Binding


/**
 * A simple [Fragment] subclass.
 */
class Frag_1_3 : Fragment() {

    private lateinit var rootView: View
    private lateinit var binding: Frag13Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_1_3, container, false)
        rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = data()

        binding.recyclerViewFrag13.adapter = AdapterClass(data)
        binding.recyclerViewFrag13.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewFrag13.setHasFixedSize(true)

        binding.button13.setOnClickListener {

            val fragment = Frag_2()
            val manager = activity!!.supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.frag_Loader, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }




    fun data(): List<Item> {

        val arrayList = ArrayList<Item>()




        arrayList.add(
            Item(R.drawable.ic_timer_10_black_24dp,
                "Are you unable to keep breath for more than 10seconds " +
                        "without coughing or any kind of discomfort?", 0, 1))
        arrayList.add(
            Item(R.drawable.ic_sentiment_dissatisfied_black_24dp,
                "Have you recently noticed loss of appetite?", 0, 1))
        arrayList.add(
            Item(R.drawable.ic_sentiment_dissatisfied_black_24dp,
                "Did you recently noticed unusual bowel movement(ex; sudden occurrence of diarrhea)?", 0, 1))
        arrayList.add(
            Item(R.drawable.ic_airport_shuttle_black_24dp,
                "Do you use public transport on regular basis?", 0, 1))
        arrayList.add(
            Item(R.drawable.ic_people_black_24dp,
                "Did you recently visited any public place (or undergo frequent visits)?", 0, 1))



        return arrayList
    }

}
