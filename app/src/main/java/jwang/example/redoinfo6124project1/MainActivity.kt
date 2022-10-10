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
import jwang.example.redoinfo6124project1.models.Grade
import jwang.example.redoinfo6124project1.models.GradeType

val gradeList: List<Grade> = listOf(
    Grade(GradeType.LAB, 20, 17, 10),
    Grade(GradeType.PROJECT, 20, 17, 10),
    Grade(GradeType.LAB, 20, 17, 10),
    Grade(GradeType.EXAM, 20, 17, 10),
    Grade(GradeType.LAB, 20, 17, 10)

)

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fragment_placeholder,
                DashboardFragment.newInstance("ok")
            ).commit()
        }
    }
}