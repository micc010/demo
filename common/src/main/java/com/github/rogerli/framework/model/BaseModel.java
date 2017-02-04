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

	private String sortBy;

	private String sorted;

	private Integer pageNum;

	private Integer pageSize;

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

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSorted() {
		return sorted;
	}

	public void setSorted(String sorted) {
		this.sorted = sorted;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
