package com.topcom.bi.utils;

import com.topcom.bi.config.UploadConfig;
import com.topcom.bi.domain.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author lism
 */
@Component
public class FileUploader {

    @Autowired
    UploadConfig uploadConfig;


    /**
     * 上传单个文件到basePath下指定子文件夹
     *
     * @param file      文件
     * @param subFolder 子文件夹
     * @return 上传的文件信息
     * @throws IOException
     */
    public UploadFile upload(MultipartFile file, String subFolder) throws IOException {
        UploadFile uploadFile = new UploadFile();
        String basePath = uploadConfig.getBaseFolder();
        String fileFolder = basePath;
        if (subFolder != null) {
            fileFolder = fileFolder + File.separatorChar + subFolder;
        }
        /**
         * 以当前日期为文件夹
         */
        fileFolder = fileFolder + File.separatorChar + LocalDate.now().toString();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //修改文件名 + 当前时间戳
        String targetFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + UUID.randomUUID()
                + "." + suffix;
        File targetFile = new File(getFileFolder(fileFolder, suffix), targetFileName);
        // 保存
        file.transferTo(targetFile);
        uploadFile.setFilename(fileName);
        uploadFile.setFilepath(targetFile.getAbsolutePath().replace(basePath, ""));
        uploadFile.setFileType(suffix);
        return uploadFile;
    }

    /**
     * 上传文件到basePath
     *
     * @param file 文件
     * @return
     * @throws IOException
     */
    public UploadFile upload(MultipartFile file) throws IOException {
        return upload(file, null);
    }

    /**
     * 批量上传
     *
     * @param files
     * @return
     * @throws IOException
     */
    public List<UploadFile> upload(MultipartFile[] files) throws IOException {
        return upload(files, null);
    }

    /**
     * 批量上传文件到basePath下指定子文件夹
     *
     * @param files     文件列表
     * @param subFolder 子文件夹
     * @return
     * @throws IOException
     */
    public List<UploadFile> upload(MultipartFile[] files, String subFolder) throws IOException {
        List<UploadFile> uploadFileList = new ArrayList<>();
        for (MultipartFile file : files) {
            UploadFile uploadFile = upload(file, subFolder);
            uploadFileList.add(uploadFile);
        }
        return uploadFileList;
    }

    /**
     * 通过文件后缀得到要存放的文件夹
     *
     * @param suffix
     * @return
     */
    public String getFileFolder(String fileFolder, String suffix) {
        String folder = fileFolder + File.separatorChar + suffix.toLowerCase();
        if (!new File(folder).exists()) {
            new File(folder).mkdirs();
        }
        return folder;
    }
}
