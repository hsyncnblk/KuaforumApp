package com.hsyncnblk.kuaforumapp

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
import com.hsyncnblk.kuaforumapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Initialize Firebase Auth
        auth = Firebase.auth


        val currentUser = auth.currentUser

        if (currentUser != null){
            Navigation.findNavController(requireView()).navigate(R.id.login_to_musteriMain)
        }

        binding.buttonLoginGirisYap.setOnClickListener {
            val email = binding.etMusteriUsername.text.toString()
            val password = binding.etMusteriPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task->
                    if (task.isSuccessful){
                        Navigation.findNavController(requireView()).navigate(R.id.login_to_musteriMain)
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