package com.hsyncnblk.kuaforumapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hsyncnblk.kuaforumapp.databinding.FragmentMusteriMainBinding


class MusteriMainFragment : Fragment() {

    private lateinit var binding: FragmentMusteriMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusteriMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.buttonCikisYap.setOnClickListener {
            auth.signOut()

            //kaldır bu işlemi daha sonru

            Navigation.findNavController(it).navigate(R.id.musteriMain_to_login)

        }

        binding.buttonprofilegit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.musteriMain_to_musteriProfil)
        }

    }


}