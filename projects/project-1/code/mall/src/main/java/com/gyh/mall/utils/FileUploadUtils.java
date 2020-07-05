package com.gyh.mall.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class FileUploadUtils {

    private static final String BASE_PATH = "/upload";

    public static Map<String, Object> parseRequest(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        // 工厂模式 DruidDataSourceFactory
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 如果文件名称乱码，需要设置这个
        upload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            while (iterator.hasNext()){
                //这个item就是每个input的封装
                FileItem item = iterator.next();
                if(item.isFormField()){
                    //是否是普通的form表单数据
                    //user
                    processFormField(item, resultMap);
                }else {
                    //user
                    processUploadedFile(item,request, resultMap);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 处理上传的文件业务逻辑
     * @param item
     * @param request
     * @param map
     */
    private static void processUploadedFile(FileItem item, HttpServletRequest request, Map<String, Object> map) {
        // 就是form表单的input name
        String fieldName = item.getFieldName();
        String fileName = item.getName();
        System.out.println(fieldName);
        System.out.println(fileName);
        //保存到服务器磁盘上
        //哈希值的特点：散列 31 乘子
        //其他方式完全ok
        fileName = UUID.randomUUID() + "-" + fileName;
        int code = fileName.hashCode();
        // 8位十六进制 1 /2 /3 /8 /9 /a /f /1 /fileName
        //           16*16*16*16*16*16*16*16/ 寥寥无几的几个文件
        String hexString = Integer.toHexString(code);
        char[] chars = hexString.toCharArray();
        String prefix = BASE_PATH;
        for (char c : chars) {
            prefix = prefix + "/" + c;
        }
        String basePath = prefix + "/" + fileName;
        String path = request.getServletContext().getRealPath(basePath);
        File file = new File(path);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            item.write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //显示的 保存相对路径
        map.put(fieldName, basePath);
    }

    /**
     * 处理form表单数据的具体业务逻辑
     * @param item
     * @param map
     */
    private static void processFormField(FileItem item, Map<String, Object> map) {
        String fieldName = item.getFieldName();
        String value = null;
        try {
            value = item.getString("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put(fieldName, value);
        System.out.println(fieldName + " = " + value);
    }
}
