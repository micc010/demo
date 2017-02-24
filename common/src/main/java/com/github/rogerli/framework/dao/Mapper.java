package com.github.rogerli.framework.dao;

import com.github.rogerli.framework.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author roger.li
 * @date 2015/8/24
 */
public interface Mapper<T extends BaseModel, PK> {

    /**
     *
     * @param id
     * @return
     */
    int deleteByKey(PK id);

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
    T findByKey(PK id);

    /**
     *
     * @param record
     * @return
     */
    int updateByKey(T record);

    /**
     *
     * @param entity
     * @return
     */
    int updateByKeySelective(T entity);

    /**
     *
     * @param query
     * @return
     */
    List<T> findList(T query);

}
