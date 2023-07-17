package com.filippo.core.session.domain

object NoActiveSessionError: Throwable() {
    override val message: String = "No active session found"
}
