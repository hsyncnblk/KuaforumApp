package com.hsyncnblk.kuaforumapp

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hsyncnblk.kuaforumapp.databinding.FragmentKuaforLoginBinding


class KuaforLoginFragment : Fragment() {

    private lateinit var binding: FragmentKuaforLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKuaforLoginBinding.inflate(inflater,container,false)



        binding.buttonLoginKuaforKayitOl.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.kuaforLogin_to_kuaforSignUp)
        }




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth= Firebase.auth

        val currentUser = auth.currentUser

        if (currentUser !=null){
            Navigation.findNavController(requireView()).navigate(R.id.kuaforLogin_to_kuaforMain)
        }

        binding.buttonKuaforGirisYap.setOnClickListener {
            val email = binding.etKuaforUserName.text.toString()
            val password = binding.etKuaforPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->

                    if (task.isSuccessful){

                        Navigation.findNavController(it).navigate(R.id.kuaforLogin_to_kuaforMain)

                    }else{
                        Toast.makeText(context,"${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()

                    }

                }.addOnFailureListener {
                    Toast.makeText(context,it.localizedMessage, Toast.LENGTH_SHORT).show()

                }

            }else{
                Toast.makeText(context,"Boş alan olmamalı!", Toast.LENGTH_SHORT).show()
            }

        }


    }


}