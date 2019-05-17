package net.lll0.app

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_rxbus.*
import net.lll0.base.bus.RxBus
import net.lll0.widget.PieData


class RxbusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxbus)
        val currentThread = Thread.currentThread()
        currentThread.name
        Log.e("RxbusActivity", currentThread.name)
        observer()
        val datas = arrayListOf<PieData>()
        val pieData = PieData("sloop", 60f)
        val pieData2 = PieData("sloop", 30f)
        datas.add(pieData)
        datas.add(pieData2)
        pieView.setData(datas)
    }

    @SuppressLint("CheckResult")
    private fun observer() {
        RxBus.get().toObservable(String::class.java).subscribe { textTv.text = it }

    }

    fun click(v: View) {
        Thread(Runnable {

            val currentThread = Thread.currentThread()
            currentThread.name
            Thread.sleep(60000)
            RxBus.get().post("ajhsdlkjahsblkdjh ")
            Log.e("RxbusActivity", currentThread.name)
        }).start()

    }

}
