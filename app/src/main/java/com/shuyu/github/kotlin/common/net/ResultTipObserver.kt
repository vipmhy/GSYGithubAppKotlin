package com.shuyu.github.kotlin.common.net

import android.accounts.NetworkErrorException
import android.content.Context
import com.shuyu.github.kotlin.R
import org.jetbrains.anko.toast
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * 网络请求返回处理
 */
abstract class ResultTipObserver<T>(private val context: Context) : ResultObserver<T>() {

    override fun onInnerCodeError(code: Int, message: String) {
        super.onInnerCodeError(code, message)
        when (code) {
            401 -> context.toast(R.string.error_401)
            402 -> context.toast(R.string.error_402)
            403 -> context.toast(R.string.error_403)
            404 -> context.toast(R.string.error_404)
            else -> context.toast(code.toString() + " : " + message)
        }
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        try {
            if (e is ConnectException
                    || e is TimeoutException
                    || e is NetworkErrorException
                    || e is UnknownHostException) {
                context.toast(R.string.netError)
            } else {
                context.toast(e.message ?: context.getString(R.string.unKnowError))
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
    }


}
