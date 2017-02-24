/**
 * @文件名称：NodeDao.java
 * @文件描述：
 * @版权所有：(C)2008-2018
 * @公司：
 * @完成日期:  2016-4-6  
 * @作者    ： roger  
 */
package com.github.rogerli.flow.define.dao;

import com.github.rogerli.flow.define.entity.Node;
import com.github.rogerli.framework.dao.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Roger
 * @description
 */
@Repository
public interface NodeMapper extends Mapper<Node, String> {

	int deleteByFlowId(String flowId);

}
