package com.mattdag.f1.listening.exporter.util

import io.netty.buffer.ByteBuf

fun ByteBuf.toByteArraySafe(): ByteArray {
    if (this.hasArray()) {
        return this.array()
    }

    val bytes = ByteArray(this.readableBytes())
    this.getBytes(this.readerIndex(), bytes)

    return bytes
}
