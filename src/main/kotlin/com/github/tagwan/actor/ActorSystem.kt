package com.github.tagwan.actor

/**
 * Actor system
 *
 * @author jdg
 */
class ActorSystem private constructor(
    private val builder: Builder
) {

    infix fun actorOf(props: Props) {

    }

    fun actorOf(props: Props, actorName: String) {

    }

    fun terminate() {

    }


    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        // 系统名字
        lateinit var name: String
        // 起使时间
        var startTime: Long = 0L

        fun build(): ActorSystem {
            startTime = System.currentTimeMillis()
            return ActorSystem(this)
        }
    }
}