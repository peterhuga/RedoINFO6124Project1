package jwang.example.redoinfo6124project1.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat.animate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import jwang.example.redoinfo6124project1.GradeAdapter
import jwang.example.redoinfo6124project1.MainActivity
import jwang.example.redoinfo6124project1.R
import jwang.example.redoinfo6124project1.databinding.FragmentGradesListBinding


class GradesListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private val toolbar: Toolbar by lazy {
        requireActivity().findViewById<Toolbar>(R.id.toolbar)
    }
    private lateinit var binding: FragmentGradesListBinding
    //private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: GradeAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager




    private var isFabShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGradesListBinding.inflate(inflater, container, false)
        binding.fabShowMenu.setOnClickListener{
            if (isFabShown) {
                hideFabMenu()
            } else {
                showFabMenu()
            }
        }
        binding.fabLayoutAddLab.visibility = ConstraintLayout.INVISIBLE
        binding.fabLayoutAddExam.visibility = ConstraintLayout.INVISIBLE


        binding.tvCourseName.text = param2
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(context as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        (context as MainActivity).toolbar.title = param1
    }

    private fun showFabMenu() {
        isFabShown = true
        //binding.fabShowMenu.isClickable = false
        binding.fabLayoutAddLab.visibility = ConstraintLayout.VISIBLE
        binding.fabLayoutAddExam.visibility = ConstraintLayout.VISIBLE

        binding.fabLayoutAddLab.animate().translationYBy(
            -resources.getDimension(R.dimen.standard_155) - 60).apply {
                duration = 100
        }.withEndAction{
            binding.fabLayoutAddLab.animate().translationYBy(60.toFloat()).apply {
                duration = 75
            }
        }

        binding.fabLayoutAddExam.animate().translationYBy(
            -resources.getDimension(R.dimen.standard_105) - 40).apply {
            duration = 100
        }.withEndAction{
            binding.fabLayoutAddExam.animate().translationYBy(40.toFloat()).apply {
                duration = 75
            }
        }





        binding.fabShowMenu.animate().duration = 100
        binding.fabShowMenu.animate().rotationBy(45.0f)


    }

    private fun hideFabMenu() {
        isFabShown = false
        binding.fabLayoutAddLab.animate()
            .translationYBy(resources.getDimension(R.dimen.standard_155))
            .withEndAction { binding.fabLayoutAddLab.visibility = ConstraintLayout.INVISIBLE }
        binding.fabLayoutAddExam.animate()
            .translationYBy(resources.getDimension(R.dimen.standard_105))
            .withEndAction { binding.fabLayoutAddExam.visibility = ConstraintLayout.INVISIBLE }
        binding.fabShowMenu.animate().rotationBy(-45.0f)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.toolbar_main, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                (context as MainActivity).supportFragmentManager.popBackStackImmediate()
            }
            R.id.item_about -> {
                    Log.d("MyTag", "about clicked")
                    true
                }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GradesListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}


