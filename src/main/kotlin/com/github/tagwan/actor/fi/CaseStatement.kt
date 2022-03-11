package com.github.tagwan.actor.fi

import com.github.tagwan.actor.pf.PartialFunction
import java.util.function.Consumer
import java.util.function.Predicate

class UnitCaseStatement<F, P>(
    private val predicate: Predicate<F>,
    private val apply: Consumer<P>
): PartialFunction<F, Unit>() {

    override fun test(t: F): Boolean {
        return predicate.test(t)
    }

    @SuppressWarnings("Unchecked")
    override fun applyIfDefined(x: F) {
        this.apply.accept(x as P)
    }
}

class CaseStatement<F, T> {
   companion object {
       val empty = PartialFunction.EMPTY
   }
}