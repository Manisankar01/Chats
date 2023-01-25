package com.example.chats

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.chats.databinding.SignUpLayoutBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var method: FireBaseMethods
    lateinit var binding: SignUpLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        method = FireBaseMethods()
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
            if((binding.etPassword.text.toString() == binding.etReEnterPassword.text.toString())) {
                method.creteAccount(
                    binding.etFirstName.text.toString(),
                    binding.etLastName.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etMobileNumber.text.toString(),
                    this)
                clearAllFields()
            }
        }
        binding.tvSignInText.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(
                androidx.appcompat.R.anim.abc_fade_in,
                androidx.appcompat.R.anim.abc_fade_out
            )
            this.finish()
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