package com.gamecodeschool.mycalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvResults: TextView? = null // Creates a nullable text view
    // Variable named tvResults
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResults = findViewById<TextView>(R.id.tvResults)
        // Initializes the tvResults variable to the tvResults ID in the layout
    }
    fun onDigit(view: View){ // view takes the view of whatever the input using this method is
        tvResults?.append((view as Button).text) // Takes the input as a button, and converts it to text
        lastNumeric = true
        lastDot = false
    }
    fun onClear(view: View){
        tvResults?.text = ""
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvResults?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        tvResults?.text?.let{ // Needs to be used to allow the string input to pass through
            if(lastNumeric && !isOperatorAdded(it.toString())){
                // Allows for the Operator input to pass through as well as the boolean
                tvResults?.append((view as Button).text) // Appends the operator to the textview
                lastNumeric = false
                lastDot = false
            }
        }
    }
    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvResults?.text.toString() // Takes the textview and converts it from a text to a string
            var prefix = ""
            try {
                // Defining a method to deal with negative numbers not breaking the function
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                    // Takes out the text before index 1, so in this case index 0 which will be -
                }
                if (tvValue.contains("-")){ //Subtraction function
                    val splitValue = tvValue.split("-")
                    // Splits the tvValue string at the defined char into two arrays
                    var stringOne = splitValue[0]
                    val stringTwo = splitValue[1]

                    // Checks is there was a leading negative sign, if so it reapplies it
                    // before running the calculation
                    if (prefix.isNotEmpty()){
                        stringOne = prefix + stringOne
                    }

                    tvResults?.text = removeDecimalZero((stringOne.toDouble() - stringTwo.toDouble()).toString())
                    // Prints the result of the function described
                }
                else if (tvValue.contains("+")){ //Subtraction function
                    val splitValue = tvValue.split("+")
                    // Splits the tvValue string at the defined char into two arrays
                    var stringOne = splitValue[0]
                    val stringTwo = splitValue[1]

                    // Checks is there was a leading negative sign, if so it reapplies it
                    // before running the calculation
                    if (prefix.isNotEmpty()){
                        stringOne = prefix + stringOne
                    }

                    tvResults?.text = removeDecimalZero((stringOne.toDouble() + stringTwo.toDouble()).toString())
                    // Prints the result of the function described
                }
                else if (tvValue.contains("/")){ //Subtraction function
                    val splitValue = tvValue.split("/")
                    // Splits the tvValue string at the defined char into two arrays
                    var stringOne = splitValue[0]
                    val stringTwo = splitValue[1]

                    // Checks is there was a leading negative sign, if so it reapplies it
                    // before running the calculation
                    if (prefix.isNotEmpty()){
                        stringOne = prefix + stringOne
                    }

                    tvResults?.text = removeDecimalZero((stringOne.toDouble() / stringTwo.toDouble()).toString())
                    // Prints the result of the function described
                }
                else if (tvValue.contains("*")){ //Subtraction function
                    val splitValue = tvValue.split("*")
                    // Splits the tvValue string at the defined char into two arrays
                    var stringOne = splitValue[0]
                    val stringTwo = splitValue[1]

                    // Checks is there was a leading negative sign, if so it reapplies it
                    // before running the calculation
                    if (prefix.isNotEmpty()){
                        stringOne = prefix + stringOne
                    }

                    tvResults?.text = removeDecimalZero((stringOne.toDouble() * stringTwo.toDouble()).toString())
                    // Prints the result of the function described
                }
            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeDecimalZero(result: String) : String {
        // Creates a function to remove .0 at the end of whole number results
    // Passes a string as "result" and then returns a string.
        var value = result
        if (result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }

        // Checks for an operator string
    private fun isOperatorAdded(value : String) : Boolean{ //Passes a string and returns a boolean
        return if(value.startsWith("-")){
            false
        }
        else value.contains("/")
                || value.contains("*")
                || value.contains("-")
                || value.contains("+")
    }
}