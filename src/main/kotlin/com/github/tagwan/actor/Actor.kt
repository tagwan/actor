package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope

interface Actor {

    val context: ActorContext

    infix fun receive(msg: Envelope)

    fun loop()

    fun preStart()

    fun postStop()
}