package com.johngrib.neovim.rpc

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.johngrib.neovim.annotation.MessageConverterFun

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder("type", "name", "args")
class Notification(
    val name: String,
    val args: List<Any?>,
) : Message(MessageType.NOTIFICATION) {
    companion object {
        @MessageConverterFun
        fun noConvert(notification: Notification): Notification {
            return notification
        }
    }

    override fun toString(): String {
        return "Notification(name='$name', args=$args)"
    }
}