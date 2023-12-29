package com.dobrihlopez.securedcommunicationexample.domain

typealias KeysBunch = Pair<PublicKey, PrivateKey>

@JvmInline
value class PublicKey(val key: String)

@JvmInline
value class PrivateKey(val key: String)