package com.github.tagwan.actor.dispatch

import com.github.tagwan.actor.Actor
import com.github.tagwan.actor.ActorRef
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.toList

class KMailbox(
    private val capacity: Int = 1000
) : Mailbox {

    private val inputChannel = Channel<Envelope>(capacity)

    override suspend fun hasMessages(): Boolean  = inputChannel.toList().isEmpty()

    override suspend fun enqueue(receiver: Actor, handle: Envelope) {
        //pass
    }

    override suspend infix fun consumeEach(consumer: (Envelope) -> Unit) {
        inputChannel.consumeEach {
            consumer(it)
        }
    }

    override suspend fun cleanUp(owner: ActorRef, deadLetters: MessageQueue) {
        //pass
    }

}