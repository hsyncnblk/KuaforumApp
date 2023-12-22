package com.hsyncnblk.kuaforumapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hsyncnblk.kuaforumapp.databinding.FragmentMusteriProfilBinding


class MusteriProfilFragment : Fragment() {

    private lateinit var binding: FragmentMusteriProfilBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMusteriProfilBinding.inflate(inflater,container,false)

        auth = Firebase.auth
        firestore=Firebase.firestore

        val uid = auth.currentUser?.uid

        uid?.let {

            firestore.collection("users").document(uid).get()
                .addOnSuccessListener { document->

                    Log.e("path hata yok","path ok")
                    if (document.exists()){
                        val musteriAd = document.getString("username")
                        val musteriTel =document.getString("telefon")
                        val musteriSehir =document.getString("sehir")
                        val musteriIlce =document.getString("ilçe")

                        binding.MusteriProfilIsim.setText(musteriAd)
                        binding.MusteriProfilTel.setText(musteriTel)
                        binding.musteriProfilSehir.setText(musteriSehir)
                        binding.MusteriProfilIlce.setText(musteriIlce)

                    }else{
                        Toast.makeText(context,"Belirtilen kullanıcı bulunamadı", Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener {


                    Toast.makeText(context, "Firestore Hatası", Toast.LENGTH_SHORT).show()
                }
        }








        return binding.root
    }




}