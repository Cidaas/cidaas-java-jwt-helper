package de.cidaas.jwt;

import de.cidaas.jwt.algorithms.Algorithm;
import de.cidaas.jwt.exceptions.JWTDecodeException;
import de.cidaas.jwt.impl.JWTParser;
import de.cidaas.jwt.interfaces.DecodedJWT;
import de.cidaas.jwt.interfaces.Verification;

@SuppressWarnings("WeakerAccess")
public class JWT {

    private final JWTParser parser;

    /**
     * Constructs a new instance of the JWT library. Use this if you need to decode many JWT
     * tokens on the fly and do not wish to instantiate a new parser for each invocation.
     */
    public JWT() {
        parser = new JWTParser();
    }

    /**
     * Decode a given Json Web Token.
     * <p>
     * Note that this method <b>doesn't verify the token's signature!</b> Use it only if you trust the token or you already verified it.
     *
     * @param token with jwt format as string.
     * @return a decoded JWT.
     * @throws JWTDecodeException if any part of the token contained an invalid jwt or JSON format of each of the jwt parts.
     */
    public DecodedJWT decodeJwt(String token) throws JWTDecodeException {
        return new JWTDecoder(parser, token);
    }

    /**
     * Decode a given Json Web Token.
     * <p>
     * Note that this method <b>doesn't verify the token's signature!</b> Use it only if you trust the token or you already verified it.
     *
     * @param token with jwt format as string.
     * @return a decoded JWT.
     * @throws JWTDecodeException if any part of the token contained an invalid jwt or JSON format of each of the jwt parts.
     */
    public static DecodedJWT decode(String token) throws JWTDecodeException {
        return new JWTDecoder(token);
    }

    /**
     * Returns a {@link JWTVerifier} builder with the algorithm to be used to validate token signature.
     *
     * @param algorithm that will be used to verify the token's signature.
     * @return {@link JWTVerifier} builder
     * @throws IllegalArgumentException if the provided algorithm is null.
     */
    public static Verification require(Algorithm algorithm) {
        return JWTVerifier.init(algorithm);
    }

    /**
     * Returns a Json Web Token builder used to create and sign tokens
     *
     * @return a token builder.
     */
    public static JWTCreator.Builder create() {
        return JWTCreator.init();
    }
}
