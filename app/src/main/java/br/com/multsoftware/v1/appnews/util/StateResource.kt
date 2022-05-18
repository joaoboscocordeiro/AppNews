package br.com.multsoftware.v1.appnews.util

/**
 * Created by João Bosco on 16/05/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
sealed class StateResource<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : StateResource<T>(data)
    class Error<T>(message: String, data: T? = null) : StateResource<T>(data, message)
    class Loading<T>(data: T? = null) : StateResource<T>(data)
}
