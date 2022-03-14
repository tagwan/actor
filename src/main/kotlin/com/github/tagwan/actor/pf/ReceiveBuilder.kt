package com.github.tagwan.actor.pf

import com.github.tagwan.actor.AbstractActor
import com.github.tagwan.actor.fi.UnitCaseStatement
import java.util.function.Consumer
import java.util.function.Predicate

class ReceiveBuilder {

    private var statements: PartialFunction<Any, Any>? = null

    protected infix fun addStatement(statement: PartialFunction<Any, Any>) {
        if (statements == null) statements = statement else statements = statements?.orElse(statement)
    }


    fun <P> match(type: Class<P>, action: Consumer<P>): ReceiveBuilder {
        return this.matchUnchecked(type, action)
    }

    fun <P> matchUnchecked(type: Class<P>, action: Consumer<P>): ReceiveBuilder {
        val predicate = Predicate<Any> { o -> type.isInstance(o) }
        //this addStatement UnitCaseStatement(predicate, action)

        this addStatement object : PartialFunction<Any, Unit>() {
            override fun applyIfDefined(x: Any) {
                action.accept(x as P)
            }

            override fun test(t: Any): Boolean {
                return predicate.test(x)
            }
        }

        return this
    }

    fun matchAny(action: Consumer<Any>) {
        this addStatement object : PartialFunction<Any, Unit>() {
            override fun applyIfDefined(x: Any) {
                action.accept(x)
            }

            override fun test(t: Any): Boolean {
                return true
            }
        }
        return this
    }

    fun build(): AbstractActor.Receive {
        return AbstractActor.Receive(statements?.orElse(PartialFunction.EMPTY) ?: PartialFunction.EMPTY)
    }

    companion object {
        fun create(): ReceiveBuilder {
            return ReceiveBuilder()
        }
    }
}