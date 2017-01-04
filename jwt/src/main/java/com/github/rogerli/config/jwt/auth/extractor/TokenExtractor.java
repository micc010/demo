package com.github.rogerli.config.jwt.auth.extractor;

/**
 * Implementations of this interface should always return raw base-64 encoded
 * representation of JWT Token.
 * 
 * @author vladimir.stankovic
 *
 * Aug 5, 2016
 */
public interface TokenExtractor {

    /**
     *
     * @param payload
     * @return
     */
    String extract(String payload);

}
