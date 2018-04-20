package com.faisal.totassignment.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.faisal.totassignment.R
import com.faisal.totassignment.ui.fragment.AddStudentInfoFragment
import com.faisal.totassignment.ui.fragment.ShowStudentInfoFragment
import com.faisal.totassignment.ui.fragment.WebviewFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> {
                selectedFragment = WebviewFragment.newInstance()
            }
            R.id.navigation_dashboard -> {
                selectedFragment = AddStudentInfoFragment.newInstance()
            }
            R.id.navigation_notifications -> {
                selectedFragment = ShowStudentInfoFragment.newInstance()

            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout_main, selectedFragment)
        transaction.commit()
        return@OnNavigationItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
