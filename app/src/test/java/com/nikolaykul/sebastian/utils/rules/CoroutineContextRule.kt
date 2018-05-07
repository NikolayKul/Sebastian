package com.nikolaykul.sebastian.utils.rules

import com.nikolaykul.sebastian.utils.common.CoroutineContextProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * @author NikolayKul
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