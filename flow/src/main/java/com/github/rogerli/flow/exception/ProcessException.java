/**
 * @文件名称：ProcessException.java
 * @文件描述：
 * @版权所有：(C)2008-2018
 * @公司：
 * @完成日期:  2016-4-25  
 * @作者    ： roger  
 */
package com.github.rogerli.flow.exception;

/**
 * @author Roger
 * @description
 */
public class ProcessException extends RuntimeException {

	/**
	 * @param message
	 */
	public ProcessException(String message) {
		super(message);
	}

	/**
	 * @param root
	 */
	public ProcessException(Throwable root) {
		super(root);
	}

	/**
	 * @param message
	 * @param root
	 */
	public ProcessException(String message, Throwable root) {
		super(message, root);
	}

}
