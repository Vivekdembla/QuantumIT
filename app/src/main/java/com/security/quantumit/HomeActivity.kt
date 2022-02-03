package com.security.quantumit

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.security.quantumit.Adapter.HomeAdapter
import com.security.quantumit.Data.homeData


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar

//        toolbar.setLogo(R.drawable.google) // set logo for Toolbar
//        toolbar.title = "HOME"


        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        val home_RV = findViewById<RecyclerView>(R.id.main_RecyclerView)
        home_RV.layoutManager = LinearLayoutManager(this)
        val homeData = homeData()
        home_RV.adapter = HomeAdapter(this,homeData)
    }
}