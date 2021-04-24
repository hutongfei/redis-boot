package com.my.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.my.utils.OSSClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author hutf
 * @createTime 2021年04月23日 09:53:00
 */
@RequestMapping
@CrossOrigin
@Controller
public class OssController {

    @RequestMapping("/upload")
    @ResponseBody
    public PutObjectResult upload(@RequestParam MultipartFile file) throws IOException {
        OSS client = OSSClient.getClient();
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        PutObjectResult bucket = client.putObject("hutf-bucket", fileName, inputStream);
        client.shutdown();
        return bucket;
    }

    @GetMapping("/downLoad/{fileName}")
    @ResponseBody
    public String downLoad(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        OSS ossClient = OSSClient.getClient();
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject("hutf-bucket", fileName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        ResponseMessage response1 = ossObject.getResponse();
        String uri = response1.getUri();

        return uri;
    }
}
