package com.example.effort

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effort.databinding.Frag13Binding
import com.example.effort.databinding.FragHealthRisk1Binding
import com.example.effort.databinding.FragRisk2Binding


/**
 * A simple [Fragment] subclass.
 */
class Frag_risk_1 : Fragment() {

    private lateinit var binding: FragHealthRisk1Binding
    private lateinit var rootView: View
    private lateinit var succ_dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_health_risk_1, container, false)
        rootView = binding.root

        return rootView
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showSuccessDialog()

        var data = data()

        binding.recyclerViewRisk.adapter = AdapterClass_Risk(data)
        binding.recyclerViewRisk.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewRisk.setHasFixedSize(true)

        binding.buttonRisk.setOnClickListener {

            val age : Int = binding.ageInput.text.toString().toInt()
            var pref = activity?.getSharedPreferences("mark", Context.MODE_PRIVATE)
            var editor = pref?.edit()
            var score: Int = pref!!.getInt("score2", 0)


            if(age < 60){

                editor?.putInt("score2", score.plus(1))
                editor?.apply()
            }
            else{

                editor?.putInt("score2", score.plus(2))
                editor?.apply()


            }

            val fragment = Frag_risk_2()
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
            Item(R.drawable.ic_local_hospital_bluee,
                "Are you an asthma Patient?", 0, 2))
        arrayList.add(
            Item(R.drawable.ic_local_hospital_bluee,
                "Are you a Cancer Patient or suffering from immune system complication?", 0, 2))
        arrayList.add(
            Item(R.drawable.ic_local_hospital_bluee,
                "Are you a diabetic patient?", 0, 2))
        arrayList.add(
            Item(R.drawable.ic_local_hospital_bluee,
                "Are you a cardiovascular patient?", 0, 2))
        arrayList.add(
            Item(R.drawable.ic_local_hospital_bluee,
                "Are you suffering from any kind of chronic disease?", 0, 2))

        return arrayList
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun showSuccessDialog() {

        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.instructions_dialog, null)


        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        dialogBuilder.setOnDismissListener { }
        dialogBuilder.setView(dialogView)

        succ_dialog = dialogBuilder.create();
        succ_dialog.show()

        var frd_view : ImageView = dialogView.findViewById(R.id.sucForwardImg)
        var img_touch : ImageView = dialogView.findViewById(R.id.dialog_succ_image)

        img_touch.setBackgroundResource(R.drawable.result_animator)
        val frameAnimation : AnimationDrawable = img_touch.background
                as AnimationDrawable
        frameAnimation.start()

        frd_view.setOnClickListener {

            succ_dialog.dismiss()
        }


    }

}
