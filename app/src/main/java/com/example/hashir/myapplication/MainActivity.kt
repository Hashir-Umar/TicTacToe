package com.example.hashir.TicTacToe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var player: Int = 1
    var sym = "o"
    val x: IntArray = intArrayOf(-1, -1, -1,
                                -1, -1, -1,
                                -1, -1, -1)
    var flag = "main"
    var turn = 0

    lateinit var player1Name: String
    lateinit var player2Name: String

    lateinit var btn0 : Button
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btnRestart : Button

    lateinit var title : TextView
    lateinit var p1 : TextView
    lateinit var p2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        p1  = findViewById(R.id.p1)
        p2  = findViewById(R.id.p2)

        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)

        btnRestart = findViewById(R.id.btnRestart)
        title = findViewById(R.id.titleGame)

        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btnRestart.setOnClickListener(this)

    }

    override fun onClick(obj: View?) {

        when (obj?.id) {

            R.id.btn0 -> {
                if (setMark(0)) btn0.text = sym else return
            }
            R.id.btn1 -> {
                if (setMark(1)) btn1.text = sym else return
            }
            R.id.btn2 -> {
                if (setMark(2)) btn2.text = sym else return
            }
            R.id.btn3 -> {
                if (setMark(3)) btn3.text = sym else return
            }
            R.id.btn4 -> {
                if (setMark(4)) btn4.text = sym else return
            }
            R.id.btn5 -> {
                if (setMark(5)) btn5.text = sym else return
            }
            R.id.btn6 -> {
                if (setMark(6)) btn6.text = sym else return
            }
            R.id.btn7 -> {
                if (setMark(7)) btn7.text = sym else return
            }
            R.id.btn8 -> {
                if (setMark(8)) btn8.text = sym else return
            }
            R.id.btnRestart -> {

                if (flag == "main") {

                    if (p1.text.toString().isEmpty() || p2.text.toString().isEmpty()) {

                        Toast.makeText(this, "Enter Both Player Names!!!", Toast.LENGTH_LONG).show()
                        return
                    } else {

                        p1.visibility = View.GONE
                        p2.visibility = View.GONE

                        btnRestart.text = "Restart Game"

                        title.visibility = View.VISIBLE

                        player1Name = p1.text.toString()
                        player2Name = p2.text.toString()

                        var l = findViewById<LinearLayout>(R.id.linearLayout)
                        l.visibility = View.VISIBLE

                        flag = "play"
                        return
                    }
                } else if (flag == "play") {

                    initGame()
                    btnRestart.text = "Restart Game"
                    return
                } else if (flag == "again") {
                    var l = findViewById<LinearLayout>(R.id.linearLayout)
                    l.visibility = View.VISIBLE
                    btnRestart.text = "Restart Game"
                    initGame()
                    flag = "play"
                    return
                }
            }
        }
        var win = isPlayerWins();
        if (win || turn == 9) {

            var l = findViewById<LinearLayout>(R.id.linearLayout)
            l.visibility = View.GONE
                
            if(win) {
                if (player == 1)
                    title.text = "${player1Name} Wins"
                else
                    title.text = "${player2Name} Wins"
            }
            else
                title.text = "Draw"
            
            btnRestart.text = "Play Again"
            flag = "again"

            return
        }

        if(player == 1) {
            player = 2
            sym = "x"
        }
        else {
            player = 1
            sym = "o"
        }

        if(player == 1)
            title.text = "${player1Name}'s Turn"
        else
            title.text = "${player2Name}'s Turn"
    }

    fun setMark(index: Int): Boolean
    {
        if(x[index] == -1) {
            x[index] = player
            turn+=1
            return true
        }

        return false
    }

    fun isPlayerWins(): Boolean
    {
        var outer = 0
        var check : Boolean =  true

        //horizontal
        while(outer < 9) {
            check = true
            for (i in 0..2) {
                if (x[i + outer] != player)
                   check = false
            }
            if(check == true) return true
            outer += 3
        }

        //Vertical
        outer = 0
        while(outer < 3) {
            check = true
            for (i in 0..8 step 3) {
                if(x[i + outer] != player)
                    check = false
            }

            if(check == true) return true
            outer += 1
        }

        //Left Diagonal
        check = true
        for (i in 0..8 step 4)
            if(x[i] != player)
                check = false

         if(check == true) return true

        //Right Diagonal
        check = true
        for (i in 6 downTo 2 step 2)
            if(x[i] != player)
                check = false

         if(check == false) return false

        return true;
    }

    fun initGame() :Unit{

        player = 1
        title.text = "${player1Name}'s Turns"
        sym = "o"
        for (i in 0..8)
            x[i] = -1
        turn = 0
        btn0.text = ""
        btn1.text = ""
        btn2.text = ""
        btn3.text = ""
        btn4.text = ""
        btn5.text = ""
        btn6.text = ""
        btn7.text = ""
        btn8.text = ""

    }

}
