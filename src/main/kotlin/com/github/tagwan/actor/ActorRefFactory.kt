package com.github.tagwan.actor

abstract class ActorRefFactory {

    infix fun actorOf(props: Props): ActorRef {
        return ActorRef.NoSender
    }
}