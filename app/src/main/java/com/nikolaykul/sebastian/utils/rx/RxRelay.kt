package com.nikolaykul.sebastian.utils.rx

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject


/**
 * A relay that can consume and propagate events
 */
class RxRelay<T>(
        private val subject: Subject<T> = BehaviorSubject.create()
) {
    fun observe(strategy: BackpressureStrategy = BackpressureStrategy.LATEST): Flowable<T> =
            subject.toFlowable(strategy)

    fun push(value: T) {
        subject.onNext(value)
    }
}