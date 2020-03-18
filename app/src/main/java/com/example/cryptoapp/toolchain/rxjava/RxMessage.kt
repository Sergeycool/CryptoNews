package com.example.cryptoapp.toolchain.rxjava

import com.example.cryptoapp.toolchain.annotation.ProcessStatus

@Suppress("unused")
class RxMessage<T: Any>  constructor(@ProcessStatus var status: Int,
                                     var result: T?,
                                     val error: Throwable?) {

    companion object {
        /**
         * [io.reactivex.Observable] just started
         * via [RxMessage.onStart] method.
         */
        const val START = 0

        /**
         * [io.reactivex.Observable] has in progress status,
         * set via [RxMessage.onNext].
         */
        const val PROCESSING = 1

        /**
         * [io.reactivex.Observable] finished the process,
         * set via [RxMessage.onComplete]
         * or [RxMessage.onError]
         * or [RxMessage.onNextLast].
         */
        const val COMPLETE = 2

        fun <T : Any> onNext(result: T?): RxMessage<T> {
            return RxMessage(PROCESSING, result, null)
        }

        fun <T : Any> onNext(@ProcessStatus status: Int, result: T?): RxMessage<T> {
            return RxMessage(status, result, null)
        }

        fun <T : Any> onNextLast(result: T?): RxMessage<T>{
            return RxMessage(COMPLETE, result, null)
        }

        fun <T : Any> onError(error: Throwable?): RxMessage<T> {
            return RxMessage(COMPLETE, null, error)
        }

        fun <T : Any> onStart(): RxMessage<T> {
            return RxMessage(START, null, null)
        }

        fun <T : Any> onComplete(): RxMessage<T> {
            return RxMessage(COMPLETE, null, null)
        }
    }

    fun isStart(): Boolean {
        return status == START
    }

    fun isProcessing(): Boolean {
        return status == PROCESSING
    }

    fun isComplete(): Boolean {
        return status == COMPLETE
    }

    fun hasError(): Boolean {
        return error != null
    }

    fun isCompleteWithoutError(): Boolean {
        return isComplete() && !hasError()
    }
}
