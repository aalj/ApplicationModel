package net.lll0.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kotlin_main.*
import net.lll0.app.util.MyUtils

class KotlinMainActivity : AppCompatActivity(), View.OnClickListener {

    var tag: String = "KotlinMainActivity";

    lateinit var te: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)
        text.setOnClickListener(this)

        edit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.e(tag, "beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e(tag, "onTextChanged")
            }
            override fun afterTextChanged(s: Editable?) {
                Log.e(tag, "afterTextChanged")
            }
        })

        clear_text.onFocusChangeListener = View.OnFocusChangeListener { _, _ ->
        }


    }


    override fun onClick(v: View?) {
        //? 表示变量是可以为空的
        //!! 表示不为空的情况下执行
        val id = v!!.id
        when (id) {
            R.id.text -> {
                Toast.makeText(this, MyUtils.getString(), Toast.LENGTH_SHORT).show()
                var info = edit.text.toString()
                text.text = info
            }
        }
    }


}
