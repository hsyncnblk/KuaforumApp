package com.hsyncnblk.kuaforumapp

import android.os.Bundle
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
import com.hsyncnblk.kuaforumapp.databinding.FragmentKuaforProfileBinding

class KuaforProfileFragment : Fragment() {

    private lateinit var binding: FragmentKuaforProfileBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKuaforProfileBinding.inflate(inflater,container,false)

        auth = Firebase.auth
        firestore = Firebase.firestore

        val uid = auth.currentUser?.uid

        uid?.let {
            firestore.collection("kaufor").document(uid).get()
                .addOnSuccessListener { document->
                    if (document.exists()){
                        // Firestore'dan çekilen verileri al
                        val kuaforAd = document.getString("username")
                        val kuaforTel = document.getString("phone")
                        val kuaforSehir = document.getString("city")
                        val kuaforIlce = document.getString("town")
                        val kuaforAdres = document.getString("adress")

                        // TextView ve TextInputEditText elemanlarına verileri yerleştir
                        binding.kauforProfilIsim.setText(kuaforAd)
                        binding.KuaforProfilTel.setText(kuaforTel)
                        binding.KuaforProfilSehir.setText(kuaforSehir)
                        binding.kauforProfilIlce.setText(kuaforIlce)
                        binding.kuaforProfilAdres.setText(kuaforAdres)
                    }else{
                        Toast.makeText(context,"Belirtilen kullanıcı bulunamadı", Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener {
                    Toast.makeText(context, "Firestore Hatası", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnGuncelleKuafor.setOnClickListener {
            updateProfile()
        }


        return binding.root
    }

    private fun updateProfile(){

        val uid = auth.currentUser?.uid

        uid?.let {

            val updatedData = hashMapOf(
                "username" to binding.kauforProfilIsim.text.toString(),
                "phone" to binding.KuaforProfilTel.text.toString(),
                "city" to binding.KuaforProfilSehir.text.toString(),
                "town" to binding.kauforProfilIlce.text.toString(),
                "adress" to binding.kuaforProfilAdres.text.toString()
            )
            firestore.collection("kaufor").document(uid).update(updatedData.toMap())
                .addOnSuccessListener {
                    Toast.makeText(context, "Profil güncellendi", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {

                    Toast.makeText(context, "Profil güncelleme hatası", Toast.LENGTH_SHORT).show()
                }


        }
    }


}