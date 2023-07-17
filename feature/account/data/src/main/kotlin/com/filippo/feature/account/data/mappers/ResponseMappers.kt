package com.filippo.feature.account.data.mappers

import com.filippo.domain.AccountDetails
import com.filippo.feature.account.data.remote.models.AccountResponse

internal fun AccountResponse.toDomainModel() =
    AccountDetails(
        gravatarHash = AccountDetails.GravatarHash(avatar.gravatar.hash),
        id = id,
        includeAdult = includeAdult,
        name = name,
        username = username
    )
