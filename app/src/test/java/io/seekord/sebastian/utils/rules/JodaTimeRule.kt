package io.seekord.sebastian.utils.rules

import org.joda.time.DateTimeZone
import org.joda.time.tz.Provider
import org.joda.time.tz.UTCProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * @author NikolayKul
 */
class JodaTimeRule(
        private val provider: Provider = UTCProvider()
) : TestRule {
    override fun apply(base: Statement, description: Description) = object : Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            DateTimeZone.setProvider(provider)
            base.evaluate()
        }
    }
}