package jwang.example.redoinfo6124project1

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jwang.example.redoinfo6124project1.fragments.DashboardFragment
import jwang.example.redoinfo6124project1.fragments.GradesListFragment
import jwang.example.redoinfo6124project1.models.Grade
import jwang.example.redoinfo6124project1.models.GradeType
import java.io.*

var gradeList: ArrayList<Grade> = arrayListOf(
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

    override fun onStop() {
        val ofile: FileOutputStream = openFileOutput("test.json", MODE_PRIVATE)
        val osw = OutputStreamWriter(ofile)
        var jsonList = Gson().toJson(gradeList)
        for(grade in jsonList)
        {
            osw.write(grade.toString())
        }
        osw.flush()
        osw.close()
        Log.d("MyTag", "file written")
        super.onStop()
    }

    override fun onStart() {
        super.onStart()

        try {

            val fin: FileInputStream = openFileInput("test.json")
            val isr = InputStreamReader(fin)
            val inputBuffer = CharArray(100)
            var str = ""
            var charRead: Int
            while (isr.read(inputBuffer).also { charRead = it } > 0) {
                val readString = String(inputBuffer, 0, charRead)
                str += readString
            }
            val listGradeType = object: TypeToken<ArrayList<Grade>>() {}.type
            gradeList = Gson().fromJson(str, listGradeType)
            Log.d("MyTag", "list: ${gradeList.size}")



        } catch (ioe: IOException) {
            Log.d("MyTag", "Read file fails")
            ioe.printStackTrace()

        }

    }
}