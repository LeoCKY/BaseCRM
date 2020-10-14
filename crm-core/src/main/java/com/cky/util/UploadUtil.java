package com.cky.util;

import com.cky.exception.BusinessException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * 文件上傳工具類
 */
@Getter
@Setter
@ConfigurationProperties
@Component
public class UploadUtil {

    /**
     * 按照當日創建文件夾
     */
    @Value("${upload.isDayType}")
    private boolean isDayType;
    /**
     * 自定義文件路徑
     */
    @Value("${upload.uploadPath}")
    private String uploadPath;

    @Value("${upload.imagePath}")
    private String imagePath;

    public static final String IMAGE_SUFFIX = "bmp,jpg,png,gif,jpeg";


    public UploadUtil() {
    }

    public String upload(MultipartFile multipartFile) {
        if (isNull(multipartFile)) {
            throw new BusinessException("上傳數據/地址獲取異常");
        }

        LoadType loadType = fileNameStyle(multipartFile);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), loadType.getCurrentFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadType.getFileName();
    }

    /**
     * 格式化文件名 默認採用UUID
     *
     * @return
     */
    public LoadType fileNameStyle(MultipartFile multipartFile) {
        String curr = multipartFile.getOriginalFilename();
        int suffixLen = curr.lastIndexOf(".");
        boolean flag = false;
        int index = -1;
        if ("blob".equals(curr)) {
            flag = true;
            index = 0;
            curr = UUID.randomUUID() + ".png";
        } else if (suffixLen == -1) {
            throw new BusinessException("文件獲取異常");
        }
        if (!flag) {
            String suffix = curr.substring(suffixLen, curr.length());
            index = Arrays.binarySearch(IMAGE_SUFFIX.split(","),
                    suffix.replace(".", ""));

            curr = UUID.randomUUID() + suffix;
        }
        LoadType loadType = new LoadType();
        loadType.setFileName(curr);
        //image 情況
        curr = StringUtils.isEmpty(imagePath) || index == -1 ?
                uploadPath + File.separator + curr : imagePath + File.separator + curr;
        loadType.setCurrentFile(new File(curr));
        return loadType;
    }

    private boolean isNull(MultipartFile multipartFile) {
        if (null != multipartFile) {
            return false;
        }
        return true;
    }

}

@Data
class LoadType {
    private String fileName;
    private File currentFile;
}
