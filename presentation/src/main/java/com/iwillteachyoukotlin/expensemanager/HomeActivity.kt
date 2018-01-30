package com.iwillteachyoukotlin.expensemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.iwillteachyoukotlin.expensemanager.domain.Greeting
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        greeting.text = Greeting().greet()
    }
}
