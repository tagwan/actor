package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope

interface Actor {

    val context: ActorContext

    val process: Actor.(Envelope) -> Unit

    infix fun receive(msg: Envelope)

}