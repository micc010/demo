package com.github.rogerli.security.session.eis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author roger.li
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static final String ACTIVE_SESSION = "atv:session:";
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionDAO.class);
    private RedisTemplate<Serializable, Session> redisTemplate;
    private ValueOperations<Serializable, Session> sessionOperations;

    /**
     * @param redisTemplate
     */
    public RedisSessionDAO(RedisTemplate<Serializable, Session> redisTemplate) {
        LOGGER.debug("======Create redis session DAO======");
        this.redisTemplate = redisTemplate;
        sessionOperations = redisTemplate.opsForValue();
    }

    /**
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        LOGGER.debug("======doCreate session======");
        final Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        sessionOperations.set(ACTIVE_SESSION + sessionId, session, 20, TimeUnit.SECONDS);
        return sessionId;
    }

    /**
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        LOGGER.debug("======doReadSession:" + String.valueOf(sessionId) + "," + System.currentTimeMillis() + "======");
        final Session session = sessionOperations.get(ACTIVE_SESSION + sessionId);
        return session;
    }

    /**
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        LOGGER.debug("======update session======");
        sessionOperations.set(ACTIVE_SESSION + session.getId(), session, 20, TimeUnit.SECONDS);
    }

    /**
     * @param session
     */
    @Override
    public void delete(Session session) {
        LOGGER.debug("======delete session======");
        final Serializable sessionId = session.getId();
        redisTemplate.delete(ACTIVE_SESSION + sessionId);
    }

    /**
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        return Collections.emptySet();
    }

//    private Cache<Serializable, Session> initLocalSessions(int initialCapacity) {
//        return CacheBuilder.newBuilder()
//                .maximumSize(600)
//                .initialCapacity(initialCapacity <= 0 ? 600 : initialCapacity)
//                .expireAfterWrite(15, TimeUnit.SECONDS)
//                .expireAfterAccess(15, TimeUnit.SECONDS)
//                .removalListener(LocalSessionsRemovalListener.DEFAULT)
//                .build();
//    }
//
//    // save to redis offset interval cache
//    private Cache<Serializable, Long> initSessionSaveIntervals(int initialCapacity) {
//        return CacheBuilder.newBuilder()
//                .maximumSize(600)
//                .initialCapacity(initialCapacity <= 0 ? 600 : initialCapacity)
//                .expireAfterWrite(30, TimeUnit.SECONDS)
//                .expireAfterAccess(30, TimeUnit.SECONDS)
//                .build();
//    }
}
