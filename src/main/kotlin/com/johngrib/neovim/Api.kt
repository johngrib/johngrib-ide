package com.johngrib.neovim

import org.msgpack.core.MessagePack
import org.msgpack.jackson.dataformat.MessagePackExtensionType

class Api internal constructor(private val client: Client) {
    suspend fun callFunction(name: String, args: List<Any>): Any? {
        val rsp = client.request("nvim_call_function", listOf(name, args))
        if (rsp.error != null) {
            throw Exception(rsp.error.toString())
        }
        return rsp.result
    }

    suspend fun setClientInfo(name: String, version: Map<String, String>): Any? {
        val rsp = client.request(
            Constants.FUN_NVIM_SET_CLIENT_INFO,
            listOf(
                name,
                version,
                "remote",
                emptyMap<String, String>(),
                emptyMap<String, String>(),
            )
        )
        if (rsp.error != null) {
            throw Exception(rsp.error.toString())
        }
        return rsp.result
    }

    suspend fun callAtomic(calls: List<Pair<String, List<Any>>>): List<Any?> {
        val rsp = client.request(
            "nvim_call_atomic",
            listOf(calls.map { listOf(it.first, it.second) })
        )
        if (rsp.error != null) {
            throw Exception(rsp.error.toString())
        }
        return rsp.result as List<Any?>
    }

    suspend fun setVar(name: String, value: Any?) {
        val rsp = client.request(
            "nvim_set_var",
            listOf(name, value),
        )
        if (rsp.error != null) {
            throw Exception(rsp.error.toString())
        }
    }

    suspend fun command(cmdLine: String) {
        val rsp = client.request(
            "nvim_command",
            listOf(cmdLine),
        )
        if (rsp.error != null) {
            throw Exception(rsp.error.toString())
        }
    }

    suspend fun getApiInfo(): ApiInfo {
        val rsp = client.request(
            "nvim_get_api_info",
            emptyList(),
        )
        if (rsp.error != null) {
            throw Exception(rsp.error.toString())
        }
        return ApiInfo(rsp.result as List<*>)
    }

    suspend fun getCurrentBuf(): Int {
        val rsp = client.request("nvim_get_current_buf", emptyList())
        if (rsp.error != null) {
            throw Exception(rsp.error.toString())
        }
        return MessagePack.newDefaultUnpacker(
            (rsp.result as MessagePackExtensionType).data
        ).unpackInt()
    }
}