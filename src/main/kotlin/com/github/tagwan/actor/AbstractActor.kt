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
    val mailbox: Mailbox = KMailbox(),
    name: String = "",
    nThreads: Int = 0
) : Actor {

    private var dispatcher: ExecutionContext = ExecutionContext(mailbox, createReceive(), name, nThreads)

    private val receive = createReceive()

    @ObsoleteCoroutinesApi
    override suspend infix fun receive(msg: Envelope) {
        mailbox enqueue msg
    }

    fun receiveBuilder(): ReceiveBuilder = ReceiveBuilder.create()

    abstract fun createReceive(): AbstractActor.Receive

    override suspend fun loop() {
        mailbox.consumeEach { envelop ->
            val msg = envelop.message
            receive.onMessage.apply(msg)
        }
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