package com.mattdag.f1.kafka.common.util

import io.netty.buffer.ByteBuf
import io.netty.util.CharsetUtil

fun ByteBuf.readString(maxLength: Int): String {
    var length = this.bytesBefore(maxLength, 0.toByte())
    length = if (length == -1) maxLength else length

    val nameBytes = ByteArray(length)
    this.readBytes(nameBytes)
    val result = String(nameBytes, CharsetUtil.UTF_8)

    this.skipBytes(maxLength - length)
    return result
}
