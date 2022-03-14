package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope
import kotlinx.coroutines.Job

object SystemGuardian {

    val job = Job()
    private val actors = HashMap<String, Actor>()

    fun publish(path: String, msg: Envelope) {
        val actor = actors[path] ?: return
        actor receive msg
    }

    fun start(consumer: (Envelope) -> Unit) {

        // actors.forEach { it.stop() }
    }

    fun stop() {

    }
}