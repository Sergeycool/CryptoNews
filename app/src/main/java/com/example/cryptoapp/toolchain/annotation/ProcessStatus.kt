package com.example.cryptoapp.toolchain.annotation

import androidx.annotation.IntDef
import com.example.cryptoapp.toolchain.rxjava.RxMessage.Companion.COMPLETE
import com.example.cryptoapp.toolchain.rxjava.RxMessage.Companion.PROCESSING
import com.example.cryptoapp.toolchain.rxjava.RxMessage.Companion.START

/**
 * Denotes that the annotated element must be one of the
 * defined process statuses of [com.example.cryptoapp.toolchain.rxjava.RxMessage] and that
 * its value should be one of the explicitly named constants.
 *
 * @see IntDef
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(START, PROCESSING, COMPLETE)
annotation class ProcessStatus