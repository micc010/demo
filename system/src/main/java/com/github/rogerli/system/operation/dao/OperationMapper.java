package com.github.rogerli.system.operation.dao;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.system.operation.entity.Operation;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationMapper extends Mapper<Operation, String> {

}
