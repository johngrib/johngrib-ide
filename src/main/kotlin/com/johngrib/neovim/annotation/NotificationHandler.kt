package com.johngrib.neovim.annotation

@Target(AnnotationTarget.FUNCTION)
annotation class NotificationHandler(val name: String)

