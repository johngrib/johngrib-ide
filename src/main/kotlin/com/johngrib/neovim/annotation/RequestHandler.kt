package com.johngrib.neovim.annotation

@Target(AnnotationTarget.FUNCTION)
annotation class RequestHandler(val name: String)
