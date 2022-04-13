package com.example.flowpractice

import java.io.InputStream
import java.io.OutputStream

/**
 * InputStream的扩展函数
 */
inline fun InputStream.copyTo(
    output: OutputStream,
    buffSize: Int = DEFAULT_BUFFER_SIZE, progress: (l: Long) -> Unit
): Long {

    var bytesCopied = 0L
    var buff = ByteArray(buffSize)
    var readBytes = read(buff)
    while (readBytes > 0) {
        output.write(buff, 0, readBytes)
        bytesCopied += readBytes
        readBytes = read(buff)
        progress(bytesCopied);
    }

    return bytesCopied;
}