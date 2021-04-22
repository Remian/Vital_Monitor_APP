package com.example.effort

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.effort.databinding.Frag2Binding
import com.example.effort.databinding.FragRrBinding
import com.example.effort.databinding.FragTempBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class Frag_temp : Fragment() {

    private lateinit var binding: FragTempBinding
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.frag_temp, container, false
        )
        rootView = binding.root

        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.barTempChart.visibility = View.GONE

        binding.submitButton.setOnClickListener {

            val pref_submit = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
            val editor_submit = pref_submit.edit()
            var current_data = pref_submit.getString("temp", "")
            editor_submit.putString("temp", current_data + ";" + binding.tempInput.text.toString())
            editor_submit.apply()

            Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();

        }

        binding.graphButton.setOnClickListener {

            binding.barTempChart.visibility = View.VISIBLE
            binding.graphBUttonCard.visibility = View.GONE

            val pref_temp = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
            var temp_string = pref_temp.getString("temp", "")
            var temp_array: ArrayList<String> = ArrayList<String>()
            //binding.descp.text = temp_string.toString()


            if (temp_string!!.isNotEmpty()) {


                temp_array = temp_string.split(";") as ArrayList<String>
                temp_array.removeAt(0)

                var count_int = 1
                var count_float: Float = 0.0f

                val entries = ArrayList<BarEntry>()


                for (i in temp_array) {
                    count_float = count_int.toFloat()
                    count_int += 1

                    var i_float = i.toFloat()
                    entries.add(BarEntry(count_float,i_float))

                }

                val barDataSetTemp = BarDataSet(entries, "Temperature")

                val dataTemp = BarData(barDataSetTemp)
                binding.barTempChart.data = dataTemp
                binding.barTempChart.setFitBars(true)
                binding.barTempChart.invalidate()




            }
        }

    }
}
