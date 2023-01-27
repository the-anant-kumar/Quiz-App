package com.anant.quiz.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anant.quiz.R
import com.anant.quiz.adapters.QuizAdapter
import com.anant.quiz.ui.MainActivity
import com.anant.quiz.ui.QuizViewModel
import com.anant.quiz.util.GlobalVariable.Companion.CATEGORY
import com.anant.quiz.util.GlobalVariable.Companion.DIFFICULTY
import com.anant.quiz.util.Resource
import kotlinx.android.synthetic.main.fragment_questions.*


class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    private lateinit var viewModel: QuizViewModel
    private lateinit var quizAdapter: QuizAdapter
    private val TAG = "QuestionF"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.getQuestions(CATEGORY, DIFFICULTY)


        viewModel.questions.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    Log.d(TAG, "Successssssssss")
                    response.data?.let { quizResponse ->
                        quizAdapter.differ.submitList(quizResponse.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "Error Occurred: $message", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }

        fabSubmit.setOnClickListener {
            findNavController().navigate(R.id.action_questionsFragment_to_resultFragment)
        }

    }

    private fun hideProgressBar() {
        pbQuestions.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        pbQuestions.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        quizAdapter = QuizAdapter()
        rvQuestionContainer.apply {
            adapter = quizAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}