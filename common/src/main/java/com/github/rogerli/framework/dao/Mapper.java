package com.github.rogerli.framework.dao;

import com.github.rogerli.framework.model.Model;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author roger.li
 * @date 2015/8/24
 */
@Transactional
public interface Mapper<T> {

    /**
     *
     * @param id
     * @return
     */

    int deleteByPrimaryKey(String id);

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
    T selectByPrimaryKey(String id);

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
    List<T> selectList(Model query);

}
