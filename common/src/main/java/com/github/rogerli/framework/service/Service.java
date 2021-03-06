package com.github.rogerli.framework.service;

import com.github.rogerli.framework.model.BaseModel;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author roger.li
 */
public interface Service<T extends BaseModel, PK> {

    /**
     * @param id
     * @return
     */
    @Transactional
    int deleteByKey(PK id);

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
    T findByKey(PK id);

    /**
     * @param entity
     * @return
     */
    @Transactional
    T updateByKey(T entity);

    /**
     * @param entity
     * @return
     */
    @Transactional
    T updateByKeySelective(T entity);

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
    List<T> findList(T query);

}
