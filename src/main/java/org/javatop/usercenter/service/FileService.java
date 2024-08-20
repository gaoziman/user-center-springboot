package org.javatop.usercenter.service;

import org.javatop.usercenter.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-26 15:46
 * @description :
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param file 上传的文件
     * @return Result
     */
    Result uploadFile(MultipartFile file);
}
