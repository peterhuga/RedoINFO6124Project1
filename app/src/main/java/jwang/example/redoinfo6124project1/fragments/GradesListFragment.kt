package jwang.example.redoinfo6124project1.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

    private lateinit var binding: FragmentGradesListBinding

    private lateinit var viewAdapter: GradeAdapter





    private var thisGradeList = arrayListOf<Grade>()

    private var isFabShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { it ->
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
        thisGradeList = gradeList.filter { it.courseName == param1 } as ArrayList<Grade>
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
        binding.fabLayoutAddProject.visibility = ConstraintLayout.INVISIBLE


        binding.tvCourseName.text = param2
        binding.fabLab.setOnClickListener {
            showDialogFragment(GradeType.LAB)
            hideFabMenu()
        }

        binding.fabExam.setOnClickListener {
            showDialogFragment(GradeType.EXAM)
            hideFabMenu()
        }

        binding.fabProject.setOnClickListener {
            showDialogFragment(GradeType.PROJECT)
            hideFabMenu()
        }


        viewAdapter = GradeAdapter(thisGradeList)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.recyclerView.adapter = viewAdapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedCourse: Grade =
                    thisGradeList[viewHolder.adapterPosition]

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                thisGradeList.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                viewAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                Snackbar.make(binding.recyclerView, deletedCourse.type.string, Snackbar.LENGTH_LONG)
                    .setAction("Undo") {

                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            thisGradeList.add(position, deletedCourse)

                            // below line is to notify item is
                            // added to our adapter class.
                            viewAdapter.notifyItemInserted(position)
                    }.show()
            } // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(binding.recyclerView)

        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        (context as MainActivity).toolbar.title = param1
        if (thisGradeList.size > 0) {
            binding.tvNoEntry.visibility = TextView.GONE
        }
    }



    private fun showFabMenu() {
        isFabShown = true
        //binding.fabShowMenu.isClickable = false
        binding.fabLayoutAddLab.visibility = ConstraintLayout.VISIBLE
        binding.fabLayoutAddExam.visibility = ConstraintLayout.VISIBLE
        binding.fabLayoutAddProject.visibility = ConstraintLayout.VISIBLE

        binding.recyclerView.animate().alpha(0.15f).apply {
            duration = 175
        }

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

        binding.fabLayoutAddProject.animate().translationYBy(
            -resources.getDimension(R.dimen.standard_55) - 20).apply {
            duration = 100
        }.withEndAction{
            binding.fabLayoutAddProject.animate().translationYBy(20.toFloat()).apply {
                duration = 75
            }
        }





        binding.fabShowMenu.animate().duration = 100
        binding.fabShowMenu.animate().rotationBy(45.0f)


    }

    private fun hideFabMenu() {
        isFabShown = false
        binding.recyclerView.animate().alpha(1.0f)
        binding.fabLayoutAddLab.animate()
            .translationYBy(resources.getDimension(R.dimen.standard_155))
            .withEndAction { binding.fabLayoutAddLab.visibility = ConstraintLayout.INVISIBLE }
        binding.fabLayoutAddExam.animate()
            .translationYBy(resources.getDimension(R.dimen.standard_105))
            .withEndAction { binding.fabLayoutAddExam.visibility = ConstraintLayout.INVISIBLE }
        binding.fabLayoutAddProject.animate()
            .translationYBy(resources.getDimension(R.dimen.standard_55))
            .withEndAction { binding.fabLayoutAddProject.visibility = ConstraintLayout.INVISIBLE }
        binding.fabShowMenu.animate().rotationBy(-45.0f)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.toolbar_list, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                (context as MainActivity).supportFragmentManager.popBackStackImmediate()
                Log.d("MyTag", "back clicked")
            }
            R.id.item_clear_all -> {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Warning")
                    .setMessage("Are you sure to delete all the records?")
                    .setCancelable(true)
                    .setPositiveButton("OK"){
                    d,w ->
                    Log.d("MyTag", "clear all clicked")
                        gradeList.removeAll(thisGradeList.toSet())
                    thisGradeList.clear()
                    viewAdapter.notifyDataSetChanged()
                    binding.tvNoEntry.visibility = TextView.VISIBLE
                        d.dismiss()
                    }
                    .setNegativeButton("Cancel"){
                        dialog, which -> dialog.cancel()
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialogFragment(type: GradeType){

        // Dialog can have only one view, so wrap all edittext views in a layout and add it to the dialog

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        val etDialogFullMark = EditText(context).apply {
            this.hint = "Full Mark"
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        val etDialogMyMark = EditText(context).apply {
            this.hint = "My Mark"
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        val etDialogWeight = EditText(context).apply {
            this.hint = "Weight(%)"
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        layout.addView(etDialogFullMark)
        layout.addView(etDialogMyMark)
        layout.addView(etDialogWeight)
        layout.setPadding(50, 20, 50, 20)

        val builder = AlertDialog.Builder(context)
        builder.setTitle(param1 + " " + type.string)
        builder.setView(layout)

        builder.setCancelable(false)
// Though this listener does nothing, it has to be implemented to show the positive button.
        builder.setPositiveButton("Save"){
                dialog, which -> Log.d("MyTag", "positive button")

        }
        builder.setNegativeButton("Cancel"){
                dialog, which -> dialog.cancel()
        }
/*
Disable dialog positive button dismiss the dialog if some check is not passed.
Don't create(). Only show(). Otherwise it won't work to overwrite the listner.
 */
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

                var grade = Grade(param1!!, type, fullMark.toInt(), myMark.toInt(), weight.toInt()
                )

                gradeList.add(grade)
                thisGradeList.add(grade)
                binding.tvNoEntry.visibility = TextView.GONE
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


