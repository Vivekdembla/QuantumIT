package com.security.quantumit

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.security.quantumit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN: Int = 1001
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityMainBinding
    //Google SignIn
    var googleSignInClient: GoogleSignInClient? = null
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        signinpage()
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor = ContextCompat.getColor(this, R.color.brown)
        setContentView(binding.root)
        auth = Firebase.auth
        setSupportActionBar(binding.loginToolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        //SIGN UP
        binding.signup.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.fillEmailSignup.text.toString(),
                binding.fillPasswordsignup.text.toString()
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Registration successful!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {

                    // Registration failed
                    Toast.makeText(
                        getApplicationContext(),
                        "Registration failed!!"
                                + " Please try again later\n" + task.exception,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }

        //SIGN IN
        binding.signin.setOnClickListener {
            val email = binding.fillEmail.text.toString()
            val password = binding.fillPassword.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            getApplicationContext(),
                            "Login successful!!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        startActivity(Intent(this,HomeActivity::class.java))
                        finish()
                    } else {
                        // sign-in failed
                        Toast.makeText(
                            getApplicationContext(),
                            "Login failed!!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
        }
        binding.signinpage.setOnClickListener {
            signinpage()
        }
        binding.register.setOnClickListener{
            signuppage()
        }
        binding.signuppage.setOnClickListener {
            signuppage()
        }

        //Using Google
        binding.googleAuthentication.setOnClickListener{
            signInToGoogle()
        }
        configureGoogleClient()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                showToastMessage("Google Sign in Succeeded")
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                showToastMessage("Google Sign in Failed $e")
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = firebaseAuth!!.currentUser
                    Log.d(TAG, "signInWithCredential:success: currentUser: " + user!!.email)
                    showToastMessage("Firebase Authentication Succeeded ")
                    launchMainActivity(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    showToastMessage("Firebase Authentication failed:" + task.exception)
                }
            }
    }

    private fun showToastMessage(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }

    private fun launchMainActivity(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }

    private fun signInToGoogle() {
        val signInIntent = googleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    fun signinpage(){
        binding.createAcc.visibility = View.GONE
        binding.name.visibility = View.GONE
        binding.fillName.visibility = View.GONE
        binding.emailsignup.visibility = View.GONE
        binding.fillEmailSignup.visibility = View.GONE
        binding.contact.visibility = View.GONE
        binding.fillContact.visibility = View.GONE
        binding.passwordsignup.visibility = View.GONE
        binding.fillPasswordsignup.visibility = View.GONE
        binding.signup.visibility = View.GONE
        binding.signuppage.setBackgroundResource(R.color.transparent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.signuppage.backgroundTintList = ContextCompat.getColorStateList(this, R.color.transparent)
        }
        binding.signupDec.visibility = View.GONE

        binding.register.visibility = View.VISIBLE
        binding.dont.visibility = View.VISIBLE
        binding.siginDec.visibility = View.VISIBLE
        binding.email.visibility = View.VISIBLE
        binding.fillEmail.visibility = View.VISIBLE
        binding.password.visibility = View.VISIBLE
        binding.fillPassword.visibility = View.VISIBLE
        binding.signin.visibility = View.VISIBLE
        binding.signinAcc.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.signinpage.backgroundTintList = ContextCompat.getColorStateList(this, R.color.brown)
        }
        binding.with.visibility = View.VISIBLE
        binding.googleAuthentication.visibility = View.VISIBLE

    }
    fun signuppage(){
        binding.createAcc.visibility = View.VISIBLE
        binding.name.visibility = View.VISIBLE
        binding.fillName.visibility = View.VISIBLE
        binding.emailsignup.visibility = View.VISIBLE
        binding.fillEmailSignup.visibility = View.VISIBLE
        binding.contact.visibility = View.VISIBLE
        binding.fillContact.visibility = View.VISIBLE
        binding.passwordsignup.visibility = View.VISIBLE
        binding.fillPasswordsignup.visibility = View.VISIBLE
        binding.signup.visibility = View.VISIBLE
        binding.signupDec.visibility = View.VISIBLE
        binding.signuppage.setBackgroundResource(R.drawable.bottom_round)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.signuppage.backgroundTintList = ContextCompat.getColorStateList(this, R.color.brown)
        }


        binding.register.visibility = View.GONE
        binding.dont.visibility = View.GONE
        binding.siginDec.visibility = View.GONE
        binding.email.visibility = View.GONE
        binding.fillEmail.visibility = View.GONE
        binding.password.visibility = View.GONE
        binding.fillPassword.visibility = View.GONE
        binding.signin.visibility = View.GONE
        binding.signinAcc.visibility = View.GONE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.signinpage.backgroundTintList = ContextCompat.getColorStateList(this, R.color.transparent)
        }

        binding.with.visibility = View.GONE
        binding.googleAuthentication.visibility = View.GONE
    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureGoogleClient() {
        // Configure Google Sign In
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) // for the requestIdToken, this is in the values.xml file that
                // is generated from your google-services.json
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // Set the dimensions of the sign-in button.
        binding.googleAuthentication.setSize(SignInButton.SIZE_WIDE)
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()
    }
}