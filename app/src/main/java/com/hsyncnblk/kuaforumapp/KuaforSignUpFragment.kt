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
import com.hsyncnblk.kuaforumapp.databinding.FragmentKuaforSignUpBinding

class KuaforSignUpFragment : Fragment() {

    private lateinit var binding: FragmentKuaforSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKuaforSignUpBinding.inflate(inflater,container,false)

        auth=Firebase.auth
        firestore=Firebase.firestore

        binding.btnKuaforKayitOl.setOnClickListener {

            val email = binding.etKuaforSignUpUsername.text.toString()
            val password = binding.etKuaforSignUpPassword.text.toString()

            val kuaforAd= binding.etKuaforAdiKayit.text.toString()
            val kuaforTel = binding.etKuaforKayitTel.text.toString()
            val kauforSehir = binding.etKuaforSehir.text.toString()
            val kauforIlce = binding.etKuaforIlce.text.toString()
            val kauforAdres = binding.etKuaforAdres.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() ){


                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {

                        val user = auth.currentUser
                        val uid = user?.uid

                        val userData = hashMapOf(
                            "username" to kuaforAd,
                            "phone" to kuaforTel,
                            "city" to kauforSehir,
                            "town" to kauforIlce,
                            "adress" to kauforAdres)

                        uid?.let {
                            firestore.collection("kaufor").document(uid).set(userData)
                                .addOnSuccessListener {
                                    Navigation.findNavController(requireView()).navigate(R.id.kuaforSignUp_to_kuaforMain)
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context,"firestore hatası",Toast.LENGTH_SHORT).show()
                                }
                        }



                    }




            }else{
                Toast.makeText(context,"Boş alan olmamalı!!", Toast.LENGTH_SHORT).show()
            }


        }



        return binding.root
    }


}