package com.github.tagwan.test

import com.github.tagwan.actor.AbstractActor
import com.github.tagwan.actor.ActorSystem
import com.github.tagwan.actor.Props
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class EchoActor : AbstractActor() {

    override fun createReceive(): Receive {
        return receiveBuilder()
            .match(String::class.java, ::echo)
            .build()
    }

    private fun echo(param: String) {
        println("param-->$param")
    }

    companion object {
        fun props(): Props {
            return Props.create(EchoActor::class.java) {
                EchoActor()
            }
        }

    }
}

@ObsoleteCoroutinesApi
fun main() {
    val system = ActorSystem.build {
        name("ActorSystem")
    }

    val actor = system actorOf EchoActor.props()
    actor tell "hello world"
}