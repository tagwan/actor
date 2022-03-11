package com.github.tagwan.actor

import java.lang.reflect.Modifier

class Props private constructor(
    val actor: ActorRef
){

    companion object {

        private infix fun validate(clazz: Class<*>) {
            if (Modifier.isAbstract(clazz.modifiers))
                throw IllegalArgumentException("Actor class [${clazz.name}] must not be abstract")
        }

        fun create(clazz: Class<*>, supplier: () -> ActorRef): Props {
            this validate clazz
            return Props(supplier())
        }
    }
}