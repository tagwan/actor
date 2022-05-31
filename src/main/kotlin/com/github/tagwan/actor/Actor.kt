package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope

interface Actor {

    val context: ActorContext

    suspend infix fun receive(msg: Envelope)

    suspend fun loop()

    fun preStart()

    fun postStop()
}