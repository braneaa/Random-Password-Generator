package an.projects.randompasswordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var numbers = arrayOf(6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30)
    private var yesOrNo = arrayOf("Yes", "No")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapterPasswordLength = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, numbers)
        number_of_characters_spinner.adapter = arrayAdapterPasswordLength

        val arrayAdapterYesOrNo = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yesOrNo)
        symbol_spinner.adapter = arrayAdapterYesOrNo
        number_spinner.adapter = arrayAdapterYesOrNo
        capital_letter_spinner.adapter = arrayAdapterYesOrNo

        generate_btn.setOnClickListener {
            val numberOfCharacters = number_of_characters_spinner.selectedItem as Int
            val symbol = symbol_spinner.selectedItem.toString()
            val number = number_spinner.selectedItem.toString()
            val capitalLetter = capital_letter_spinner.selectedItem.toString()
            tv_password.text = generatePassword(numberOfCharacters, symbol , number, capitalLetter)
        }

    }

    fun generatePassword(numberOfCharacters: Int, symbol : String, number: String, capitalLetter: String): String {

        lateinit var charPool: List<Char>

        if(symbol == "Yes" && number == "Yes" && capitalLetter == "Yes") {
            charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9') + '!' + '@' + '#' + '$' + '%' + '^' + '&' + '*' + '(' + ')'
        }
        else if (symbol == "No" && number == "Yes" && capitalLetter == "Yes"){
            charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        }
        else if (symbol == "No" && number == "No" && capitalLetter == "Yes"){
            charPool = ('a'..'z') + ('A'..'Z')
        }
        else if (symbol == "Yes" && number == "No" && capitalLetter == "Yes"){
            charPool = ('a'..'z') + ('A'..'Z') + '!' + '@' + '#' + '$' + '%' + '^' + '&' + '*' + '(' + ')'
        }
        else if (symbol == "No" && number == "No" && capitalLetter == "No"){
            charPool = ('b'..'z') + 'a'
        }
        else if (symbol == "Yes" && number == "No" && capitalLetter == "No"){
            charPool = ('a'..'z') + '!' + '@' + '#' + '$' + '%' + '^' + '&' + '*' + '(' + ')'
        }
        else if (symbol == "No" && number == "Yes" && capitalLetter == "No"){
            charPool = ('a'..'z') + ('0'..'9')
        }
        else if (symbol == "Yes" && number == "Yes" && capitalLetter == "No"){
            charPool = ('a'..'z') + ('0'..'9') + '!' + '@' + '#' + '$' + '%' + '^' + '&' + '*' + '(' + ')'
        }

        return (1..numberOfCharacters)
            .map { i -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

}