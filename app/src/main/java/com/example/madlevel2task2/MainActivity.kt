package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_question.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews(){
        binding.rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter
        binding.rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        for (i in Question.QUESTIONS.indices){
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()

        swipeLeftHelper().attachToRecyclerView(rvQuestions)
        swipeRightHelper().attachToRecyclerView(rvQuestions)
    }

    private fun swipeLeftHelper(): ItemTouchHelper{

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (!questions[position].questionAnswer){ //correct
                    Snackbar.make(txtQuestion, "Correctly answered!", Snackbar.LENGTH_SHORT).show()
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                }
                else{
                    Snackbar.make(txtQuestion, "Incorrect, question wont be removed!", Snackbar.LENGTH_SHORT).show()
                    questionAdapter.notifyDataSetChanged()
                }
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun swipeRightHelper(): ItemTouchHelper{

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (questions[position].questionAnswer){ //correct
                    Snackbar.make(txtQuestion, "Correctly answered!", Snackbar.LENGTH_SHORT).show()
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                }
                else{
                    Snackbar.make(txtQuestion, "Incorrect, question wont be removed!", Snackbar.LENGTH_SHORT).show()
                    questionAdapter.notifyDataSetChanged()
                }
            }
        }
        return ItemTouchHelper(callback)
    }
}