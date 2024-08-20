package org.javatop.usercenter.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.javatop.usercenter.common.enums.HttpStatusEnum;
import org.javatop.usercenter.domian.vo.file.UploadFileRspVO;
import org.javatop.usercenter.exception.BizException;
import org.javatop.usercenter.service.FileService;
import org.javatop.usercenter.util.MinioUtil;
import org.javatop.usercenter.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-26 15:47
 * @description :
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 上传文件
     *
     * @param file 上传的文件
     * @return Result
     */
    @Override
    public Result uploadFile(MultipartFile file) {
        try {
            // 上传文件
            String url = minioUtil.uploadFile(file);

            //构造成功返参，将图片的访问链接返回
            UploadFileRspVO uploadFile = UploadFileRspVO.builder()
                    .url(url)
                    .build();
            return Result.success(uploadFile);
        } catch (Exception e) {
            log.error("==> 上传文件至 Minio 错误: ", e);
            // 手动抛出业务异常，提示 “文件上传失败”
            throw new BizException(HttpStatusEnum.FILE_UPLOAD_FAILED);
        }
    }
}
