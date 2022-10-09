package jwang.example.redoinfo6124project1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
                GradesListFragment.newInstance()
            ).commit()
        }
    }
}