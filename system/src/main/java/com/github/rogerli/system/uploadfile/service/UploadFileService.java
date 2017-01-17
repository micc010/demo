package com.github.rogerli.system.uploadfile.service;

import com.github.rogerli.framework.service.AbstractService;
import com.github.rogerli.system.uploadfile.dao.UploadFileMapper;
import com.github.rogerli.system.uploadfile.entity.UploadFile;
import com.github.rogerli.system.uploadfile.model.UploadFileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileService extends AbstractService<UploadFile, String, UploadFileMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileService.class);

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Override
    protected UploadFileMapper getMapper() {
        return uploadFileMapper;
    }

    public void updateByItemId(UploadFileModel model) {
        getMapper().updateByItemId(model);
    }

}
