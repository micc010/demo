/**
 * @文件名称：BaseModel.java
 * @文件描述：
 * @版权所有：(C)2008-2018
 * @公司：
 * @完成日期:  2016-1-20  
 * @作者    ： roger  
 */
package com.github.rogerli.framework.model;

/**
 * @author Roger
 */
public class BaseModel implements Model {

	private long token;
	
	/**
	 * @see Model#getToken()
	 */
	@Override
	public long getToken() {
		return this.token;
	}

	/**
	 * @see Model#setToken(long)
	 */
	@Override
	public void setToken(long token) {
		this.token = token;
	}

}
