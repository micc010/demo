/**
 * @文件名称：FlowModel.java
 * @文件描述：
 * @版权所有：(C)2008-2018
 * @公司：
 * @完成日期:  2016-4-1  
 * @作者    ： roger  
 */
package com.github.rogerli.flow.define.model;

import com.github.rogerli.flow.define.entity.Flow;
import com.github.rogerli.flow.define.entity.Node;
import com.github.rogerli.flow.define.entity.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roger
 * @description
 */
public class FlowModel extends Flow {

	private List<Node> nodeList = new ArrayList<Node>(10);

	private List<Path> pathList = new ArrayList<Path>(10);

	/**
	 * @return the nodeList
	 */
	public List<Node> getNodeList() {
		return nodeList;
	}

	/**
	 * @param nodeList the nodeList to set
	 */
	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

	/**
	 * @return the pathList
	 */
	public List<Path> getPathList() {
		return pathList;
	}

	/**
	 * @param pathList the pathList to set
	 */
	public void setPathList(List<Path> pathList) {
		this.pathList = pathList;
	}

}
