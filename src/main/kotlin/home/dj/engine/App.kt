package home.dj.engine

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
    build()
        .args(*args)
        .eagerInitSingletons(true)
        .packages("home.dj")
        .start()
}