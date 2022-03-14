package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope
import com.github.tagwan.actor.dispatch.ExecutionContext
import com.github.tagwan.actor.dispatch.KMailbox
import com.github.tagwan.actor.dispatch.Mailbox
import com.github.tagwan.actor.pf.PartialFunction
import com.github.tagwan.actor.pf.ReceiveBuilder
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
abstract class AbstractActor(
    override val context: ActorContext = ActorContext(),
    mailbox: Mailbox = KMailbox(),
    name: String = "",
    nThreads: Int = 0
) : Actor {

    private var dispatcher: ExecutionContext = ExecutionContext(mailbox, createReceive(), name, nThreads)

    @ObsoleteCoroutinesApi
    override infix fun receive(msg: Envelope) {
        dispatcher sendEnvelop msg
    }

    fun receiveBuilder(): ReceiveBuilder = ReceiveBuilder.create()

    abstract fun createReceive(): AbstractActor.Receive

    override fun loop() {
        dispatcher.monitoringMailbox()
    }

    override fun preStart() {
        //pass
    }

    override fun postStop() {
        //pass
    }

    // Receive
    class Receive(
        val onMessage: PartialFunction<Any, Any>
    ) {
        fun orElse(other: Receive): Receive {
            return Receive(onMessage.orElse(other.onMessage))
        }
    }
}