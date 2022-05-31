package com.github.tagwan.actor

data class ActorPath(
    val name: String,
    val parent: ActorPath? = null
) {
    infix fun parentOf(address: ActorPath): Boolean =
        address.parent?.let { this === it } ?: false

    override fun toString(): String =
        "${parent ?: ""}/$name"
}