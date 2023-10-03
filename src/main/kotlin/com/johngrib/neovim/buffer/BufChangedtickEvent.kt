package com.johngrib.neovim.buffer

import com.johngrib.neovim.BufferApi
import com.johngrib.neovim.annotation.MessageConverterFun
import com.johngrib.neovim.rpc.Notification

class BufChangedtickEvent(
    val id: Int,
    val changedTick: Int,
) {
    companion object {
        @MessageConverterFun
        fun fromNotification(notification: Notification): BufChangedtickEvent {
            val bufId = BufferApi.decodeBufId(notification)
            val tick = notification.args[1] as Int
            return BufChangedtickEvent(bufId, tick)
        }
    }
}
