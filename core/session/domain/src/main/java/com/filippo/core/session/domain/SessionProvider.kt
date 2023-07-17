package com.filippo.core.session.domain

import com.filippo.core.session.domain.models.SessionId

interface SessionProvider {
    suspend fun isLoggedIn(): Boolean
    suspend fun getSessionId(): SessionId?
    suspend fun saveSessionId(sessionId: SessionId)
}
