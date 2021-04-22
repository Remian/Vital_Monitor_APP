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

/**
 * A simple [Fragment] subclass.
 */
class Frag_2 : Fragment() {

    private lateinit var binding: Frag2Binding
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_2, container, false)
        rootView = binding.root

        val pref = activity?.getSharedPreferences("mark",Context.MODE_PRIVATE)

        val score: Int = pref!!.getInt("score", 0)

        binding.score.text = score.toString()

        if(score<3){

            binding.assessment.text = "Reduced Contamination Chance"
            binding.recom.text = "Maintain Hygiene and social distance"

        }

        else if(score in 3..7){

            binding.assessment.text = "Moderate Chance of Contamination"
            binding.recom.text = "Maintain Quarantine, perform test, seek professional medical advice"
        }

        else if(score >= 8){

            binding.assessment.text = "High Chance of contamination"
            binding.recom.text = "Immediate hospitalization and treatment "
        }

        else{


            binding.assessment.text = "reduced"
            binding.recom.text = "maintain hygiene"
        }



        return rootView

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButton.setOnClickListener {
            val pref = activity?.getSharedPreferences("mark",Context.MODE_PRIVATE)
            val editor = pref?.edit()
            editor?.putInt("score", 0)
            editor?.commit()

            val fragment = Frag_1()
            val manager = activity!!.supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.frag_Loader, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.imageButtonHomeFrag2.setOnClickListener {

            val fragment = Frag_startup()
            val manager = activity!!.supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.frag_Loader, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }


    }


