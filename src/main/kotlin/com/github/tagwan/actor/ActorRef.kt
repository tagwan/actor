package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope
import java.io.Serializable

open class ActorRef(
    private val path: String = ""
) : Serializable {

    infix fun tell(msg: Any) {
        this.tell(msg, ActorRef.NoSender)
    }

    fun tell(msg: Any, sender: ActorRef) {
        SystemGuardian.publish(path, Envelope(msg, sender))
    }

    /**
     * Forwards the message and passes the original sender actor as the sender.
     */
    fun forward(message: Any, context: ActorContext) = this.tell(message, context.sender())

    companion object NoSender : ActorRef()
}