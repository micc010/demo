/**
 * @文件名称： IndexController.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/5
 * @作者 ： Roger
 */
package com.github.rogerli;

import com.github.rogerli.framework.web.AbstractTmplController;
import com.github.rogerli.system.login.entity.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Roger
 * @create 2017/1/5 19:04
 */
@Controller
public class IndexController extends AbstractTmplController<Login, String>{

    private final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Override
    protected String getFilePath() {
        return "/";
    }

    @RequestMapping(
        value = {"/index", "", "/"}
    )
    public ModelAndView index() {
        LOGGER.debug("======index======");
        ModelAndView mv = new ModelAndView(getFilePath() + "index");
        return mv;
    }

    @RequestMapping(
            value = {"/home"},
            method = RequestMethod.GET
    )
    public ModelAndView home() {
        LOGGER.debug("======home======");
        ModelAndView mv = new ModelAndView(getFilePath() + "home");
        return mv;
    }

    @RequestMapping(
            value = {"/login"},
            method = RequestMethod.GET
    )
    public ModelAndView login() {
        LOGGER.debug("======login======");
        ModelAndView mv = new ModelAndView(getFilePath() + "login");
        return mv;
    }

}
