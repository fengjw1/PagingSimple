package com.alex.pagingsimple

import java.util.concurrent.Executors

/**
 * 线程池
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f: ()->Unit){
    IO_EXECUTOR.execute(f)
}