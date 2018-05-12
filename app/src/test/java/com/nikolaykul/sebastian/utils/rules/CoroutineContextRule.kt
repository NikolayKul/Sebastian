package com.nikolaykul.sebastian.utils.rules

import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * A [TestRule] that mocks all [CoroutineContextProvider]'s contexts with the [Unconfined] one
 *
 * @see [CoroutineContextProvider.mockContexts]
 */
class CoroutineContextRule : TestRule {
    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                CoroutineContextProvider.mockContexts()
                base.evaluate()
            }
        }
    }
}