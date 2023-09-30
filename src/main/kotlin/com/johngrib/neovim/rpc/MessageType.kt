package com.johngrib.neovim.rpc

import com.fasterxml.jackson.annotation.JsonValue

enum class MessageType(
    @JsonValue val value: Int,
) {
    REQUEST(0),
    RESPONSE(1),
    NOTIFICATION(2),
    ;

    companion object {
        fun valueOf(value: Int) = MessageType.values().find { it.value == value }
    }
}