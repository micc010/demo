package com.github.rogerli.framework.service;

import com.github.pagehelper.Page;
import com.github.rogerli.framework.model.Model;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author roger.li
 * @date 2016/11/29
 */
public interface Service<T> {

    /**
     *
     * @param id
     * @return
     */
    @Transactional
    int deleteByPrimaryKey(String id);

    /**
     *
     * @param entity
     * @return
     */
    @Transactional
    int insert(T entity);

    /**
     *
     * @param entity
     * @return
     */
    @Transactional
    int insertSelective(T entity);

    /**
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    T selectByPrimaryKey(String id);

    /**
     *
     * @param entity
     * @return
     */
    @Transactional
    int updateByPrimaryKey(T entity);

    /**
     *
     * @param entity
     * @return
     */
    @Transactional
    int updateByPrimaryKeySelective(T entity);

    /**
     *
     * @param query
     * @return
     */
    @Transactional(readOnly = true)
    List<T> selectList(Model query);

}
