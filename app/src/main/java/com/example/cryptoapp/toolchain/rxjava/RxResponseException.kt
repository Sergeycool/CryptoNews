package com.example.cryptoapp.toolchain.rxjava

import retrofit2.Response

class RxResponseException(var code: Int, message: String?) : Exception(message) {

    constructor(response: Response<*>) : this(
        response.code(),
        getErrorBodyString(response)
    )

    companion object {
        private fun getErrorBodyString(response: Response<*>): String? {
            var message: String? = null
            try {
                if (response.errorBody() != null) {
                    message = response.errorBody()!!.string()
                }
            } catch (ignored: Throwable) {
            }
            return message
        }
    }

}
