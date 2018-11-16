package net.lll0.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kotlin_main.*
import net.lll0.app.util.MyUtils
import net.lll0.base.http.HttpCreateClickBuild
import net.lll0.base.utils.json.GsonUtil
import net.lll0.base.utils.log.MyLog
import net.lll0.utils.time.PickerUtils
import net.lll0.widget.convenientbanner.ConvenientBanner
import net.lll0.widget.convenientbanner.holder.CBViewHolderCreator
import net.lll0.widget.convenientbanner.holder.Holder
import net.lll0.widget.convenientbanner.listener.OnItemClickListener
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.*

class KotlinMainActivity : AppCompatActivity(), View.OnClickListener, OnItemClickListener {


    var tag: String = "KotlinMainActivity"
    var count: Int = 0
    lateinit var banner: ConvenientBanner<String>

    lateinit var te: TextView
    var s: ArrayList<String> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)
        text.setOnClickListener(this)
        banner = findViewById(R.id.banner);
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

        sms.setOnClickListener(this)
        restart.setOnClickListener(this)
        costomView.setOnClickListener(this)
        picker_time.setOnClickListener(this)
        picker_opsition.setOnClickListener(this)
        MyLog.e("通过xml文字大小     " + restart.textSize)
        MyLog.e("通过已定义 xml文字大小     " + costomView.textSize)
        myHttp()
    }


    fun initBanner() {

        banner.setPages(object : CBViewHolderCreator {
            override fun getLayoutId(): Int {
                return R.layout.item_localimage
            }

            override fun createHolder(itemView: View?): Holder<*> {
                return NetWorkImageHolderView(this@KotlinMainActivity, itemView)
            }
        }, s)

        banner.setCanLoop(true)
        banner.setPageIndicator(intArrayOf(R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused))
        banner.setOnItemClickListener(this)
    }

    override fun onItemClick(position: Int) {
        MyLog.e("我在干嘛---->  $position")
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
            R.id.sms -> {
                Log.e("KotlinMainActivity", "开始倒计时")
                sms.startCountDown()

                MyLog.e("日志测试")
            }
            R.id.restart -> {
                Log.e("KotlinMainActivity", "重置倒计时")
                sms.restart()
                costomView.setCanSubmit()
                myHttp()
            }
            R.id.costomView -> {
                MyLog.e("点击测试")
                MyLog.e("点击执行之前----> ${costomView.isEnabled} ")

                if (count % 2 == 0) {
                    costomView.setNotSubmit()
                } else {
                    costomView.setCanSubmit()
                }
                MyLog.e("点击执行之后----> ${costomView.isEnabled} ")

                count++

            }
            R.id.picker_opsition -> {
                //条件选择器
                var first: ArrayList<String> = ArrayList()
                first.add("1")
                first.add("2")
                first.add("3")
                first.add("4")
                first.add("5")
                first.add("6")
                first.add("7")
                var s: ArrayList<String> = ArrayList()
                s.add("1")
                s.add("2")
                s.add("3")
                s.add("4")
                s.add("5")
                s.add("6")
                s.add("7")
                var t: ArrayList<String> = ArrayList()
                t.add("1")
                t.add("2")
                t.add("3")
                t.add("4")
                t.add("5")
                t.add("6")
                t.add("7")

                PickerUtils().initNoLinkOptionsPicker("条件选择器", this, first, s, t, false) { options1, options2, options3 ->
                    MyLog.e("选中第一个--> $options1")
                    MyLog.e("选中第二个--> $options2")
                    MyLog.e("选中第三个--> $options3")


                }
            }
            R.id.picker_time -> {
                //时间选择器
                PickerUtils().initCustomTimePicker("选择时间", Calendar.getInstance(), this)
            }
        }
    }


    fun myHttp() {
        val build = HttpCreateClickBuild.instance().build()
        val build1 = Request.Builder().url("https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1").build()
        build.newCall(build1).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val string = response.body()!!.string()
                MyLog.e("请求结果--->  $string")
                val gsonToList = GsonUtil.GsonToBean(string, ItemBean::class.java)
                println(gsonToList)
                this@KotlinMainActivity.runOnUiThread {
                    setDatas(gsonToList)
                }
            }
        })
    }


    fun setDatas(itemBean: ItemBean) {
        itemBean.results.forEach {
            s.add(it.url)
        }
        initBanner()
    }


    override fun onResume() {
        super.onResume()
        banner.startTurning(1000L)
    }

    override fun onPause() {
        super.onPause()
        banner.stopTurning()
    }
}
