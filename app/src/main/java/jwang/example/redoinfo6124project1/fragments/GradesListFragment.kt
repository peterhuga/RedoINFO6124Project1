package jwang.example.redoinfo6124project1.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import jwang.example.redoinfo6124project1.GradeAdapter
import jwang.example.redoinfo6124project1.R


class GradesListFragment : Fragment() {
    private val toolbar: Toolbar by lazy {
        requireActivity().findViewById<Toolbar>(R.id.toolbar)
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: GradeAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var fabLabLayout: ConstraintLayout
    private lateinit var fabShowMenu: FloatingActionButton

    private var isFabShown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grades_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabLabLayout = requireActivity().findViewById(R.id.fabLayoutAddLab)
        fabShowMenu = requireActivity().findViewById(R.id.fabShowMenu)
        fabLabLayout.visibility = ConstraintLayout.INVISIBLE


//        setHasOptionsMenu(true)
//        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        (context as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)


        //(context as MainActivity).scrollable_toolbar.isTitleEnabled = false
        //(context as MainActivity).toolbar.title = "haha"
        toolbar.title = "My Grades"
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.inflateMenu(R.menu.toolbar_main)
//        toolbar.setNavigationOnClickListener{
//        }
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
        fabShowMenu.setOnClickListener{
            if (isFabShown) {
                hideFabMenu()
            } else {
                showFabMenu()
            }
        }
    }









    private fun showFabMenu() {
        isFabShown = true
        fabLabLayout.visibility = ConstraintLayout.VISIBLE
    }

    private fun hideFabMenu() {
        isFabShown = false
        fabLabLayout.visibility = ConstraintLayout.INVISIBLE

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            GradesListFragment()
    }
}