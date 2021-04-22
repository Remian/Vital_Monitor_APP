package com.example.effort

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.effort.databinding.Frag2Binding
import com.example.effort.databinding.FragRisk2Binding

/**
 * A simple [Fragment] subclass.
 */
class Frag_risk_2 : Fragment() {

    private lateinit var binding: FragRisk2Binding
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_risk_2, container, false)
        rootView = binding.root

        val pref = activity?.getSharedPreferences("mark",Context.MODE_PRIVATE)

        val score: Int = pref!!.getInt("score2", 0)

        if(score < 2){

            binding.risk.text = "Probable occurrence \nof \ncritical condition \nis low"

        }

        else{

            binding.risk.text = "critical condition may occur"
        }



        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButtonRisk.setOnClickListener {
            val pref = activity?.getSharedPreferences("mark",Context.MODE_PRIVATE)
            val editor = pref?.edit()
            editor?.putInt("score2", 0)
            editor?.commit()

            val fragment = Frag_risk_1()
            val manager = activity!!.supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.frag_Loader, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.imageButtonHomeRisk.setOnClickListener {

            val fragment = Frag_startup()
            val manager = activity!!.supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.frag_Loader, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }


}


