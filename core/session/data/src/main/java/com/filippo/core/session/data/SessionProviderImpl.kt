package com.filippo.core.session.data

import android.content.SharedPreferences
import com.filippo.core.dispatchers.domain.DispatchersFactory
import com.filippo.core.session.domain.SessionProvider
import com.filippo.core.session.domain.models.SessionId
import javax.inject.Inject
import kotlinx.coroutines.withContext

internal class SessionProviderImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val dispatchersFactory: DispatchersFactory
) : SessionProvider {

    override suspend fun saveSessionId(sessionId: SessionId) =
        withContext(dispatchersFactory.io()) {
            sharedPreferences.edit().putString(SESSION_KEY, sessionId.id).apply()
        }

    override suspend fun getSessionId(): SessionId? = withContext(dispatchersFactory.io()) {
        sharedPreferences.getString(SESSION_KEY, "")?.takeIf { it.isNotBlank() }
            ?.let { SessionId(it) }
    }

    override suspend fun isLoggedIn(): Boolean = withContext(dispatchersFactory.io()) {
        sharedPreferences.contains(SESSION_KEY)
    }

    companion object {
        private const val SESSION_KEY = "session_id"
    }
}
