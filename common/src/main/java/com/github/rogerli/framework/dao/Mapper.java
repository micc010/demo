package com.github.rogerli.framework.dao;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author roger.li
 * @date 2015/8/24
 */
@Transactional
public interface Mapper<T extends Serializable, PK> {

    /**
     *
     * @param id
     * @return
     */

    int deleteByPrimaryKey(PK id);

    /**
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     *
     * @param entity
     * @return
     */
    int insertSelective(T entity);

    /**
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     *
     * @param query
     * @return
     */
    List<T> selectList(T query);

}
