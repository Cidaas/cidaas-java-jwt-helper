package de.cidaas.jwt;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import de.cidaas.jwt.exceptions.JWTDecodeException;
import de.cidaas.jwt.impl.JWTParser;
import de.cidaas.jwt.interfaces.Claim;
import de.cidaas.jwt.interfaces.DecodedJWT;
import de.cidaas.jwt.interfaces.Header;
import de.cidaas.jwt.interfaces.Payload;

/**
 * The JWTDecoder class holds the decode method to parse a given JWT token into it's JWT representation.
 */
@SuppressWarnings("WeakerAccess")
final class JWTDecoder implements DecodedJWT {

    private final String[] parts;
    private final Header header;
    private final Payload payload;

    JWTDecoder(String jwt) throws JWTDecodeException {
        this(new JWTParser(), jwt);
    }

    JWTDecoder(JWTParser converter, String jwt) throws JWTDecodeException {
        parts = TokenUtils.splitToken(jwt);
        String headerJson;
        String payloadJson;
        try {
            headerJson = StringUtils.newStringUtf8(Base64.decodeBase64(parts[0]));
            payloadJson = StringUtils.newStringUtf8(Base64.decodeBase64(parts[1]));
        } catch (NullPointerException e) {
            throw new JWTDecodeException("The UTF-8 Charset isn't initialized.", e);
        } catch (IllegalArgumentException e) {
        	throw new JWTDecodeException("The token has invalid value.", e);
        }
        header = converter.parseHeader(headerJson);
        payload = converter.parsePayload(payloadJson);
    }

    @Override
    public String getAlgorithm() {
        return header.getAlgorithm();
    }

    @Override
    public String getType() {
        return header.getType();
    }

    @Override
    public String getContentType() {
        return header.getContentType();
    }

    @Override
    public String getKeyId() {
        return header.getKeyId();
    }

    @Override
    public Claim getHeaderClaim(String name) {
        return header.getHeaderClaim(name);
    }

    @Override
    public String getIssuer() {
        return payload.getIssuer();
    }

    @Override
    public String getSubject() {
        return payload.getSubject();
    }

    @Override
    public List<String> getAudience() {
        return payload.getAudience();
    }

    @Override
    public Date getExpiresAt() {
        return payload.getExpiresAt();
    }

    @Override
    public Date getNotBefore() {
        return payload.getNotBefore();
    }

    @Override
    public Date getIssuedAt() {
        return payload.getIssuedAt();
    }

    @Override
    public String getId() {
        return payload.getId();
    }

    @Override
    public Claim getClaim(String name) {
        return payload.getClaim(name);
    }

    @Override
    public Map<String, Claim> getClaims() {
        return payload.getClaims();
    }

    @Override
    public String getHeader() {
        return parts[0];
    }

    @Override
    public String getPayload() {
        return parts[1];
    }

    @Override
    public String getSignature() {
        return parts[2];
    }

    @Override
    public String getTokenAsString() {
        return String.format("%s.%s.%s", parts[0], parts[1], parts[2]);
    }
}
