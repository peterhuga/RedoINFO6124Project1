package jwang.example.redoinfo6124project1

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import jwang.example.redoinfo6124project1.fragments.DashboardFragment
import jwang.example.redoinfo6124project1.fragments.GradesListFragment

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragment_placeholder,
                DashboardFragment.newInstance("ok")
            ).commit()
        }
    }
}