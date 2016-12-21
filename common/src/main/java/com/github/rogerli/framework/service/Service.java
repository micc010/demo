package com.github.rogerli.framework.service;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author roger.li
 */
public interface Service<T extends Serializable, PK> {

    /**
     * @param id
     * @return
     */
    @Transactional
    int deleteByPrimaryKey(PK id);

    /**
     * @param list
     * @return
     */
    @Transactional
    int deleteInBatch(List<PK> list);

    /**
     * @param entity
     * @return
     */
    @Transactional
    int insert(T entity);

    /**
     * @param entity
     * @return
     */
    @Transactional
    int insertSelective(T entity);

    /**
     * @param list
     * @return
     */
    @Transactional
    int insertInBatch(List<T> list);

    /**
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    T selectByPrimaryKey(PK id);

    /**
     * @param entity
     * @return
     */
    @Transactional
    int updateByPrimaryKey(T entity);

    /**
     * @param entity
     * @return
     */
    @Transactional
    int updateByPrimaryKeySelective(T entity);

    /**
     * @param list
     * @return
     */
    @Transactional
    int updateInBatch(List<T> list);

    /**
     * @param query
     * @return
     */
    @Transactional(readOnly = true)
    List<T> selectList(T query);

}
