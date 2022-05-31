package com.github.tagwan.actor.dispatch

import com.github.tagwan.actor.ActorRef
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.toList

class KMailbox(
    private val capacity: Int = 1000
) : Mailbox {

    private var inputChannel = Channel<Envelope>(capacity)

    override suspend fun hasMessages(): Boolean  = inputChannel.toList().isEmpty()

    override suspend infix fun enqueue(handle: Envelope) {
        inputChannel.send(handle)
    }

    override suspend infix fun consumeEach(consumer: (Envelope) -> Unit) {
        inputChannel.consumeEach {
            consumer(it)
        }
    }

    override suspend fun cleanUp(owner: ActorRef, deadLetters: MessageQueue) {
        inputChannel = Channel(capacity)
    }

}