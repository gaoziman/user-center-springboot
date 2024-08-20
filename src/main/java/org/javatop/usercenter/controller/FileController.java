package org.javatop.usercenter.controller;

import org.javatop.usercenter.service.FileService;
import org.javatop.usercenter.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-26 15:46
 * @description :
 */
@RestController
public class FileController {


    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file 上传的文件
     * @return Result
     */
    @PostMapping("/file/upload")
    public Result uploadFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
