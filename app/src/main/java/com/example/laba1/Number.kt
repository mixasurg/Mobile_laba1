package com.example.laba1

import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class Number(
    private val editText: TextInputEditText,
    private val textView: TextView,
    private val textViewPosIndex: TextView,
    private val textViewNegIndex: TextView
) {

    fun setButtonListener(button: Button) {
        button.setOnClickListener {
            var count = editText.text.toString().toIntOrNull()
            var numbers = generateRandomNumbers(count)
            textView.text = numbers.joinToString(", ")
            val Positive = numbers.indexOfLast { it > 0 }
            val Negative = numbers.indexOfFirst{ it < 0 }

            if (Negative != -1) {
                textViewNegIndex.text = "Индекс первого отрицательного: " + (Negative+1)
            } else {
                textViewNegIndex.text = "Отрицательных чисел нет."
            }

            if (Positive != -1) {
                textViewPosIndex.text = "Индекс последнего положительного: " + (Positive+1)
            } else {
                textViewPosIndex.text = "Положительных чисел нет."
            }

            highlightNumbers(textView, Negative, Positive)
            textView.isVisible = true
            textViewNegIndex.isVisible = true
            textViewPosIndex.isVisible = true
        }
    }

    fun generateRandomNumbers(count: Int? = 5): List<Int> {
        return List(count!!) { Random.nextInt(-10, 11) }
    }

    fun highlightNumbers(displayArea: TextView, Negative: Int, Positive: Int) {
        val highlightedText = StringBuilder()
        val numbers = displayArea.text.split(", ").map { it.trim() }

        for (i in numbers.indices) {
            when {
                i == Negative -> highlightedText.append("[${numbers[i]}]")
                i == Positive -> highlightedText.append("[${numbers[i]}]")
                else -> highlightedText.append("${numbers[i]} ")
            }
        }

        displayArea.text = highlightedText.toString()
    }
}