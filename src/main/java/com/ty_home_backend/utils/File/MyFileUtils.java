package com.ty_home_backend.utils.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MyFileUtils {
    public static String getByteArrayMd5Hex(byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }
    /**
     * 给出文件相对路径，读取文件并返回byte[]
     */
    public static byte[] readFileByteArray(File path) throws IOException {
        return FileUtils.readFileToByteArray(path);
    }
    /**
     * 给出图片名，返回存储路径
     * @param fileName
     * @return
     */
    public static String getImageStorePath(String fileName, Date date){
        LocalDate localDate=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return "D:\\ty\\2022-study\\pic-store" + File.separator+localDate.getYear()+
                File.separator+localDate.getMonth()+File.separator+localDate.getDayOfMonth()+
                File.separator+fileName;
    }

    /**
     * 给出路径和文件，尝试保存到本地，失败返回异常并删除以创建文件
     * @param path 路径
     * @param file 文件
     */
    public static void saveFileToLocal(File path, MultipartFile file) throws Exception {
        /*检查路径上的文件夹，没有则创建文件夹，
         *随后创建新文件
         */
        if ((!path.getParentFile().exists()
                && !path.getParentFile().mkdirs()) ||
                !path.createNewFile()) {
            throw new Exception("create file failed");
        }
        try {
            file.transferTo(path);
        } catch (Exception e) {
            /*创建失败，如果已创建空文件，删除之*/
            Files.deleteIfExists(path.toPath());
        }
    }
}
