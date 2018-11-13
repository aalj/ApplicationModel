package net.lll0.app.util

/**
 * Created by liangjun on 2018/11/5
 *
 */
class SingletonDemo private constructor() {

    companion object {
//        var instance: SingletonDemo? = null
//            get() {
//                if (field == null) {
//                    field = SingletonDemo()
//                }
//                return field!!
//            }
//
//        @Synchronized
//        fun get(): SingletonDemo {
//            return instance!!
//        }

        val instance: SingletonDemo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonDemo()
        }

    }
}