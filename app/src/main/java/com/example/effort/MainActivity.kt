package com.example.effort

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.effort.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pt : Int = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //recycler_view.adapter = AdapterClass(arrayList)
        //recycler_view.layoutManager = LinearLayoutManager(this)
        //recycler_view.setHasFixedSize(true)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val pref= getSharedPreferences("mark",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("score", 0)
        editor.putInt("score2", 0)
        editor.apply()

        /**var ss = mutableSetOf<String>("0.0;99.0;98.0")
        ss.add("99.0")


        val pref_vital = getSharedPreferences("vital",Context.MODE_PRIVATE)
        val editor_vital = pref_vital.edit()
        editor_vital.putStringSet("rr", ss)
        editor_vital.apply()**/

        //var rr_array :ArrayList<String> = arrayListOf("0.0","99.0","98.0","97.0")
        //var rr_string : String = TextUtils.join(";", rr_array)

        /**val pref_vital = getSharedPreferences("vital", Context.MODE_PRIVATE)
        val editor_vital = pref_vital.edit()
        editor_vital.putString("rr", ";33;24;10;3;60;35")
        editor_vital.putString("temp", "")
        editor_vital.putString("rr_analysis", ";Slight High;Normal;Slight Low;Low;Very High;High")

        editor_vital.apply()**/



        val fragment = Frag_startup()
        var manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.frag_Loader, fragment).commit()


    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        pt += 1

        if (count == 0) {
            super.onBackPressed()
            //additional code
            this.finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}
