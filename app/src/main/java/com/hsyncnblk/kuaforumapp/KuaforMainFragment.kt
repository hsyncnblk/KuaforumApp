package com.hsyncnblk.kuaforumapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hsyncnblk.kuaforumapp.databinding.FragmentKuaforMainBinding


class KuaforMainFragment : Fragment() {

    private lateinit var binding: FragmentKuaforMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKuaforMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth=Firebase.auth

        binding.button.setOnClickListener {
            auth.signOut()
            Navigation.findNavController(it).navigate(R.id.kuaforMain_to_login)

        }
        binding.buttonProfilegitKuafor.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.kuaforMain_to_kuaforProfil)
        }


    }


}