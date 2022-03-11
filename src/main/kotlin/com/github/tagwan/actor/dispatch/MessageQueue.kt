package com.github.tagwan.actor.dispatch

import com.github.tagwan.actor.ActorRef

interface MessageQueue {

    val numberOfMessages: Int

    val hasMessages: Boolean

    fun enqueue(receiver: ActorRef, handle: Envelope)

    fun dequeue(): Envelope

    fun cleanUp(owner: ActorRef, deadLetters: MessageQueue)
}