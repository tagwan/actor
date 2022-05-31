package com.github.tagwan.actor

import com.github.tagwan.actor.dispatch.Envelope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicReference

object SystemGuardian {

    val job = Job()
    private val actors = HashMap<String, Actor>()
    private val actors2: AtomicReference<Map<ActorPath, Actor>> = AtomicReference(mapOf())

    fun publish(path: String, msg: Envelope) {
        val actor = actors[path] ?: return
//        actor receive msg

        val scope = CoroutineScope(job)
        scope.launch {
            actor receive msg
        }
    }

    fun start(consumer: (Envelope) -> Unit) {
        for ((_, actor) in actors) {
            val scope = CoroutineScope(job)
            scope.launch {
                //actor receive msg
            }
        }
//        val scope = CoroutineScope(job)
//        scope.launch {
//            actor receive msg
//        }
//        // actors.forEach { it.stop() }
    }

    fun stop() {

    }
}