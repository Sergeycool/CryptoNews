package com.example.cryptoapp.toolchain.rxjava

@Suppress("unused", "MemberVisibilityCanBePrivate")
class RxMessage<T: Any>  constructor(val status: ProcessStatus,
                                     val result: T?,
                                     val error: Throwable?) {
    companion object {
        fun <T : Any> onNext(result: T?): RxMessage<T>
                = RxMessage(Processing, result, null)

        fun <T : Any> onNext(status: Processing, result: T?): RxMessage<T>
                = RxMessage(status, result, null)

        fun <T : Any> onNextLast(result: T?): RxMessage<T>
                = RxMessage(Complete, result, null)

        fun <T : Any> onError(error: Throwable?): RxMessage<T>
            = RxMessage(Complete, null, error)

        fun <T : Any> onStart(): RxMessage<T>
            = RxMessage(Start, null, null)

        fun <T : Any> onComplete(): RxMessage<T>
            = RxMessage(Complete, null, null)
    }

    fun isStart(): Boolean = status === Start

    fun isProcessing(): Boolean = status === Processing

    fun isComplete(): Boolean = status === Complete

    fun hasError(): Boolean = error != null

    fun isCompleteWithoutError(): Boolean = isComplete() && !hasError()

}
