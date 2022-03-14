package com.github.tagwan.actor.dispatch

import com.github.tagwan.actor.ActorRef

data class Envelope(
    val message: Any,
    val sender: ActorRef,
)