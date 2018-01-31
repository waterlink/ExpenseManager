package com.iwillteachyoukotlin.expensemanager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.iwillteachyoukotlin.expensemanager.domain.Greeting
import com.iwillteachyoukotlin.expensemanager.presentation.expense.EnterExpenseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        greeting.text = Greeting().greet()
    }

    fun onAddExpenseButtonClick(view: View) {
        val intent = Intent(this, EnterExpenseActivity::class.java)
        startActivity(intent)
    }
}
