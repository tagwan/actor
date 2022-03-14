package com.github.tagwan.actor


class ActorContext {

    fun sender(): ActorRef {
        return ActorRef.NoSender
    }
}