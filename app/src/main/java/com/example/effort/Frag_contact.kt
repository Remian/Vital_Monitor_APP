package com.example.effort

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effort.databinding.AboutBinding
import com.example.effort.databinding.Frag13Binding
import com.example.effort.databinding.FragContactBinding


/**
 * A simple [Fragment] subclass.
 */
class Frag_contact : Fragment() {

    private lateinit var rootView: View
    private lateinit var binding: FragContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.frag_contact, container, false)
        rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




}
