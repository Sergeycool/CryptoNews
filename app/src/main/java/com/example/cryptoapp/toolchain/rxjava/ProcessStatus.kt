package com.example.cryptoapp.toolchain.rxjava

sealed class ProcessStatus

/**
 * [io.reactivex.Observable] just started
 * via [RxMessage.onStart] method.
 */
object Start: ProcessStatus()

/**
 * [io.reactivex.Observable] has in progress status,
 * set via [RxMessage.onNext].
 */
object Processing: ProcessStatus()

/**
 * [io.reactivex.Observable] finished the process,
 * set via [RxMessage.onComplete]
 * or [RxMessage.onError]
 * or [RxMessage.onNextLast].
 */
object Complete: ProcessStatus()
