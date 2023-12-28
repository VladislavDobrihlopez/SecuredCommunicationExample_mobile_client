package com.dobrihlopez.securedcommunicationexample.domain

interface KeysStorage {
    fun saveKeys(publicKey: PublicKey, privateKey: PrivateKey): Boolean
    fun getKeys(): Pair<PublicKey, PrivateKey>?
    fun areKeysReady(): Boolean
}
