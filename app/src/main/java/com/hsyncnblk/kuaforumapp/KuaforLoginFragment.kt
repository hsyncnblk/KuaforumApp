package com.hsyncnblk.kuaforumapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsyncnblk.kuaforumapp.databinding.FragmentKuaforLoginBinding


class KuaforLoginFragment : Fragment() {

    private lateinit var binding: FragmentKuaforLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKuaforLoginBinding.inflate(inflater,container,false)
        return binding.root
    }


}