package com.github.rogerli.config.jwt.auth.verifier;

import org.springframework.stereotype.Component;

/**
 * BloomFilterTokenVerifier
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
@Component
public class BloomFilterTokenVerifier implements TokenVerifier {

    /**
     * 校验是否已经在redis生成
     *
     * @param jti
     * @return
     */
    @Override
    public boolean verify(String jti) {
        return true;
    }
}
