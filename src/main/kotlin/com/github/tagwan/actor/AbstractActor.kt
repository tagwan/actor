package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope
import com.github.tagwan.actor.dispatch.Mailbox
import com.github.tagwan.actor.pf.PartialFunction
import kotlinx.coroutines.launch

abstract class AbstractActor(
    override val context: ActorContext,
    private val mailbox: Mailbox
) : Actor {


    override infix fun receive(msg: Envelope) {
        context.scope().launch {
            mailbox.enqueue(this@AbstractActor, msg)
        }
    }

    fun createReceive() {

    }

    class Receive(
        private val onMessage: PartialFunction<Any, Any>
    ) {
        fun orElse(other: Receive): Receive {
            return Receive(onMessage.orElse(other.onMessage))
        }
    }
}