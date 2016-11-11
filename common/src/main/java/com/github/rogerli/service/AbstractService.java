package com.github.rogerli.service;

import com.github.rogerli.persistence.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author roger.li
 */
public abstract class AbstractService<T, E extends Mapper> implements Service<T> {
    @Autowired
    private ApplicationContext context;
    private E mapper;
    private Class<E> clazz;

    public AbstractService() {
    }

    @Autowired
    public AbstractService(ApplicationContext context) {
        this();
        this.context = context;
    }

    @Override
    public int persist(T entity) {
        if (entity == null) {
            return 0;
        }
        return getMapper().insertSelective(entity);
    }

    @Override
    public T find(int id) {
        return ((T) getMapper().selectByPrimaryKey(id));
    }

    @Override
    public List<T> findAll() {
        return getMapper().selectAll();
    }

    @Override
    public int update(T entity) {
        return getMapper().updateByPrimaryKeySelective(entity);
    }

    @Override
    public int delete(int id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int getElementCount() {
        return getMapper().selectCount();
    }

    public E getMapper() {
        initMapper();
        return mapper;
    }

    @SuppressWarnings("unchecked")
    public void setMapper(E mapper) {
        this.mapper = mapper;
    }

    protected void initMapper() {
        if (this.mapper == null) {
            clazz = (Class<E>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[1];
            this.mapper = context.getBean(clazz);
        }
    }
}
