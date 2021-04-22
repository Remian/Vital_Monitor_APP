package com.example.effort

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.effort.databinding.FragStartupBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.frag_startup.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class Frag_startup : Fragment() {

    private lateinit var binding: FragStartupBinding
    private lateinit var rootView: View

    //private var doubleBackToExitPressedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_startup, container, false)

        rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val manager = activity!!.supportFragmentManager
        //manager.popBackStackImmediate(0,FragmentManager.POP_BACK_STACK_INCLUSIVE)


        val data_1 = data_startup_1()

        val toast = Toast.makeText(activity!!,"Press the upper right corner button to exit.", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM,0,0)
        toast.show()


        binding.recyclerViewStart1.adapter = AdapterClass_Startup(data_1)
        //binding.recyclerViewStart.layoutManager = LinearLayoutManager(activity,
            //RecyclerView.HORIZONTAL, false)
        binding.recyclerViewStart1.layoutManager = LinearLayoutManager(activity)

        binding.recyclerViewStart1.setHasFixedSize(true)

        binding.exitImg.setOnClickListener {

            activity?.finish()
        }


    }





    fun data_startup_1(): List<Item_startup>{

        val arrayList = ArrayList<Item_startup>()

        arrayList.add(Item_startup(R.drawable.ic_assesment,"Get Quick \nAssesment",
            R.drawable.ic_warning_black_24dp,"Check Risk \n"))
        arrayList.add(Item_startup(R.drawable.ic_rr, "Respiratory Rate\n",
            R.drawable.ic_vitals, "Temperature records\n"))
        arrayList.add(Item_startup(R.drawable.ic_vitals, "Oxygen Saturation Records\n",
            R.drawable.ic_plot, "Graphs\n"))
        arrayList.add(Item_startup(R.drawable.ic_local_phone_black_24dp, "Contact\n",
            R.drawable.ic_local_hospital_bluee, "About\n"))



        return arrayList

    }



}
