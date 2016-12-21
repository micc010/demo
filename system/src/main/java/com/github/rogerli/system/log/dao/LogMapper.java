package com.github.rogerli.system.log.dao;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.system.log.entity.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMapper extends Mapper<Log, String> {

}