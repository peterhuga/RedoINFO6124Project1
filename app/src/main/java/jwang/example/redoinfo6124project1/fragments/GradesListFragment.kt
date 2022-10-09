package jwang.example.redoinfo6124project1.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import jwang.example.redoinfo6124project1.GradeAdapter
import jwang.example.redoinfo6124project1.MainActivity
import jwang.example.redoinfo6124project1.R
import jwang.example.redoinfo6124project1.databinding.FragmentGradesListBinding


class GradesListFragment : Fragment() {
    private val toolbar: Toolbar by lazy {
        requireActivity().findViewById<Toolbar>(R.id.toolbar)
    }
    private lateinit var binding: FragmentGradesListBinding
    //private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: GradeAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager




    private var isFabShown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_grades_list, container, false)
        binding = FragmentGradesListBinding.inflate(inflater, container, false)
        binding.fabShowMenu.setOnClickListener{
            if (isFabShown) {
                hideFabMenu()
            } else {
                showFabMenu()
            }
        }
        binding.fabLayoutAddLab.visibility = ConstraintLayout.INVISIBLE

        toolbar.title = "My Grades"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.inflateMenu(R.menu.toolbar_main)
        toolbar.setNavigationOnClickListener{
        }
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
        return binding.root

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setHasOptionsMenu(true)
//        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        (context as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
//        (context as MainActivity).scrollable_toolbar.isTitleEnabled = false
//        (context as MainActivity).toolbar.title = "haha"
//    }









    private fun showFabMenu() {
        isFabShown = true
        binding.fabLayoutAddLab.visibility = ConstraintLayout.VISIBLE
    }

    private fun hideFabMenu() {
        isFabShown = false
        binding.fabLayoutAddLab.visibility = ConstraintLayout.INVISIBLE

    }

    private fun fabAnimation(view:View) {
        //val anim: Animation = AnimationUtils.loadAnimation(view.context, translate_position )
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            GradesListFragment()
    }
}