package com.github.tagwan.actor.dispatch

import com.github.tagwan.actor.Actor
import com.github.tagwan.actor.ActorRef

interface Mailbox {
    suspend fun hasMessages(): Boolean

    suspend fun enqueue(receiver: Actor, handle: Envelope)

    suspend infix fun consumeEach(consumer: (Envelope) -> Unit)

    suspend fun cleanUp(owner: ActorRef, deadLetters: MessageQueue)
}