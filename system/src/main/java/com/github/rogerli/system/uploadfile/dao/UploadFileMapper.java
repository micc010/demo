package com.github.rogerli.system.uploadfile.dao;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.system.uploadfile.entity.UploadFile;
import com.github.rogerli.system.uploadfile.model.UploadFileModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadFileMapper extends Mapper<UploadFile, String> {

    void updateByItemId(UploadFileModel model);
}
