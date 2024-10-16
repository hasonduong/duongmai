package com.example.calc1

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calc1.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textReuslt: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textReuslt = findViewById(R.id.textView)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnBS).setOnClickListener(this)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnAddSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
        findViewById<Button>(R.id.btnMal).setOnClickListener(this)
    }

    val btnNum = arrayOf(R.id.btn0, R.id.btn1,
        R.id.btn2, R.id.btn3,
        R.id.btn4, R.id.btn5,
        R.id.btn6, R.id.btn7,
        R.id.btn8, R.id.btn9)

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id in btnNum) {
            var num: Int = (p0 as? Button)?.text.toString().toIntOrNull() ?: 0
            addDigit(num)
        } else if (id == R.id.btnAdd) {
            if (state == 2) {
                op1 = calculate()
                op2 = 0
                textReuslt.text = "0"
                op = 1
            } else {
                state = 2
                textReuslt.text = "0"
                op = 1
            }

        } else if (id == R.id.btnSub) {
            if (state == 2) {
                op1 = calculate()
                textReuslt.text = "0"
                op = 2
            } else {
                state = 2
                textReuslt.text = "0"
                op = 2
            }
        } else if (id == R.id.btnMal) {
            if (state == 2) {
                op1 = calculate()
                textReuslt.text = "0"
                op = 3
            } else {
                state = 2
                textReuslt.text = "0"
                op = 3
            }
        } else if (id == R.id.btnDivide) {
            if (state == 2) {
                op1 = calculate()
                textReuslt.text = "0"
                op = 4
            } else {
                state = 2
                textReuslt.text = "0"
                op = 4
            }
        } else if (id == R.id.btnEqual) {
            if (state == 2) {
                var rs = calculate()
                textReuslt.text = "$rs"
                state = 1
                op1 = 0
                op2 = 0
                op = 0
            }
        } else if (id == R.id.btnC) {
            state = 1
            op1 = 0
            op2 = 0
            op = 0
            textReuslt.text = "0"
        } else if (id == R.id.btnCE) {
            if (state == 1) {
                op1 = 0
                textReuslt.text = "0"
            } else {
                op2 = 0
                textReuslt.text = "0"
            }
        } else if (id == R.id.btnBS) {
            if (state == 1) {
                op1 /= 10
                textReuslt.text = "$op1"
            } else {
                op2 /= 10
                textReuslt.text = "$op2"
            }
        }
    }

    fun calculate(): Int {
        //+: 1, -: 2, x: 3, /: 4
        if (op == 1) {
            return op1 + op2
        } else if (op == 2) {
            return op1 - op2
        } else if (op == 3) {
            return op1 * op2
        } else {
            if (op2 == 0)
                return Int.MAX_VALUE
            else
                return op1/op2
        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1*10 + c
            textReuslt.text = "$op1"
        } else {
            op2 = op2*10 + c
            textReuslt.text = "$op2"
        }
    }
}