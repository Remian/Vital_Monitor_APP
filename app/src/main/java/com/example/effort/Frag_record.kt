package com.example.effort

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effort.databinding.FragRrRecordBinding
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class Frag_record : Fragment() {

    private lateinit var binding: FragRrRecordBinding
    private lateinit var rootView: View
    private lateinit var succ_dialog: AlertDialog
    private lateinit var failed_dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_rr_record, container, false)
        rootView = binding.root

        return rootView
        }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
        var rr_string_check = pref.getString("rr","")

        if(rr_string_check!!.isNotEmpty()){

            showSuccessDialog()

        }
        else{

            showFailedDialog()

        }


        var rr_list : ArrayList<String> = get_rr_list()
        var analysis_list : ArrayList<String> = get_analysis_list()

        var data = data(rr_list, analysis_list)

        binding.recyclerViewResp.adapter = AdapterClass_resp(data)
        binding.recyclerViewResp.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewResp.setHasFixedSize(true)

    }




    fun get_rr_list() : ArrayList<String>{

        var rr_arrayList : ArrayList<String> = ArrayList<String>()

        val pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
        var rr_string = pref.getString("rr","")

        if(rr_string!!.isNotEmpty()){

            rr_arrayList = rr_string!!.split(";") as ArrayList<String>
            rr_arrayList.removeAt(0)
            Collections.reverse(rr_arrayList)

        }
        else{

            rr_arrayList = "Not Available;Not Available".split(";") as ArrayList<String>

        }

        return rr_arrayList


    }

    fun get_analysis_list() : ArrayList<String>{

        var analysis_arrayList : ArrayList<String> = ArrayList<String>()

        val pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
        var analysis_string = pref.getString("rr_analysis","")

        if(analysis_string!!.isNotEmpty()){

            analysis_arrayList = analysis_string!!.split(";") as ArrayList<String>
            analysis_arrayList.removeAt(0)
            Collections.reverse(analysis_arrayList)

        }
        else{

            analysis_arrayList = "Not Available;Not Available".split(";") as ArrayList<String>

        }

        return analysis_arrayList
    }

    fun data(rr_list : ArrayList<String>, analysis_list : ArrayList<String>): List<Item_resp>{

        var last_index : Int = rr_list.size-1

        var arrayList = ArrayList<Item_resp>()

        for(i in 0..last_index){

            arrayList.add(
                Item_resp(rr_list[i],analysis_list[i])
            )
        }

        return arrayList


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun showSuccessDialog() {

        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.rr_record_success_dialog, null)


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


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun showFailedDialog() {

        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.rr_record_failed_dialog, null)


        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        dialogBuilder.setOnDismissListener { }
        dialogBuilder.setView(dialogView)

        failed_dialog = dialogBuilder.create();
        failed_dialog.show()

        var frd_view : ImageView = dialogView.findViewById(R.id.sucForwardImg)
        var img_touch : ImageView = dialogView.findViewById(R.id.dialog_succ_image)

        img_touch.setBackgroundResource(R.drawable.resp_succ_animator)
        val frameAnimation : AnimationDrawable = img_touch.background
                as AnimationDrawable
        frameAnimation.start()

        frd_view.setOnClickListener {

            failed_dialog.dismiss()
        }


    }

}
