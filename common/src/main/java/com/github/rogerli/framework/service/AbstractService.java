package com.github.rogerli.framework.service;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.framework.model.Model;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author roger.li
 * @date 2016/11/29
 */
public abstract class AbstractService<T, E extends Mapper> implements Service<T> {

    @Override
    public int deleteByPrimaryKey(String id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T entity) {
        Assert.notNull(entity);
        return getMapper().insert(entity);
    }

    @Override
    public int insertSelective(T entity) {
        Assert.notNull(entity);
        return getMapper().insertSelective(entity);
    }

    @Override
    public T selectByPrimaryKey(String id) {
        return ((T) getMapper().selectByPrimaryKey(id));
    }

    @Override
    public int updateByPrimaryKey(T entity) {
        Assert.notNull(entity);
        return getMapper().updateByPrimaryKey(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(T entity) {
        Assert.notNull(entity);
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectList(Model query) {
        return getMapper().selectList(query);
    }

    public abstract E getMapper();

}
