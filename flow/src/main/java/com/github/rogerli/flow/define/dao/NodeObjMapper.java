package com.github.rogerli.flow.define.dao;

import com.github.rogerli.flow.define.entity.NodeObj;
import com.github.rogerli.framework.dao.Mapper;

/**
 * Created by roger on 2017/2/21.
 */
public interface NodeObjMapper extends Mapper<NodeObj, String>{

    int deleteByFlowId(String flowId);

}
