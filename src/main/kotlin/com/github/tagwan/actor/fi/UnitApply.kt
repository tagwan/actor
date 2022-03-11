package com.github.tagwan.actor.fi

@FunctionalInterface
interface UnitApply<I> {
    /**
     * The application to perform.
     *
     * @param i an instance that the application is performed on
     */
    @Throws(Exception::class)
    fun apply(i: I)
}