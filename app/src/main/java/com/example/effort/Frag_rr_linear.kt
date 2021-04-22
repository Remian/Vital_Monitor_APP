package com.example.effort

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.text.set
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.effort.databinding.FragRrLinearBinding
import kotlinx.android.synthetic.main.frag_rr_linear.*
import java.lang.Math.ceil
import kotlin.concurrent.thread
import kotlin.math.abs
import kotlin.math.ceil


class Frag_rr_linear : Fragment(){

    private lateinit var binding : FragRrLinearBinding
    private lateinit var rootView : View
    private lateinit var instuctionDialog: AlertDialog
    private lateinit var resultDialog: AlertDialog

    private var thread_interrupt: Int = 0

    private var count : Float = 0.0F
    //private var rr : Float = 0.0F
    private var timer_span : Float = 40.0F
    private var mininute_time : Float = 60.0F


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_rr_linear,
            container, false)
        rootView = binding.root

        return rootView

    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showIntructionDialog()

        binding.timerCard.visibility = View.GONE
        binding.clickCard.visibility = View.GONE

        blinkCountThread()


        binding.retakeButton.setOnClickListener {

            count = 0.0F
            thread_interrupt = 1

            binding.ageCard.visibility = View.GONE
            binding.retakeButton.visibility = View.GONE
            binding.timerCard.visibility = View.VISIBLE
            binding.clickCard.visibility = View.VISIBLE

            binding.timerImage.setBackgroundResource(R.drawable.spin_animation)
            val frameAnimation : AnimationDrawable = binding.timerImage.background
            as AnimationDrawable
            frameAnimation.start()

            timer_initater()
        }

    }

    fun get_age(): Float{

        return binding.age.text.toString().toFloat()
    }



    fun rr_calc_store() : Float{

        var rr_local: Float = (count/timer_span)*mininute_time
        var rr_local_string = ceil(rr_local).toString()

        val pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
        val editor = pref.edit()
        val prefData = pref.getString("rr", "")
        editor.putString("rr", "$prefData;$rr_local_string")
        editor.apply()

        return rr_local


    }

    fun rr_analysis_store(input_age: Float, input_rr: Float): String{

        var analysis : String = ""
        var rr_int = ceil(abs(input_rr)).toInt()
        var age_int = ceil(abs(input_age)).toInt()

        when(age_int){
            1->when(rr_int){
                in 30..60 -> analysis="Normal"
                in 27..29 -> analysis="Slight Low"
                in 0..26 -> analysis="Low"
                in 61..64 -> analysis="Slight high"
                in 65..70 -> analysis="High"
                else -> analysis="Very High"
            }

            in 2..3->when(rr_int){
                in 24..40 -> analysis="Normal"
                in 20..23 -> analysis="Slight Low"
                in 0..19 -> analysis="Low"
                in 41..45 -> analysis="Slight High"
                in 46..50 -> analysis="High"
                else -> analysis="Very High"
            }

            in 4..6->when(rr_int){
                in 22..34 -> analysis="Normal"
                in 19..21 -> analysis="Slight Low"
                in 0..18 -> analysis="Low"
                in 35..39 -> analysis="Slight High"
                in 40..45 -> analysis="High"
                else -> analysis="Very High"
            }

            in 7..12->when(rr_int){
                in 18..30 -> analysis="Normal"
                in 15..17 -> analysis="Slight Low"
                in 0..14 -> analysis="Low"
                in 31..34 -> analysis="Slight High"
                in 35..40 -> analysis="High"
                else -> analysis="Very High"
            }

            in 13..18->when(rr_int){
                in 12..16 -> analysis="Normal"
                in 9..11 -> analysis="Slight Low"
                in 0..8 -> analysis="Low"
                in 17..21 -> analysis="Slight High"
                in 22..28 -> analysis="High"
                else -> analysis="Very High"
            }

            in 19..65->when(rr_int){
                in 12..28 -> analysis="Normal"
                in 10..11 -> analysis="Slight Low"
                in 0..9 -> analysis="Low"
                in 29..31 -> analysis="Slight High"
                in 32..36 -> analysis="High"
                else -> analysis="Very High"
            }

            else ->when(rr_int){
                in 10..30 -> analysis="Normal"
                in 8..9 -> analysis="Slight Low"
                in 0..7 -> analysis="Low"
                in 31..33 -> analysis="Slight High"
                in 34..37 -> analysis="High"
                else -> analysis="Very High"
            }

        }

        val pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
        val editor = pref.edit()
        val prefData = pref.getString("rr_analysis", "")
        editor.putString("rr_analysis", "$prefData;$analysis")
        editor.apply()


        return analysis
    }




    fun timer_initater() {

        val timer = object: CountDownTimer(40000, 2){

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            override fun onFinish() {
                thread_interrupt = 0
                binding.clickCard.visibility = View.GONE
                binding.timerCard.visibility = View.GONE

                binding.retakeButton.visibility = View.VISIBLE
                binding.ageCard.visibility = View.VISIBLE
                binding.timer.text = "0"

                binding.age.setText(get_age().toString())

                var rr: Float = rr_calc_store()

                var age: Float = get_age()
                var analysis: String = rr_analysis_store(age,rr)

                result_popup_dialog(rr,analysis)
            }

            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = (millisUntilFinished/1000).toString()+" "+"Sec"

            }
        }

        timer.start()

    }


    fun blinkCountThread() {

        thread(start = true){

            binding.touchView.setOnClickListener {

                if(thread_interrupt>0){

                    count += 1
                    binding.touchView.setImageResource(R.drawable.ic_touch_yellow)
                    Handler().postDelayed({
                        binding.touchView.setImageResource(R.drawable.ic_touch_blue)
                    }, 200)

                }
            }

        }

    }








    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun showIntructionDialog() {

        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.popup_rr_rl, null)


        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        dialogBuilder.setOnDismissListener { }
        dialogBuilder.setView(dialogView)

        instuctionDialog = dialogBuilder.create();
        instuctionDialog.show()

        var img_touch: ImageView =  dialogView.findViewById(R.id.touchView)
        var img_display_img : ImageView = dialogView.findViewById(R.id.dialog_touch_display)

        img_touch.setBackgroundResource(R.drawable.touch_blink_animation)
        val frameAnimation : AnimationDrawable = img_touch.background
                as AnimationDrawable
        frameAnimation.start()


        val timer = object: CountDownTimer(300000000, 2000) {
            override fun onFinish() {
                instuctionDialog.dismiss()
            }

            override fun onTick(millisUntilFinished: Long) {

                Handler().postDelayed({
                    img_display_img.setImageResource(R.drawable.ic_touch_blue_150)
                }, 500)

                img_display_img.setImageResource(R.drawable.ic_touch_blue_100)

                var but: Button = instuctionDialog.findViewById(R.id.popButton)
                but.setOnClickListener {

                    instuctionDialog.dismiss()
                }


            }
        }

        timer.start()


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun result_popup_dialog(rr_result: Float, rr_analysis: String){

        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView : View = inflater.inflate(R.layout.rr_result_dialog, null)

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        dialogBuilder.setOnDismissListener {  }
        dialogBuilder.setView(dialogView)

        resultDialog = dialogBuilder.create();
        resultDialog.show()

        var resultText : TextView = dialogView.findViewById(R.id.rr_result)
        resultText.text =  ceil(rr_result).toString()

        var analysisText : TextView = dialogView.findViewById(R.id.analysis)
        analysisText.text = rr_analysis

        var forwardImg: ImageView = dialogView.findViewById(R.id.forwardImg)
        var img_touch: ImageView = dialogView.findViewById(R.id.imgResult)

        img_touch.setBackgroundResource(R.drawable.result_animator)
        val frameAnimation : AnimationDrawable = img_touch.background
                as AnimationDrawable
        frameAnimation.start()

        forwardImg.setOnClickListener {

            resultDialog.dismiss()

        }

    }

    }