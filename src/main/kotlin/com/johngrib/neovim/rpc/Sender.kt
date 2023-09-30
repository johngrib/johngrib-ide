package com.johngrib.neovim.rpc

import com.intellij.openapi.diagnostic.Logger
import com.johngrib.neovim.NeovimConnection

private val log = Logger.getInstance(Sender::class.java)

class Sender(private val connection: NeovimConnection) {

    fun send(msg: Message) {
        MsgPackMapper.writeValue(connection.outputStream, msg)
        log.debug("Connection['$connection'] Sent message: $msg")
    }
}