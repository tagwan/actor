package com.github.tagwan.actor

import kotlinx.coroutines.CoroutineScope

interface ActorContext {

    fun scope(): CoroutineScope

    fun mailbox()
}