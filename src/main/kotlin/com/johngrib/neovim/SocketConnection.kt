package com.johngrib.neovim

import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class SocketConnection(private val socket: Socket) : NeovimConnection {
    override val inputStream: InputStream
        get() = socket.getInputStream()
    override val outputStream: OutputStream
        get() = socket.getOutputStream()

    override fun close() {
        outputStream.close()
        inputStream.close()
        socket.close()
    }
}