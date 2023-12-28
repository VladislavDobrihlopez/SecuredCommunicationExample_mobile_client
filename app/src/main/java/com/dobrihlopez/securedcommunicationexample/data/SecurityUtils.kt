package com.dobrihlopez.securedcommunicationexample.data

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.nio.charset.StandardCharsets
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.KeyStore.PrivateKeyEntry
import java.util.Base64
import javax.crypto.Cipher

object KeyPairManager {
    private const val NAME = "AndroidKeyStore"
    private const val KEY_ALIAS = "keys"
    private const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"

    private val keyStore = KeyStore.getInstance(NAME).apply {
        load(null)
    }

    fun generateKeyPair() {
        if (keyStore != null && keyStore.containsAlias(KEY_ALIAS)) return
        val generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, NAME)
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .setDigests(KeyProperties.DIGEST_SHA256)
            .build()
        generator.initialize(keyGenParameterSpec)
        generator.generateKeyPair()
    }

    private val privateKeyEntry: PrivateKeyEntry?
        get() = keyStore.getEntry(KEY_ALIAS, null) as? PrivateKeyEntry

    fun getPublicKey(): String {
        val encodedPublicKey = privateKeyEntry?.certificate?.publicKey?.encoded
        return Base64.getEncoder().encodeToString(encodedPublicKey)
    }

    fun decrypt(encryptedData: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, privateKeyEntry?.privateKey)
        val decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData))
        return String(decryptedData, StandardCharsets.UTF_8)
    }
}