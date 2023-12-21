package com.hsyncnblk.kuaforumapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.hsyncnblk.kuaforumapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        binding.buttonLoginKayitOl.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.login_to_signUp)

        }
        binding.tvKuaforGo.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.login_to_kuaforLogin)

        }



        return binding.root
    }


}