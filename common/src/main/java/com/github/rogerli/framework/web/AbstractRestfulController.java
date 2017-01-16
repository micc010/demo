/**
 * @文件名称： AbstractRestfulController.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/5
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.exception.IllegalValidateException;
import com.github.rogerli.utils.RestfulUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roger
 * @create 2017/1/5 14:55
 */
public abstract class AbstractRestfulController<T extends Serializable, PK> extends AbstractController {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractRestfulController.class);

    protected abstract Service<T, PK> getService();

    /**
     * @param id
     * @return
     */
    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> delete(@PathVariable PK id) {
        LOGGER.debug("======delete:" + String.valueOf(id) + "======");
        getService().deleteByKey(id);

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    /**
     * @param entity
     * @param bindingResult
     * @return
     * @throws IllegalValidateException
     */
    @RequestMapping(
            value = {""},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> add(T entity, BindingResult bindingResult) throws IllegalValidateException {
        LOGGER.debug("======add======");

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.bindErrors(jsonMap, bindingResult);

        getService().insertSelective(entity);

        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    /**
     * @param entity
     * @param bindingResult
     * @return
     * @throws IllegalValidateException
     */
    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.PUT},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> save(@PathVariable PK id, T entity, BindingResult bindingResult) throws IllegalValidateException {
        LOGGER.debug("======save======");

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.bindErrors(jsonMap, bindingResult);

        getService().updateByKeySelective(entity);

        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> find(@PathVariable PK id) {
        LOGGER.debug("======find:" + String.valueOf(id) + "======");
        Object t = getService().findByKey(id);

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        jsonMap.put("data", t);
        return jsonMap;
    }

    @RequestMapping(
            value = {""},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> page(T query, Page<T> page) {
        LOGGER.debug("======page======");
        if (page != null && page.getPageNum() != 0) {
            PageHelper.startPage(page.getPageNum(), page.getPageSize(), false);
        }
        List<T> list = getService().findList(query);

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        jsonMap.put("data", list);

        if (page != null && page.getPageNum() != 0) {
            // TODO nextPage
            jsonMap.put("next", "");

            // TODO curPage
            jsonMap.put("current", "");

            // TODO prevPage
            jsonMap.put("prev", "");
        }

        return jsonMap;
    }

}
