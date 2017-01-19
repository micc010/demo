/**
 * @文件名称： AbstractRestfulController.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/5
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.exception.IllegalValidateException;
import com.github.rogerli.utils.RestfulUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> delete(@PathVariable PK id) {
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
            value = {"/"},
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
            value = {"/{id}"},
            method = {RequestMethod.PUT},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> save(@PathVariable PK id, @RequestBody T entity, BindingResult bindingResult) throws IllegalValidateException {
        LOGGER.debug("======save======");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        RestfulUtils.bindErrors(jsonMap, bindingResult);
        getService().updateByKeySelective(entity);
        RestfulUtils.fillOk(jsonMap, HttpStatus.OK);
        return jsonMap;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> find(@PathVariable PK id) {
        LOGGER.debug("======find:" + String.valueOf(id) + "======");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Object t = getService().findByKey(id);
        RestfulUtils.fillOk(jsonMap, HttpStatus.OK, t);
        return jsonMap;
    }

    @RequestMapping(
            value = {""},
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = {"application/json"}
    )
    @ResponseBody
    public Map<String, Object> page(T query,
                                    @RequestParam(required = false) Integer pageNum,
                                    @RequestParam(required = false) Integer pageSize) {
        LOGGER.debug("======page======");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize, true);
        }
        List<T> list = getService().findList(query);
        if (pageNum != null && pageSize != null) {
            PageInfo info = new PageInfo(list);
            jsonMap.put("pageNum", pageNum);
            jsonMap.put("pageSize", pageSize);
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
}
