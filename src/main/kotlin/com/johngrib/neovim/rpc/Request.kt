package com.johngrib.neovim.rpc

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.johngrib.neovim.annotation.MessageConverterFun
import java.util.concurrent.atomic.AtomicLong

private val requestId = AtomicLong(0)

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder("type", "id", "method", "args")
class Request(
    val id: Long,
    val method: String,
    val args: List<Any?>,
) : Message(MessageType.REQUEST) {

    companion object {
        @MessageConverterFun
        fun noConvert(request: Request): Request {
            return request
        }
    }

    constructor(method: String, args: List<Any?>) : this(requestId.getAndIncrement(), method, args)

    override fun toString(): String {
        return "Request(id=$id, method='$method', args=$args)"
    }
}