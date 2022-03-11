package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope
import com.github.tagwan.actor.dispatch.Mailbox
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

    class Receive {

    }
}