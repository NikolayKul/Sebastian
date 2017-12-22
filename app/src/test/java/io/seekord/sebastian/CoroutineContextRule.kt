package io.seekord.sebastian

import io.seekord.sebastian.utils.coroutine.CoroutineContextProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by nikolay
 */

class CoroutineContextRule : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                CoroutineContextProvider.mockContexts()
                base?.evaluate()
            }
        }
    }

}