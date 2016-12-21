package com.github.rogerli.framework.service;

import com.github.rogerli.framework.dao.Mapper;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * @author roger.li
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class AbstractService<T extends Serializable, PK, E extends Mapper> implements Service<T, PK> {

    protected abstract E getMapper();

    @Override
    public int deleteByPrimaryKey(PK id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int deleteInBatch(List<PK> list) {
        Assert.notEmpty(list);
        int size = 0;
        for (PK id :
                list) {
            deleteByPrimaryKey(id);
            size++;
        }
        return size;
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
    public int insertInBatch(List<T> list) {
        Assert.notEmpty(list);
        int size = 0;
        for (T entity :
                list) {
            insertSelective(entity);
            size++;
        }
        return size;
    }

    @Override
    public T selectByPrimaryKey(PK id) {
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

    public int updateInBatch(List<T> list) {
        Assert.notEmpty(list);
        int size = 0;
        for (T entity :
                list) {
            updateByPrimaryKeySelective(entity);
            size++;
        }
        return size;
    }

    @Override
    public List<T> selectList(T query) {
        return getMapper().selectList(query);
    }

}
