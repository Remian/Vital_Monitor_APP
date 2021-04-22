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
import com.example.effort.databinding.Frag1Binding

/**
 * A simple [Fragment] subclass.
 */
class Frag_1 : Fragment() {

    private lateinit var rootView: View
    private lateinit var binding: Frag1Binding
    private lateinit var succ_dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_1, container, false)
        rootView = binding.root

        return rootView
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showSuccessDialog()

        val pref = activity?.getSharedPreferences("mark",Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putInt("score", 0)
        editor?.commit()

        var data = data()

        binding.recyclerViewFrag.adapter = AdapterClass(data)
        binding.recyclerViewFrag.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewFrag.setHasFixedSize(true)

        binding.button1.setOnClickListener {

            val fragment = Frag_1_2()
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
            Item(R.drawable.ic_exposure,
                "Did you have exposure (direct or indirect) to any covid-19 contaminated patient?", 0, 6))
        arrayList.add(
            Item(R.drawable.ic_airplanemode_active_blue_24dp,
                "Did you travel to any of the following countries recently? \n" +
                        "Italy, China, Iran, USA, UK, Saudi Arabia", 0, 5))
        arrayList.add(
            Item(R.drawable.ic_airplanemode_active_blue_24dp,
                "Did have any kind of travel history other than the above mentioned countries?", 0, 4))
        arrayList.add(
            Item(R.drawable.ic_airplanemode_active_blue_24dp,
                "Did you recently met with anyone who have travelled to Italy, China" +
                        "Iran, USA, UK or Saudi Arabia?",
                0, 4))
        arrayList.add(
            Item(R.drawable.ic_airplanemode_active_blue_24dp,
                "Did you recently met with anyone who have travelled to other than any of the above" +
                        "mentioned countries?",
                0, 2))



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
