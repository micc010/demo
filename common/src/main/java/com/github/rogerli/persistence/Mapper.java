package com.github.rogerli.persistence;

import java.util.List;

/**
 * @author roger.li
 * @date 2015/8/24
 */
public interface Mapper<T> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(T entity);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T entity);

    int selectCount();

    List<T> selectAll();

}
