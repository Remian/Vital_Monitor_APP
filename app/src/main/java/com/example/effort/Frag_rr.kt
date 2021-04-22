package com.example.effort

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.postDelayed
import androidx.databinding.DataBindingUtil
import com.example.effort.databinding.Frag2Binding
import com.example.effort.databinding.FragRrBinding
import com.example.effort.databinding.FragRrLinearBinding
import kotlinx.android.synthetic.main.item.*
import kotlinx.android.synthetic.main.popup_rr.*
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass.
 */
class Frag_rr : Fragment() {

    private lateinit var binding: FragRrLinearBinding
    private lateinit var rootView: View
    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_rr_linear, container, false)
        rootView = binding.root

        /**var pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
        var set: MutableSet<String> = pref.getStringSet("rr", setOf("1.0")) as MutableSet<String>
        var element = set!!.elementAt(2)

        var ss = mutableSetOf<String>("0.0","99.0","98.0")

        binding.results.text = (set).toString()**/

        val pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
        var rr_string = pref.getString("rr","")
        var rr_arry : ArrayList<String> = ArrayList<String>()
        if(rr_string!!.isNotEmpty()){

            rr_arry = rr_string.split(";") as ArrayList<String>
            Collections.reverse(rr_arry)
            //binding.results.text = rr_arry.toString().replace("[", "").replace("]", "")

        }
        else{

            //binding.results.text = "Not Available"
        }






        return rootView

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clickCard.visibility = View.GONE
        binding.timerCard.visibility = View.GONE

        showCustomDialog()

        var count : Float = 0.0F
        var min : Float = 60.0F
        var rr : Float = 0.0F
        var thread_interrupt: Int = 0


        /**thread(start = true){

            while (true){

                view.setOnTouchListener { v, event ->

                    if(thread_interrupt>0){

                        binding.touchView.setImageResource(R.drawable.ic_touch_yellow)

                        Handler().postDelayed({
                            binding.touchView.setImageResource(R.drawable.ic_touch_blue)
                        }, 500)

                    }


                    return@setOnTouchListener true
                }


            }}**/


        thread(start = true){

            while (true){

                binding.touchView.setOnClickListener {

                    if(thread_interrupt>0){

                        count += 1

                        binding.touchView.setImageResource(R.drawable.ic_touch_yellow)

                        Handler().postDelayed({
                            binding.touchView.setImageResource(R.drawable.ic_touch_blue)
                        }, 200)

                    }

                }


            }}



        binding.retakeButton.setOnClickListener {

            //binding.rr.text = "counting"
            count = 0.0F
            thread_interrupt = 1
            binding.clickCard.visibility = View.VISIBLE
            binding.timerCard.visibility = View.VISIBLE
            binding.retakeButton.visibility = View.GONE
            binding.ageCard.visibility = View.GONE

            val timer = object: CountDownTimer(30000, 1) {
                override fun onFinish() {
                    thread_interrupt = 0
                    binding.clickCard.visibility = View.GONE
                    binding.retakeButton.visibility = View.VISIBLE
                    binding.ageCard.visibility = View.VISIBLE
                    binding.timerCard.visibility = View.GONE
                    rr = (count/30)*60
                    //binding.rr.text = rr.toString()
                    binding.timer.text = "0"


                    val pref = activity!!.getSharedPreferences("vital", Context.MODE_PRIVATE)
                    val editor = pref.edit()
                    val prefData = pref.getString("rr", "")
                    editor.putString("rr", "$prefData;$rr")
                    editor.apply()


                }

                override fun onTick(millisUntilFinished: Long) {
                    //thread_interrupt = 1



                    binding.timer.text = (millisUntilFinished/1000).toString()
                    //binding.rr.text = count.toString()

                    /**

                    view!!.setOnClickListener {
                        count = count.plus(1)
                        binding.timer.text = (millisUntilFinished/1000).toString()
                    }**/

                    /**view.setOnTouchListener { v, event -> count = count.plus(1)
                        binding.rr.text = count.toString()

                        return@setOnTouchListener true}**/

                    //img_touch.setBackgroundColor(R.drawable.spin_animation)

                    //var loadAnim = AnimationDrawable()
                    //loadAnim.
                    //alertDialog.show()

                    //var frameAnimation: AnimationDrawable = img_touch.background as AnimationDrawable
                    //frameAnimation.start()


                    /**binding.touchView.setOnClickListener {
                        count = count.plus(1)
                        binding.rr.text = count.toString()

                        //binding.touchView.setBackgroundResource(R.drawable.spin_animation)

                        //val frameAnimation : AnimationDrawable = binding.touchView.background
                        //as AnimationDrawable

                        //frameAnimation.start()

                    }**/


                }
            }



            timer.start()

        }

    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun showCustomDialog() {
        val inflater: LayoutInflater = this.getLayoutInflater()
        val dialogView: View = inflater.inflate(R.layout.popup_rr, null)


        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        dialogBuilder.setOnDismissListener { }
        dialogBuilder.setView(dialogView)

        alertDialog = dialogBuilder.create();
        alertDialog.show()



        var img_touch: ImageView =  dialogView.findViewById(R.id.touchView)
        var img_display_img : ImageView = dialogView.findViewById(R.id.dialog_touch_display)


        val timer = object: CountDownTimer(300000000, 2000) {
            override fun onFinish() {

                alertDialog.dismiss()


            }

            override fun onTick(millisUntilFinished: Long) {


                Handler().postDelayed({
                    img_touch.setImageResource(R.drawable.ic_touch_yellow)
                }, 1000)


                img_touch.setImageResource(R.drawable.ic_touch_blue)



                var but: Button = alertDialog.findViewById(R.id.popButton)

                but.setOnClickListener {

                    alertDialog.dismiss()
                }


            }
        }

        timer.start()


    }


}










//dialogView.height =
//img_touch.setImageResource(R.drawable.ic_touch_yellow)
//alertDialog.window!!.getAttributes().windowAnimations = R.style.PauseDialogAnimation

//img_touch.setBackgroundColor(R.drawable.spin_animation)

//var loadAnim = AnimationDrawable()
//loadAnim.
//alertDialog.show()

//var frameAnimation: AnimationDrawable = img_touch.background as AnimationDrawable
//frameAnimation.start()
/**Thread(Runnable {
//var img_touch: ImageView =  dialogView.findViewById(R.id.touchView)
Handler().postDelayed({
//img_touch.setImageResource(R.drawable.ic_touch_yellow)
}, 500)
//img_touch.setImageResource(R.drawable.ic_touch_blue)
}).start()**/
/**var layoutParam: WindowManager.LayoutParams = WindowManager.LayoutParams()
layoutParam.copyFrom(alertDialog.window?.attributes)
var w = resources.displayMetrics.widthPixels*0.90
var h = resources.displayMetrics.widthPixels*0.9
//layoutParam.height = WindowManager.LayoutParams.MATCH_PARENT
//layoutParam.width = WindowManager.LayoutParams.MATCH_PARENT
//layoutParam.height = h.toInt()
//layoutParam.width = w.toInt()
alertDialog.window?.attributes = layoutParam**/



