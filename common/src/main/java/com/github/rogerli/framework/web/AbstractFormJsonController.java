/**
 * @文件名称： AbstractController.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/2
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.exception.IllegalValidateException;
import com.github.rogerli.framework.web.model.EntityList;
import com.github.rogerli.framework.web.model.PKList;
import com.github.rogerli.utils.RestfulUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roger
 * @description
 * @create 2016/12/2 16:58
 */
public abstract class AbstractFormJsonController<T extends Serializable, PK> extends AbstractController {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractFormJsonController.class);

    protected abstract Service<T, PK> getService();

    /**
     *
     * @param pkList
     * @return
     */
    @RequestMapping(
            value = {"/delete-list"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public Map<String, Object> deleteList(@RequestBody PKList pkList) {
        LOGGER.debug("======deleteList======");
        getService().deleteInBatch(pkList.getList());

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(
            value = {"/delete-one"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> deleteOne(PK id) {
        LOGGER.debug("======deleteOne:" + String.valueOf(id) + "======");
        getService().deleteByKey(id);

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    /**
     *
     * @param entity
     * @param bindingResult
     * @return
     * @throws IllegalValidateException
     */
    @RequestMapping(
            value = {"/add-one"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> addOne(T entity, BindingResult bindingResult) throws IllegalValidateException {
        LOGGER.debug("======addOne======");

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.bindErrors(jsonMap, bindingResult);

        getService().insertSelective(entity);

        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    /**
     *
     * @param entityList
     * @param bindingResult
     * @return
     * @throws IllegalValidateException
     */
    @RequestMapping(
            value = {"/add-list"},
            method = {RequestMethod.GET},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> addList(@RequestBody EntityList<T> entityList, BindingResult bindingResult) throws IllegalValidateException {
        LOGGER.debug("======addList======");

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.bindErrors(jsonMap, bindingResult);

        getService().insertInBatch(entityList.getList());

        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/save-one"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> saveOne(T entity, BindingResult bindingResult) throws IllegalValidateException {
        LOGGER.debug("======saveOne======");

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.bindErrors(jsonMap, bindingResult);

        getService().updateByKey(entity);

        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/query-one"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> queryOne(PK id) {
        LOGGER.debug("======queryOne:" + String.valueOf(id) + "======");
        Object t = getService().findByKey(id);

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        jsonMap.put("data", t);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/query-list"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> queryList(T query) {
        LOGGER.debug("======queryList======");
        List list = getService().findList(query);

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        jsonMap.put("data", list);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/query-page"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> queryPage(T query, Page<T> page) {
        LOGGER.debug("======queryPage======");
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), false);
        List<T> list = getService().findList(query);

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        jsonMap.put("data", list);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/save-list"},
            method = {RequestMethod.POST},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> saveList(@RequestBody EntityList<T> list) {
        LOGGER.debug("======saveList======");
        getService().updateInBatch(list.getList());

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.fill(jsonMap, HttpStatus.OK);
        return jsonMap;
    }
}
