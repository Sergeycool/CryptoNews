package com.example.cryptoapp.toolchain.rxjava

@Suppress("unused", "MemberVisibilityCanBePrivate")
class RxMessage<T: Any>  constructor(val status: ProcessStatus,
                                     val result: T?,
                                     val error: Throwable?) {

    companion object {
        fun <T : Any> onNext(result: T?): RxMessage<T> {
            return RxMessage(Processing, result, null)
        }

        fun <T : Any> onNext(status: Processing, result: T?): RxMessage<T> {
            return RxMessage(status, result, null)
        }

        fun <T : Any> onNextLast(result: T?): RxMessage<T>{
            return RxMessage(Complete, result, null)
        }

        fun <T : Any> onError(error: Throwable?): RxMessage<T> {
            return RxMessage(Complete, null, error)
        }

        fun <T : Any> onStart(): RxMessage<T> {
            return RxMessage(Start, null, null)
        }

        fun <T : Any> onComplete(): RxMessage<T> {
            return RxMessage(Complete, null, null)
        }
    }

    fun isStart(): Boolean {
        return status == Start
    }

    fun isProcessing(): Boolean {
        return status == Processing
    }

    fun isComplete(): Boolean {
        return status == Complete
    }

    fun hasError(): Boolean {
        return error != null
    }

    fun isCompleteWithoutError(): Boolean {
        return isComplete() && !hasError()
    }
}
