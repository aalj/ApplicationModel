package net.lll0.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dot_group.*
import net.lll0.base.utils.log.MyLog

class DotGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dot_group)

//        val childCount = groupView.childCount
//        MyLog.e("Group 内容 $childCount")
//        for (viewCount: Int in 1..childCount) {
//            println(viewCount)
//        }

    }
}

