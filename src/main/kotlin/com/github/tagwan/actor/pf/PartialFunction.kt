package com.github.tagwan.actor.pf

import java.util.function.Function
import java.util.function.Predicate

abstract class PartialFunction<X, Y> : Predicate<X>, Function<X, Y> {

    open fun isDefinedAt(x: X): Boolean {
        return test(x)
    }

    override fun apply(x: X): Y {
        return if (isDefinedAt(x)) {
            applyIfDefined(x)
        } else {
            throw IllegalArgumentException("Match error on $x")
        }
    }

    abstract fun applyIfDefined(x: X): Y

    fun orElse(fallback: PartialFunction<X, Y>): PartialFunction<X, Y> {
        val outer: PartialFunction<X, Y> = this
        return object : PartialFunction<X, Y>() {
            override fun test(x: X): Boolean {
                return outer.test(x) || fallback.test(x)
            }

            override fun applyIfDefined(x: X): Y {
                return if (outer.isDefinedAt(x)) {
                    outer.applyIfDefined(x)
                } else {
                    fallback.apply(x)
                }
            }

            override fun apply(x: X): Y {
                return applyIfDefined(x)
            }
        }
    }

    companion object EMPTY : PartialFunction<Any, Any>() {

        override fun applyIfDefined(x: Any): Nothing {
            throw java.lang.IllegalArgumentException()
        }

        override fun test(t: Any): Boolean {
            return false
        }

    }
}

//fun main(args: Array<String>) {
//    val f: PartialFunction<Int, String> = object : PartialFunction<Int, String>() {
//        override fun test(i: Int): Boolean {
//            return i % 2 == 0
//        }
//
//        override fun applyIfDefined(i: Int): String {
//            return "$i is even"
//        }
//    }
//    val g: PartialFunction<Int, String> = object : PartialFunction<Int, String>() {
//        override fun test(i: Int): Boolean {
//            return i % 2 == 1
//        }
//
//        override fun applyIfDefined(i: Int): String {
//            return "$i is odd"
//        }
//    }
//
//    println(f.apply(42))
//    try {
//        println(f.apply(43))
//    } catch (e: Exception) {
//        println(e)
//    }
//
//    val h = f.orElse(g)
//    println(h.apply(100))
//    println(h.apply(101))
//}