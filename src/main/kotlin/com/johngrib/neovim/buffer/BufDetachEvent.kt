package com.johngrib.neovim.buffer

import com.johngrib.neovim.BufferApi
import com.johngrib.neovim.annotation.MessageConverterFun
import com.johngrib.neovim.rpc.Notification

class BufDetachEvent(val id: Int) {
    companion object {
        @MessageConverterFun
        fun fromNotification(notification: Notification): BufDetachEvent {
            val bufId = BufferApi.decodeBufId(notification)
            return BufDetachEvent(bufId)
        }
    }
}
