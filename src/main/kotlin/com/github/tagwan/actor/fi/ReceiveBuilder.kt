package com.github.tagwan.actor.fi

import java.util.function.Predicate

class ReceiveBuilder {


    fun <P> match(type: Class<P>, action: UnitApply<P>): ReceiveBuilder {
        return this.matchUnchecked(type, action)
    }

    fun matchUnchecked(type: Class<*>, action: UnitApply<*>): ReceiveBuilder {
        val predicate = Predicate<Any> { o -> type.isInstance(o) }
        return this
    }

}