package com.github.rogerli.system.operation.service;

import com.github.rogerli.framework.service.AbstractService;
import com.github.rogerli.system.operation.dao.OperationMapper;
import com.github.rogerli.system.operation.entity.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService extends AbstractService<Operation, String, OperationMapper> {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperationService.class);

	@Autowired
	private OperationMapper operationMapper;

	@Override
	protected OperationMapper getMapper() {
		return operationMapper;
	}

}
