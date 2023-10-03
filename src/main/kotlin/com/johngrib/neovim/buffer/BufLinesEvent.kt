package com.johngrib.neovim.buffer

import com.johngrib.neovim.BufferApi
import com.johngrib.neovim.annotation.MessageConverterFun
import com.johngrib.neovim.rpc.Notification

class BufLinesEvent(
    val id: Int,
    val changedTick: Int,
    val firstLine: Int,
    val lastLine: Int,
    val lineData: List<String>,
    val hasMore: Boolean,
) {
    companion object {
        @MessageConverterFun
        fun fromNotification(notification: Notification): BufLinesEvent {
            @Suppress("UNCHECKED_CAST")
            return BufLinesEvent(
                BufferApi.decodeBufId(notification),
                notification.args[1] as Int,
                notification.args[2] as Int,
                notification.args[3] as Int,
                notification.args[4] as List<String>,
                notification.args[5] as Boolean,
            )
        }
    }
}
