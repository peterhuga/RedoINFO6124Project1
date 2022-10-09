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
            fragmentManager?.popBackStack()
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
        //binding.fabShowMenu.isClickable = false
        binding.fabLayoutAddLab.visibility = ConstraintLayout.VISIBLE

        binding.fabLayoutAddLab.animate().translationYBy(
            -resources.getDimension(R.dimen.standard_155) - 60).apply {
                duration = 100
        }.withEndAction{
            binding.fabLayoutAddLab.animate().translationYBy(60.toFloat()).apply {
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
        binding.fabShowMenu.animate().rotationBy(-45.0f)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GradesListFragment()
    }


}


