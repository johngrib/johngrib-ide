package com.johngrib.neovim.rpc

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.johngrib.neovim.annotation.MessageConverterFun

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder("type", "id", "error", "result")
class Response(
    val id: Long,
    val error: Any?,
    val result: Any?,
) : Message(MessageType.RESPONSE) {

    companion object {
        @MessageConverterFun
        fun noConvert(response: Response): Response {
            return response
        }
    }

    constructor(request: Request, error: Any?, result: Any?) : this(request.id, error, result)

    override fun toString(): String {
        return "Response(id=$id, error=$error, result=$result)"
    }
}