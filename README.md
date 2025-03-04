## 与原生 Akka 的对比
|特性	|本实现	|Akka Actor|

并发模型	协程（轻量级线程）	专属线程池（Dispatcher）
消息路由	直接引用目标 Actor	通过 ActorRef 代理
容错机制	未实现（需手动扩展）	Supervision 策略
性能	协程切换开销更低
线程切换开销较高