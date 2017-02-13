/**
 * @文件名称： AbstractController.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/2
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web;

import com.github.rogerli.framework.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Roger
 * @description
 * @create 2016/12/2 16:58
 */
public abstract class AbstractTmplController<T extends BaseModel, PK> extends AbstractController {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractTmplController.class);

    @RequestMapping(
            value = {"/edit"},
            method = {RequestMethod.GET}
    )
    public ModelAndView edit(PK id) {
        LOGGER.debug("======edit======");
        ModelAndView mv = new ModelAndView(getFilePath() + "/edit");
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping(
            value = {"/view"},
            method = {RequestMethod.GET}
    )
    public ModelAndView view(PK id) {
        LOGGER.debug("======view======");
        ModelAndView mv = new ModelAndView(getFilePath() + "/view");
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping(
            value = {"/list"},
            method = {RequestMethod.GET}
    )
    public ModelAndView list(T query) {
        LOGGER.debug("======list======");
        ModelAndView mv = new ModelAndView(getFilePath() + "/list");
        mv.addObject("query", query);
        return mv;
    }

    protected abstract String getFilePath();

}
