/**
 * @文件名称：FlowService.java
 * @文件描述：
 * @版权所有：(C)2008-2018
 * @公司：
 * @完成日期: 2016-4-7
 * @作者 ： roger
 */
package com.github.rogerli.flow.define.service;

import com.github.rogerli.flow.define.dao.FlowMapper;
import com.github.rogerli.flow.define.dao.NodeMapper;
import com.github.rogerli.flow.define.dao.PathMapper;
import com.github.rogerli.flow.define.entity.Flow;
import com.github.rogerli.flow.define.entity.Node;
import com.github.rogerli.flow.define.entity.Path;
import com.github.rogerli.flow.define.model.FlowModel;
import com.github.rogerli.flow.exception.ProcessException;
import com.github.rogerli.framework.service.AbstractService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author Roger
 * @description
 */
@Service
public class FlowService extends AbstractService<Flow, String, FlowMapper> {

    private final Logger logger = LoggerFactory.getLogger(FlowService.class);

    @Autowired
    private FlowMapper flowMapper;

    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private PathMapper pathMapper;

    /**
     * @return
     */
    @Override
    public FlowMapper getMapper() {
        return flowMapper;
    }

    /**
     * @param flowId
     * @return
     */
    public FlowModel selectFlowById(String flowId) {
        return flowMapper.selectFlowById(flowId);
    }

    public void save(FlowModel flowModel) throws ProcessException {


        if (!validateFlow(flowModel)) {
            throw new ProcessException("校验出错！");
        }

        // 保存流程
        flowMapper.insert(flowModel);

        // 保存节点
        for (Node node : flowModel.getNodeList()) {
            nodeMapper.insert(node);
        }

        // 保存路径
        for (Path path : flowModel.getPathList()) {
            pathMapper.insert(path);
        }

        // 保存流程图路径及文件
        try {
            FileUtils.writeByteArrayToFile(new File(flowModel.getJsonUrl()),
                    flowModel.getJsonString().getBytes());
        } catch (IOException e) {
            throw new ProcessException("保存流程文件失败!");
        }

    }

    /**
     * 删除流程
     *
     * @param id
     */
    public void delete(String id) {

        // 删除流程
        flowMapper.deleteByKey(id);

        // 删除节点
        nodeMapper.deleteByFlowId(id);

        // 删除路径
        pathMapper.deleteByFlowId(id);

    }

    /**
     * TODO 校验流程
     *
     * @param flowModel
     * @return
     */
    public boolean validateFlow(FlowModel flowModel) {

        return true;
    }

}