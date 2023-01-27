package com.anant.quiz.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anant.quiz.R
import com.anant.quiz.util.GlobalVariable.Companion.CATEGORY
import com.anant.quiz.util.GlobalVariable.Companion.DIFFICULTY
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.system.exitProcess


class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val TAG = "QuestionF"

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    exitProcess(0)
                }
            })

            rgDifficulty.setOnCheckedChangeListener { _, btnid ->
                DIFFICULTY = view.findViewById<RadioButton>(btnid).text.toString()
            }

            spCategories.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    CATEGORY = position + 9
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }



        btnStartQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionsFragment)
        }
    }
}