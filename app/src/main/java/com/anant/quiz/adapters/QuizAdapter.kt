package com.anant.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anant.quiz.R
import com.anant.quiz.models.Result
import com.anant.quiz.util.GlobalVariable.Companion.RESULT
import kotlinx.android.synthetic.main.item_question.view.*

class QuizAdapter: RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object:DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        return QuizViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_question,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val questions = differ.currentList[position]
        val questionShuffle = ArrayList<String>()
        questionShuffle.add(questions.correct_answer)
        questionShuffle.addAll(questions.incorrect_answers)
        questionShuffle.shuffle()

        holder.itemView.apply {
            tvQuestion.text = questions.question
            rbOption1.text = questionShuffle[0]
            rbOption2.text = questionShuffle[1]
            rbOption3.text = questionShuffle[2]
            rbOption4.text = questionShuffle[3]

            rgAnswer.setOnCheckedChangeListener { _, _ ->
                RESULT[position] = if (findViewById<RadioButton>(
                        rgAnswer.checkedRadioButtonId).text == questions.correct_answer)
                    1 else 0
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}