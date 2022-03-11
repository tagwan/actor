package com.github.tagwan.actor.fi

import java.util.function.Predicate

class UnitCaseStatement<F, P>(
    val predicate: Predicate<F>,
    val apply: UnitApply<P>
) {

}