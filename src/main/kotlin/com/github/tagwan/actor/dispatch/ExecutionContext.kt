package com.github.tagwan.actor.dispatch

import com.github.tagwan.actor.AbstractActor
import com.github.tagwan.actor.SystemGuardian
import kotlinx.coroutines.*

@ObsoleteCoroutinesApi
class ExecutionContext(
    private val mailbox: Mailbox,
    private val receive: AbstractActor.Receive,
    name: String = "ActorPool",
    nThreads: Int = 2
) {
    private val dispatcher: ExecutorCoroutineDispatcher = newFixedThreadPoolContext(nThreads, name)

    fun execute(run: () -> Unit) {
        val scope = CoroutineScope(SystemGuardian.job)
        scope.launch(dispatcher) {
            run()
        }
    }

    infix fun sendEnvelop(msg: Envelope) {
        val scope = CoroutineScope(SystemGuardian.job)
        scope.launch(dispatcher) {
            mailbox.enqueue(msg)
        }
    }

    fun monitoringMailbox() {
        val scope = CoroutineScope(SystemGuardian.job)
        scope.launch(dispatcher) {
            mailbox.consumeEach { envelop ->
                val msg = envelop.message
                receive.onMessage.apply(msg)
            }
        }
    }
}