package com.github.rogerli.security.shiro.session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author roger.li
 */
@Component
public class SessionListener extends SessionListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void onStart(Session session) {
        LOGGER.debug(" ===>> Session: [{}] From: [{}] CREATE ", session.getId(), session.getHost());

        // TODO 日志
    }

    @Override
    public void onExpiration(Session session) {
        LOGGER.debug(" <<=== Session: [{}] From: [{}] EXPIRE ", session.getId(), session.getHost());

        // TODO 日志
    }

    @Override
    public void onStop(Session session) {
        LOGGER.debug(" <<=== Session: [{}] From: [{}] STOP ", session.getId(), session.getHost());

        // TODO 日志
    }
}
