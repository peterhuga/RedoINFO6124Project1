package jwang.example.redoinfo6124project1.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import jwang.example.redoinfo6124project1.R
import jwang.example.redoinfo6124project1.databinding.FragmentDashboardBinding
import jwang.example.redoinfo6124project1.databinding.FragmentGradesListBinding


private const val ARG_PARAM1 = "param1"



class DashboardFragment : Fragment() {

    private var param1: String? = null
    private val toolbar: Toolbar by lazy {
        requireActivity().findViewById<Toolbar>(R.id.toolbar)
    }
    private lateinit var binding: FragmentDashboardBinding


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