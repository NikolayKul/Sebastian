package com.nikolaykul.sebastian.utils.rules

import com.nikolaykul.sebastian.utils.common.CoroutineDispatchersProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * A [TestRule] that mocks all [CoroutineDispatchersProvider]'s dispatchers
 */
class CoroutineContextRule : TestRule {
    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                CoroutineDispatchersProvider.mockDispatchers()
                base.evaluate()
            }
        }
    }
}