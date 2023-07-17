package com.filippo.multimodule.utils

import groovy.lang.MissingPropertyException
import java.io.File
import java.io.FileNotFoundException
import java.util.*

object SecretsUtil {

    private const val SECRETS_FILE_NAME = "secret.properties"
    private const val KEY_SECRET_SPLIT_CHARACTER = "="
    private const val KEY_SPLIT_CHARACTER = "_"

    private fun getSecretFromFile(key: String): String {
        val camelCaseKey = key.underScoreToCamelCase()
        val secrets = HashMap<String, String>()
        val secretsFile = File(SECRETS_FILE_NAME)
        if (secretsFile.exists()) {
            secretsFile.forEachLine { line ->
                val (secretKey, secretValue) = line.split(KEY_SECRET_SPLIT_CHARACTER)
                secrets[secretKey] = secretValue
            }
        } else {
            throw FileNotFoundException(
                "File ${secretsFile.path} Not found! It should be placed inside Your root project."
            )
        }
        val secretToReturn = secrets[camelCaseKey]
        if (secretToReturn.isNullOrEmpty()) {
            throw MissingPropertyException(
                "Missing property: $key. Please, check configuration inside $SECRETS_FILE_NAME file."
            )
        }
        return secretToReturn
    }

    fun getSecret(key: String): String = "\"${getSecretFromFile(key)}\""

    @Suppress("DefaultLocale")
    private fun String.underScoreToCamelCase() =
        split(KEY_SPLIT_CHARACTER).joinToString("") {
            it.toLowerCase(Locale.ROOT).capitalize()
        }
}
