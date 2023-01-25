package com.example.chats.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chats.FireBaseMethods
import com.example.chats.R
import com.example.chats.databinding.SignUpLayoutBinding

class SignUpFragment : Fragment() {

    lateinit var binding: SignUpLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val method = FireBaseMethods()
        binding.etFirstName.addTextChangedListener(signUpTextWatcher)
        binding.etLastName.addTextChangedListener(signUpTextWatcher)
        binding.etEmail.addTextChangedListener(signUpTextWatcher)
        binding.etPassword.addTextChangedListener(signUpTextWatcher)
        binding.etReEnterPassword.addTextChangedListener(signUpTextWatcher)
        binding.etMobileNumber.addTextChangedListener(signUpTextWatcher)
        passwordFocusListener()
        emailFocusListener()
        confirmPasswordFocusListener()
        mobileNumberFocusListener()
        binding.btnSignUp.setOnClickListener {
            if ((binding.etPassword.text.toString() == binding.etReEnterPassword.text.toString())) {
                context?.let { it1 ->
                  val data =  method.creteAccount(
                        binding.etFirstName.text.toString(),
                        binding.etLastName.text.toString(),
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString(),
                        binding.etMobileNumber.text.toString(),
                        it1
                    )
                    if(data){
                        val transaction = activity?.supportFragmentManager?.beginTransaction()
                        transaction?.setCustomAnimations(
                            androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom,
                            androidx.appcompat.R.anim.abc_shrink_fade_out_from_bottom
                        )
                        transaction?.replace(R.id.flContainer, SignInFragment())
                        transaction?.disallowAddToBackStack()
                        transaction?.commit()
                    }
                }
                clearAllFields()
            }
        }
        binding.tvSignInText.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.flContainer, SignInFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()

        }
    }

    private fun clearAllFields(){
        binding.etFirstName.text?.clear()
        binding.etLastName.text?.clear()
        binding.etEmail.text?.clear()
        binding.etPassword.text?.clear()
        binding.etReEnterPassword.text?.clear()
        binding.etMobileNumber.text?.clear()

    }

    private val signUpTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.btnSignUp.isEnabled = ((binding.etFirstName.text.toString().trim()
                .isNotEmpty()) && (binding.etLastName.text.toString().trim().isNotEmpty())
                    && (binding.etEmail.text.toString().trim().isNotEmpty()))


        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    private fun confirmPasswordFocusListener() {
        binding.etReEnterPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.confirmPasswordLayout.helperText = text()
            }
        }
    }

    private fun text(): String? {
        if (binding.etReEnterPassword.text.toString() !=
            binding.etPassword.text.toString()
        ) {
            return "mismatched"
        }
        return null

    }

    private fun passwordFocusListener() {
        binding.etPassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordLayout.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.etPassword.text.toString()
        if (passwordText.length < 8) {
            return "password should be minimum 8 characters"
        }

        if (!passwordText.contains(Regex("[A-Z]"))) {
            return "password must contain one capital letter"
        }
        if (!passwordText.contains(Regex("[a-z]"))) {
            return "password contain one small case letter"
        }
        if (!passwordText.contains(Regex("[0-9]"))) {
            return "pass must contain one numerical value"
        }
        if (!passwordText.matches(".*[!@#$%^&*].*".toRegex())) {
            return "must contain one special character"
        }

        return null

    }

    private fun emailFocusListener() {
        binding.etEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailLayout.helperText = validEmail()
            }

        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email address"
        }
        return null
    }
    private fun mobileNumberFocusListener() {
        binding.etMobileNumber.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.mobileNumber.helperText = validMobileNumber()
            }

        }
    }
    private fun validMobileNumber():String?{
        val mobileNumber = binding.etMobileNumber.text.toString()
        if(!Patterns.PHONE.matcher(mobileNumber).matches()){
            return "Invalid Mobile Number"
        }
        return  null
    }

}