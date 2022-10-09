package jwang.example.redoinfo6124project1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import jwang.example.redoinfo6124project1.models.Course

class CourseAdapter (
    private val context: Context,
    private val courseList: List<Course>
    ) :BaseAdapter(){
        private lateinit var courseCodeTV: TextView
        private lateinit var currentGradeTV: TextView

    override fun getCount(): Int {
        return courseList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {



        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.course_listview_item, parent, false)
        courseCodeTV = convertView.findViewById(R.id.textViewCourseCode)
        currentGradeTV = convertView.findViewById(R.id.textViewCurrentGrade)
        courseCodeTV.text = courseList[position].courseCode
        currentGradeTV.text = courseList[position].currentGrade.toString()
        val lp = parent?.layoutParams
        lp?.height?.div(6)


        return convertView
    }

}