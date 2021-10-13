package com.example.encode_decode

import android.icu.util.Currency
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.beans.IndexedPropertyChangeEvent
import java.util.*

class MainActivity : AppCompatActivity() {
    //                      1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26
    var alphapet = arrayOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
    var clphapet = arrayOf('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','T','Z')
    lateinit var ain: CharArray
    var aout = ""
    var co = 0
    lateinit var indec:EditText
    lateinit var inenc:EditText
    lateinit var encbut:Button
    lateinit var decbut:Button
    lateinit var inin:TextView
    lateinit var outout:TextView
    lateinit var rv: RecyclerView
    var lis =arrayListOf<ArrayList<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        encbut.setOnClickListener {
            encode()
        }
        decbut.setOnClickListener {
            decode()
        }

    }
    fun init() {
        indec=findViewById(R.id.decin)
        inenc=findViewById(R.id.encin)
        encbut=findViewById(R.id.encbut)
        decbut=findViewById(R.id.decbut)
        rv=findViewById(R.id.rvm)
        rv.adapter=RVAdapter(lis)
        rv.layoutManager= LinearLayoutManager(this)
    }
    fun encode(){
        ain = inenc.text.toString().toCharArray()
        for (element in ain) {
            var ch = element
            if (ch.isLowerCase()) {
                if (ch != ' ') {
                    for (j in alphapet.indices) {
                        if (ch == alphapet[j]) {
                            co = j
                            co = co + 13
                            if (co > alphapet.size) {
                                co = co - alphapet.size
                            }
                            break
                        }
                    }
                    aout = aout + alphapet[co].toString()
                } else {
                    aout = aout + ch.toString()
                }
            } else {
                if (ch != ' ') {
                    for (j in clphapet.indices) {
                        if (ch == clphapet[j]) {
                            co = j
                            co = co + 13
                            if (co > clphapet.size) {
                                co = co - clphapet.size
                            }
                            break
                        }
                    }
                    aout = aout + alphapet[co].toString()
                } else {
                    aout = aout + ch.toString()
                }
            }

        }
        lis.add(arrayListOf(inenc.text.toString(),aout))
        inenc.setText("")
        indec.setText("")
        aout=""
        rv.adapter?.notifyDataSetChanged()

    }

    fun decode(){
        ain = indec.text.toString().toCharArray()
        for (element in ain) {
            var ch = element
            if(ch.isLowerCase()){
                if (ch != ' ') {
                    for (j in alphapet.indices) {
                        if (ch == alphapet[j]) {
                            co = j
                            co = co - 13
                            if (co < 0) {
                                co = co + alphapet.size
                            }
                            break
                        }
                    }
                    aout = aout + alphapet[co].toString()
                } else {
                    aout = aout + ch.toString()
                }
            }else{
                if (ch != ' ') {
                    for (j in clphapet.indices) {
                        if (ch == clphapet[j]) {
                            co = j
                            co = co - 13
                            if (co < 0) {
                                co = co + clphapet.size
                            }
                            break
                        }
                    }
                    aout = aout + alphapet[co].toString()
                } else {
                    aout = aout + ch.toString()
                }
            }
        }
        lis.add(arrayListOf(indec.text.toString(),aout))
        inenc.setText("")
        indec.setText("")
        aout=""
        rv.adapter?.notifyDataSetChanged()
    }
}