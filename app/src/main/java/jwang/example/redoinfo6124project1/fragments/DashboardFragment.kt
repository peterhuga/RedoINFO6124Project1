package jwang.example.redoinfo6124project1.fragments

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
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
const val ARG_PARAM2 = "param2"



class DashboardFragment : Fragment() {

    private var param1: String? = null
    private val toolbar: Toolbar by lazy {
        requireActivity().findViewById<Toolbar>(R.id.toolbar)
    }
    private lateinit var binding: FragmentDashboardBinding

    val courses: List<Course> = listOf(
        Course("INFO6124", "Android App Development", 0),
        Course("INFO6125", "iOS App Development", 0),
        Course("INFO6126", "UI/UX Design" , 0),
        Course("INFO6127", "Enterprise Platform" , 0),
        Course("INFO6128", "Progressive Web Application" , 0),
        Course("INFO6129", "Cross-platform App Development" , 0)
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
        //toolbar.title = "Redo 6124 Project1"
        setHasOptionsMenu(true)


  //      toolbar.inflateMenu(R.menu.toolbar_main)
//
//        toolbar.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.item_about -> {
//                    Log.d("MyTag", "about clicked")
//                    true
//                }
//                else -> {
//                    super.onOptionsItemSelected(it)
//                }
//            }
//        }

        binding.listViewCourses.adapter = CourseAdapter(requireContext(), courses)
        binding.listViewCourses.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val courseCode = courses.get(position).courseCode
                val courseName = courses.get(position).courseName
                val transaction = fragmentManager?.beginTransaction()
                if (transaction != null) {
                    transaction.replace(
                        R.id.fragment_placeholder,
                        GradesListFragment.newInstance(courseCode, courseName)
                    ).addToBackStack(null).commit()
                }
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (context as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)

        //(context as MainActivity).scrollable_toolbar.isTitleEnabled = false
        (context as MainActivity).toolbar.title = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.toolbar_main, menu)

        super.onCreateOptionsMenu(menu, inflater)
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