package jwang.example.redoinfo6124project1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import jwang.example.redoinfo6124project1.MainActivity
import jwang.example.redoinfo6124project1.R


class AboutFragment : Fragment() {

    private val toolbar: Toolbar by lazy {
        requireActivity().findViewById<Toolbar>(R.id.toolbar)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context as MainActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        (context as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setTitle(R.string.menu_about)
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            AboutFragment()
    }
}