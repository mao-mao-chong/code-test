package org.com.bmw.controller;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.util.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileController {
    //获取yaml中配置的上传路径属性
    @Value(("${file.upload.path}"))
    private String uploadPath;
    @Value(("${file.upload.ip}"))
    private String ip;
    @Value(("${file.upload.port}"))
    private String port;

    @RequestMapping(value ="/upload",method = RequestMethod.POST)
    public ReturnMsg uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("上传文件开始=========");
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        //上传文件的根路径
        String ROOT_PATH = uploadPath + File.separator + "files";
        String originalFilename = file.getOriginalFilename(); // 文件的原始名称
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String extName = FileUtil.extName(originalFilename);// png
        if (!FileUtil.exist(ROOT_PATH)) {
            FileUtil.mkdir(ROOT_PATH); // 如果当前文件的父级目录不存在，就创建
        }
        originalFilename = System.currentTimeMillis() + suffix;
        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);
        // 存储文件到本地的磁盘里面去
        file.transferTo(saveFile);
        //返回文件的链接，这个链接就是文件的下载地址，这个下载地址就是我的后台提供出来的
        String url = "http://" + ip + ":" + port + "/file/download?imageName=" + originalFilename;
        Map map = new HashMap();
        map.put("pictureUrl",url);
        returnMsg.setData(map);
        log.info("上传文件结束========={}",returnMsg);
        return returnMsg;
    }

    //文件下载相关代码
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadImage(String imageName, HttpServletRequest request, HttpServletResponse response) {
        log.info("the imageName is : "+imageName);
        String fileUrl = uploadPath + File.separator + "files" + File.separator+imageName;
        if (fileUrl != null) {
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + imageName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
