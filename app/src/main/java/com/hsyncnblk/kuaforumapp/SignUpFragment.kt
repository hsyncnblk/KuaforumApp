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
import com.hsyncnblk.kuaforumapp.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSignUpBinding.inflate(inflater,container,false)

        // Initialize Firebase Auth
        auth = Firebase.auth



        binding.buttonSignUpKayitOl.setOnClickListener {

            val email = binding.etMusteriKayitUsername.text.toString()
            val password = binding.etMusteriKayitPassword.text.toString()
            val confirmPassword = binding.etMusteriKayitPasswordTekrar.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){

                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {

                        Navigation.findNavController(requireView()).navigate(R.id.signUp_to_musteriMain)

                    }.addOnFailureListener {
                        Toast.makeText(context,it.localizedMessage,Toast.LENGTH_SHORT).show()

                    }

                }else{
                    Toast.makeText(context,"Parolanız aynı değil!",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context,"Boş alan olmamalı!!",Toast.LENGTH_SHORT).show()
            }
        }







        return binding.root
    }


}