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
import com.example.effort.databinding.FragGraphBinding
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
class Frag_graph : Fragment() {

    private lateinit var binding: FragGraphBinding
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.frag_graph, container, false
        )
        rootView = binding.root

        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
        var temp_string = pref.getString("temp", "")
        var rr_string = pref.getString("rr", "")


        if(temp_string!!.isNotEmpty()){

            var temp_array: ArrayList<String> = ArrayList<String>()

            temp_array = temp_string!!.split(";") as ArrayList<String>
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
            binding.barTempGraph.data = dataTemp
            binding.barTempGraph.setFitBars(true)
            binding.barTempGraph.invalidate()


        }

        else{

            setPlotTemp()

        }

        if(rr_string!!.isNotEmpty()){

            var rr_array: ArrayList<String> = ArrayList<String>()

            rr_array = rr_string.split(";") as ArrayList<String>
            rr_array.removeAt(0)

            var count_int = 1
            var count_float: Float = 0.0f

            val entries = ArrayList<BarEntry>()


            for (i in rr_array) {
                count_float = count_int.toFloat()
                count_int += 1

                var i_float = i.toFloat()
                entries.add(BarEntry(count_float,i_float))

            }

            val barDataSetTemp = BarDataSet(entries, "Respiration")

            val dataTemp = BarData(barDataSetTemp)
            binding.barRrGraph.data = dataTemp
            binding.barRrGraph.setFitBars(true)
            binding.barRrGraph.invalidate()


        }

        else{

            setPlotRR()
        }


        }



    fun setPlotTemp() {

        val entries = ArrayList<BarEntry>()

        entries.add(BarEntry(1f, 98.5f))
        entries.add(BarEntry(2f, 98.5f))
        entries.add(BarEntry(3f, 98.5f))
        entries.add(BarEntry(4f, 98.5f))
        entries.add(BarEntry(5f, 98.5f))
        entries.add(BarEntry(6f, 98.5f))

        val barDataSet = BarDataSet(entries, "Temperature")


        val data = BarData(barDataSet)
        binding.barTempGraph.data = data
        binding.barTempGraph.setFitBars(true)
        binding.barTempGraph.invalidate()


    }

    fun setPlotRR() {

        val entries = ArrayList<BarEntry>()

        entries.add(BarEntry(1f, 98.5f))
        entries.add(BarEntry(2f, 98.5f))
        entries.add(BarEntry(3f, 98.5f))
        entries.add(BarEntry(4f, 98.5f))
        entries.add(BarEntry(5f, 98.5f))
        entries.add(BarEntry(6f, 98.5f))

        val barDataSet = BarDataSet(entries, "Respiration Rate")


        val data = BarData(barDataSet)
        binding.barRrGraph.data = data
        binding.barRrGraph.setFitBars(true)
        binding.barRrGraph.invalidate()


    }

    }

