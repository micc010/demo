package com.github.rogerli.framework.service;

import com.github.pagehelper.PageHelper;
import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.framework.model.BaseModel;
import com.github.rogerli.utils.RestfulUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author roger.li
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
public abstract class AbstractService<T extends BaseModel, PK, E extends Mapper> implements Service<T, PK> {

    protected abstract E getMapper();

    @Override
    public int deleteByKey(PK id) {
        return getMapper().deleteByKey(id);
    }

    @Override
    public int deleteInBatch(List<PK> list) {
        Assert.notEmpty(list);
        int size = 0;
        for (PK id :
                list) {
            deleteByKey(id);
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
    public T findByKey(PK id) {
        return ((T) getMapper().findByKey(id));
    }

    @Override
    public int updateByKey(T entity) {
        Assert.notNull(entity);
        return getMapper().updateByKey(entity);
    }

    @Override
    public int updateByKeySelective(T entity) {
        Assert.notNull(entity);
        return getMapper().updateByKeySelective(entity);
    }

    public int updateInBatch(List<T> list) {
        Assert.notEmpty(list);
        int size = 0;
        for (T entity :
                list) {
            updateByKeySelective(entity);
            size++;
        }
        return size;
    }

    @Override
    public List<T> findList(T query) {
        if (query.getPageNum() != null && query.getPageNum() > 0
                && query.getPageSize() != null && query.getPageSize() > 0) {
            PageHelper.startPage(query.getPageNum(), query.getPageSize());
        }
        if(StringUtils.hasText(query.getSortBy())){
            RestfulUtils.checkOrder(query.getSorted());
            String sortBy = query.getSortBy();
            sortBy.replaceAll(",", " " + query.getSorted() + ",");
            sortBy += " " + query.getSorted();
            PageHelper.orderBy(sortBy);
        }
        return getMapper().findList(query);
    }

}
