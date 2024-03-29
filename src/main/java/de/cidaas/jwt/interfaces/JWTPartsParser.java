package de.cidaas.jwt.interfaces;

import de.cidaas.jwt.exceptions.JWTDecodeException;

/**
 * The JWTPartsParser class defines which parts of the JWT should be converted to it's specific Object representation instance.
 */
public interface JWTPartsParser {

    /**
     * Parses the given JSON into a Payload instance.
     *
     * @param json the content of the Payload in a JSON representation.
     * @return the Payload.
     * @throws JWTDecodeException if the json doesn't have a proper JSON format.
     */
    Payload parsePayload(String json) throws JWTDecodeException;

    /**
     * Parses the given JSON into a Header instance.
     *
     * @param json the content of the Header in a JSON representation.
     * @return the Header.
     * @throws JWTDecodeException if the json doesn't have a proper JSON format.
     */
    Header parseHeader(String json) throws JWTDecodeException;
}