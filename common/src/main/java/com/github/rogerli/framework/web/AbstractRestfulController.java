/**
 * @文件名称： AbstractRestfulController.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/5
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web;

import com.github.rogerli.framework.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author Roger
 * @create 2017/1/5 14:55
 */
public abstract class AbstractRestfulController<T extends Serializable, PK> extends AbstractController {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractRestfulController.class);

    protected abstract Service<T, PK> getService();


}
