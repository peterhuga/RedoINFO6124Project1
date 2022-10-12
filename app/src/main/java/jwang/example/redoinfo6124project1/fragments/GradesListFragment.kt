package jwang.example.redoinfo6124project1.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jwang.example.redoinfo6124project1.GradeAdapter
import jwang.example.redoinfo6124project1.MainActivity
import jwang.example.redoinfo6124project1.R
import jwang.example.redoinfo6124project1.databinding.FragmentGradesListBinding
import jwang.example.redoinfo6124project1.gradeList
import jwang.example.redoinfo6124project1.models.Grade
import jwang.example.redoinfo6124project1.models.GradeType


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
        binding.fabLab.setOnClickListener { showDialogFragment(GradeType.LAB) }
        viewAdapter = GradeAdapter(gradeList)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = viewAdapter
        Log.d("MyTag", "recyclerView")

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

    private fun showDialogFragment(type: GradeType){

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        val etDialogFullMark = EditText(context).apply { this.hint = "Full Mark"}
        val etDialogMyMark = EditText(context).apply { this.hint = "My Mark" }
        val etDialogWeight = EditText(context).apply { this.hint = "Weight(%)" }
        layout.addView(etDialogFullMark)
        layout.addView(etDialogMyMark)
        layout.addView(etDialogWeight)
        layout.setPadding(50, 20, 50, 20)

        val builder = AlertDialog.Builder(context)
        builder.setTitle(param1 + " " + type.string)
        builder.setView(layout)

        builder.setCancelable(false)




        builder.setPositiveButton("Save"){
                dialog, which -> Log.d("MyTag", "positive button")



        }
        builder.setNegativeButton("Cancel"){
                dialog, which -> dialog.cancel()
        }

        val dialog = builder.show()


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val fullMark = etDialogFullMark.text.toString()
            val myMark = etDialogMyMark.text.toString()
            val weight = etDialogWeight.text.toString()
            if (fullMark == ""|| myMark == "" || weight == ""){
                Toast.makeText(context, getString(R.string.empty_field), Toast.LENGTH_LONG).show()
            }else if (fullMark.toInt() > 100){
                Toast.makeText(context, getString(R.string.full_over_100),Toast.LENGTH_SHORT).show()
            }else if (myMark.toInt() > fullMark.toInt()) {
                Toast.makeText(context, getString(R.string.rcv_gt_full),Toast.LENGTH_SHORT).show()
            } else if (weight.toInt() > 100) {
                Toast.makeText(context, getString(R.string.perc_gt_100),Toast.LENGTH_SHORT).show()
            }else {

                var grade = Grade(
                    type, fullMark.toInt(), myMark.toInt(), weight.toInt()
                )

                gradeList.add(grade)

                dialog.dismiss()
                Log.d("MyTag", "array size: ${gradeList.size}")
            }
        }
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


