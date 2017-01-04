/**
 * @文件名称： OAuth2ClientDetailsService.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/23
 * @作者 ： Roger
 */
package com.github.rogerli.config.oauth;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 * @author Roger
 * @create 2016/12/23 15:12
 */
@Component
public class OAuth2ClientDetailsService implements ClientDetailsService {



    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return null;
    }
}
