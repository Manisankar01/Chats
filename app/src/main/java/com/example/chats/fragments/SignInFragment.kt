package com.example.chats.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chats.FireBaseMethods
import com.example.chats.HomeActivity
import com.example.chats.R
import com.example.chats.databinding.SignInLayoutBinding

class SignInFragment : Fragment() {
    lateinit var binding: SignInLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignInLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fireBaseMethods = FireBaseMethods()
        binding.etEmail.addTextChangedListener(loginTextWatcher)
        binding.etPassword.addTextChangedListener(loginTextWatcher)
        binding.etEmail.setOnClickListener {
        }
        binding.ivTimerStartButton.setOnClickListener {

        }
        var myRunnable: Runnable = Runnable {
            binding.backAnimation1.animate().scaleX(4f).alpha(0f).setDuration(1000)
                .withEndAction {
                    binding.backAnimation1.scaleX = 1f
                    binding.backAnimation1.scaleY = 1f
                    binding.backAnimation1.alpha = 1f
                }
            binding.backAnimation2.animate().scaleX(4f).alpha(0f).setDuration(1000)
                .withEndAction {
                    binding.backAnimation2.scaleX = 1f
                    binding.backAnimation2.scaleY = 1f
                    binding.backAnimation2.alpha = 1f
                }

        }
        binding.btnSignIn.setOnClickListener {
//            context?.let { it1 ->
//                fireBaseMethods.userLogin(
//                    binding.etEmail.text.toString(), binding.etPassword.text.toString(),
//                    it1
//                )
            activity?.let {
                val intent = Intent(it, HomeActivity::class.java)
                it.startActivity(intent)
            }
            activity?.finish()

//            }
            Toast.makeText(
                context, "Sign-in successful",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.tvSignup.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.setCustomAnimations(
                androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom,
                androidx.appcompat.R.anim.abc_shrink_fade_out_from_bottom
            )
            transaction?.replace(R.id.flContainer, SignUpFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            binding.btnSignIn.isEnabled = ((binding.etEmail.text.toString().trim()
                .isNotEmpty()) && (binding.etPassword.text.toString().trim().isNotEmpty()))

        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }
}