package com.anant.quiz.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anant.quiz.R
import com.anant.quiz.util.GlobalVariable.Companion.RESULT
import kotlinx.android.synthetic.main.fragment_result.*
import java.util.*


class ResultFragment : Fragment(R.layout.fragment_result) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var score = 0
        for(i in RESULT){
            score += i
        }
        tvResult.text = "You Got $score/10 Points"

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    RESULT = ArrayList(Collections.nCopies(10, 0))
                    findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
                }
            }
        )

    }
}