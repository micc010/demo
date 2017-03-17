/**
 * @文件名称： AbstractRestfulController.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/5
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web;

import com.github.pagehelper.PageInfo;
import com.github.rogerli.framework.model.BaseModel;
import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.exception.IllegalValidateException;
import com.github.rogerli.utils.RestfulUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roger
 * @create 2017/1/5 14:55
 */
public abstract class AbstractJsonController<T extends BaseModel, PK> extends AbstractController {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractJsonController.class);

    protected abstract Service<T, PK> getService();

    /**
     * @param id
     * @return
     */
    @RequestMapping(
            value = {"/delete"},
            method = {RequestMethod.POST},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> delete(PK id) {
        LOGGER.debug("======delete:" + String.valueOf(id) + "======");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        getService().deleteByKey(id);
        RestfulUtils.fillOk(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    /**
     * @param entity
     * @param bindingResult
     * @return
     * @throws IllegalValidateException
     */
    @RequestMapping(
            value = {"/add"},
            method = {RequestMethod.POST},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> add(@RequestBody T entity, BindingResult bindingResult) throws IllegalValidateException {
        LOGGER.debug("======add======");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.bindErrors(jsonMap, bindingResult);
        getService().insertSelective(entity);
        RestfulUtils.fillOk(jsonMap, HttpStatus.OK, entity);
        return jsonMap;
    }

    /**
     * @param entity
     * @param bindingResult
     * @return
     * @throws IllegalValidateException
     */
    @RequestMapping(
            value = {"/save"},
            method = {RequestMethod.POST},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> save(@RequestBody T entity, BindingResult bindingResult) throws IllegalValidateException {
        LOGGER.debug("======save======");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.bindErrors(jsonMap, bindingResult);
        getService().updateByKeySelective(entity);
        RestfulUtils.fillOk(jsonMap, HttpStatus.OK, entity);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/find"},
            method = {RequestMethod.GET},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> find(PK id) {
        LOGGER.debug("======find:" + String.valueOf(id) + "======");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Object t = getService().findByKey(id);
        RestfulUtils.fillOk(jsonMap, HttpStatus.OK, t);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/page"},
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> page(T query) {
        LOGGER.debug("======page======");
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        filter(query);

        List<T> list = getService().findList(query);
        if (query.getPageNum() != null && query.getPageNum() > 0
                && query.getPageSize() != null && query.getPageSize() > 0) {
            PageInfo info = new PageInfo(list);
            jsonMap.put("pageNum", query.getPageNum());
            jsonMap.put("pageSize", query.getPageSize());
            jsonMap.put("total", info.getTotal());
            jsonMap.put("pages", info.getPages());
            jsonMap.put("isFirstPage", info.isIsFirstPage());
            jsonMap.put("isLastPage", info.isIsLastPage());
            jsonMap.put("hasPreviousPage", info.isHasPreviousPage());
            jsonMap.put("hasNextPage", info.isHasNextPage());
        }
        RestfulUtils.fillOk(jsonMap, HttpStatus.OK, list);
        return jsonMap;
    }

    protected void filter(T query){

    }

}
