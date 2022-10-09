package jwang.example.redoinfo6124project1.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import jwang.example.redoinfo6124project1.CourseAdapter
import jwang.example.redoinfo6124project1.MainActivity
import jwang.example.redoinfo6124project1.R
import jwang.example.redoinfo6124project1.databinding.FragmentDashboardBinding
import jwang.example.redoinfo6124project1.models.Course


const val ARG_PARAM1 = "param1"



class DashboardFragment : Fragment() {

    private var param1: String? = null
    private val toolbar: Toolbar by lazy {
        requireActivity().findViewById<Toolbar>(R.id.toolbar)
    }
    private lateinit var binding: FragmentDashboardBinding

    val courses: List<Course> = listOf(
        Course("INFO6124", "Android Application Development", 0),
        Course("INFO6125", "iOS Application Development", 0),
        Course("INFO6126", "UI/UX Design" , 0),
        Course("INFO6127", "Enterprise Platform" , 0),
        Course("INFO6128", "Progressive Web Application" , 0),
        Course("INFO6129", "Cross-platform Application Development" , 0)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        toolbar.title = "Redo 6124 Project1"

        toolbar.inflateMenu(R.menu.toolbar_main)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_about -> {
                    Log.d("MyTag", "about clicked")
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }

        binding.listViewCourses.adapter = CourseAdapter(requireContext(), courses)
        binding.listViewCourses.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val fragParam = courses.get(position).courseCode
                val transaction = fragmentManager?.beginTransaction()
                if (transaction != null) {
                    transaction.replace(
                        R.id.fragment_placeholder,
                        GradesListFragment.newInstance(fragParam)
                    ).addToBackStack(null).commit()
                }
            }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}