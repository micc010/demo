package com.github.rogerli.config.jwt.auth.verifier;

/**
 * 更新token时，验证token是否有效
 *
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface TokenVerifier {

    /**
     *
     * @param jti
     * @return
     */
    boolean verify(String jti);
}
