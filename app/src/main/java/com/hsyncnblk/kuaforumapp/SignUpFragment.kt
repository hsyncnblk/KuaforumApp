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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hsyncnblk.kuaforumapp.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSignUpBinding.inflate(inflater,container,false)

        // Initialize Firebase Auth
        auth = Firebase.auth
        firestore=Firebase.firestore



        binding.buttonSignUpKayitOl.setOnClickListener {

            val email = binding.etMusteriKayitUsername.text.toString()
            val password = binding.etMusteriKayitPassword.text.toString()


            val name = binding.etMusteriName.text.toString()
            val telefon = binding.etMusteriTel.text.toString()
            val sehir = binding.etMusteriSehir.text.toString()
            val ilce = binding.etMusteriIIce.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()){


                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {

                        val user = auth.currentUser
                        val uid = user?.uid

                        val userData = hashMapOf(
                            "username" to name,
                            "telefon" to telefon,
                            "sehir" to sehir,
                            "ilçe" to ilce

                        )

                        uid.let {
                            firestore.collection("users").document().set(userData)
                                .addOnSuccessListener {
                                    Navigation.findNavController(requireView()).navigate(R.id.signUp_to_musteriMain)
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context,"firestore hatası",Toast.LENGTH_SHORT).show()
                                }
                        }



                    }


            }else{
                Toast.makeText(context,"Boş alan olmamalı!!",Toast.LENGTH_SHORT).show()
            }
        }







        return binding.root
    }


}