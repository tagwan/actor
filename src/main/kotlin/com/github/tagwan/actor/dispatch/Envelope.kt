package com.github.tagwan.actor.dispatch

import com.github.tagwan.actor.Actor

data class Envelope(
    val message: Any,
    val sender: Actor,
)