package com.github.kiolk.githubwatch.data.networking

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.SignatureAlgorithm
import okhttp3.Interceptor
import okhttp3.Response
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64
import java.util.Date

class BaseAuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = getJwt()

        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/vnd.github+json")
            .addHeader("Authorization", "Bearer $token")
            .addHeader("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return chain.proceed(request)
    }

    private fun getJwt(): String {
        val privateKey: PrivateKey = getPrivateKeyFromPemFile()

        val alg: SignatureAlgorithm = Jwts.SIG.RS256 // or PS512, RS256, etc...
        val jwt = Jwts.builder()
            .issuer(GITHUB_CLIENT_ID)
            .expiration(Date(System.currentTimeMillis() + EXPIRATION_JWT_TIME))
            .issuedAt(Date())
            .header()
            .contentType("JWT").and()
            .signWith(privateKey, alg)
            .compact()
        return jwt
    }

    @Suppress("Indentation")
    private fun getPrivateKeyFromPemFile(): PrivateKey {
        // Read the PEM file
        var privateKeyPEM: String = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "-----END RSA PRIVATE KEY-----\n"

        // Remove the first and last lines
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN RSA PRIVATE KEY-----", "")
            .replace("-----END RSA PRIVATE KEY-----", "")
            .replace("\\s".toRegex(), "")

        // Decode the Base64-encoded string
        val encoded: ByteArray = Base64.getDecoder().decode(privateKeyPEM)

        // Generate the PrivateKey
        val keySpec = PKCS8EncodedKeySpec(encoded)
        val keyFactory = KeyFactory.getInstance("RSA") // or "EC" if using an EC key
        return keyFactory.generatePrivate(keySpec)
    }

    companion object {
        const val EXPIRATION_JWT_TIME = 600_000
        const val GITHUB_CLIENT_ID = "Iv23liviU15Sm3vwMEnG"
    }
}
