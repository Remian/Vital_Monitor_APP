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
import com.example.effort.databinding.Frag12Binding


/**
 * A simple [Fragment] subclass.
 */
class Frag_1_2 : Fragment() {

    private lateinit var rootView: View
    private lateinit var binding: Frag12Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_1_2, container, false)
        rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = data()

        binding.recyclerViewFrag12.adapter = AdapterClass(data)
        binding.recyclerViewFrag12.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewFrag12.setHasFixedSize(true)

        binding.button12.setOnClickListener {

            val fragment = Frag_1_3()
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
            Item(R.drawable.ic_people_black_24dp,
                "Have you met with any potential corona patient (or you think he/she may be infected)?",
                0, 2))
        arrayList.add(
            Item(R.drawable.ic_wc_black_24dp,
                "Did any of your family member (or you come regular contact with)" +
                        " have met with anyone recently who had " +
                        "travelled abroad?", 0, 2))

        arrayList.add(
            Item(R.drawable.ic_airline_seat_individual_suite_black_24dp,
                "Are you suffering from any kind of flu?", 0, 1))
        arrayList.add(
            Item(R.drawable.ic_airline_seat_individual_suite_black_24dp,
                "Are you suffering from dry cough?", 0, 1))
        arrayList.add(
            Item(R.drawable.ic_airline_seat_individual_suite_black_24dp,
                "Do you have any kind of respiration discomfort?", 0, 2))
        arrayList.add(
            Item(R.drawable.ic_airline_seat_individual_suite_black_24dp,
                "Are you suffering from pneumonia?", 0, 3))



        return arrayList
    }

}
