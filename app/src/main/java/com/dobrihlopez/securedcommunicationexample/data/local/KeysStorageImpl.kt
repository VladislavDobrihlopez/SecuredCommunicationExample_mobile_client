package com.dobrihlopez.securedcommunicationexample.data.local

import android.annotation.SuppressLint
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.dobrihlopez.securedcommunicationexample.domain.KeysStorage
import com.dobrihlopez.securedcommunicationexample.domain.PrivateKey
import com.dobrihlopez.securedcommunicationexample.domain.PublicKey
import dagger.hilt.android.qualifiers.ApplicationContext

class KeysStorageImpl(
    @ApplicationContext val context: Context
) : KeysStorage {
    private val preferences = kotlin.run {
        val masterKey = MasterKey
            .Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_PREFERENCES_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    @SuppressLint("ApplySharedPref")
    override fun saveKeys(publicKey: PublicKey, privateKey: PrivateKey): Boolean {
        return preferences.edit().apply {
            putString(PUBLIC_STORAGE_KEY, publicKey.key)
            putString(PRIVATE_STORAGE_KEY, privateKey.key)
        }.commit()
    }

    override fun getKeys(): Pair<PublicKey, PrivateKey>? {
        return kotlin.run {
            val retrievedPublicKey = preferences.getString(PUBLIC_STORAGE_KEY, null)
            val retrievedPrivateKey = preferences.getString(PRIVATE_STORAGE_KEY, null)

            if (retrievedPrivateKey != null && retrievedPublicKey != null) {
                Pair(PublicKey(key = retrievedPublicKey), PrivateKey(key = retrievedPrivateKey))
            } else {
                null
            }
        }
    }

    override fun areKeysReady(): Boolean {
        return preferences.contains(PUBLIC_STORAGE_KEY) && preferences.contains(PRIVATE_STORAGE_KEY)
    }

    companion object {
        private const val ENCRYPTED_PREFERENCES_NAME = "ENCRYPTED_KEYS_STORAGE"
        private const val PUBLIC_STORAGE_KEY = "public_key"
        private const val PRIVATE_STORAGE_KEY = "private_key"
    }
}