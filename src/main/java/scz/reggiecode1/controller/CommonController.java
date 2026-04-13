package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scz.reggiecode1.common.Result;

import java.io.*;
import java.util.UUID;

/**
 * 通用控制器（文件上传下载）
 */
@Slf4j
@RestController
@RequestMapping("/common")
@ConfigurationProperties(prefix = "reggie.file")
@Tag(name = "通用接口")
@Data
public class CommonController {
    
    private String path;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    @Operation(summary = "文件上传")
    public Result<String> upload(@RequestBody MultipartFile file) {
        log.info("文件上传：{}", file.getOriginalFilename());
        
        // 创建文件存储目录
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        
        // 获取原始文件后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        
        // 生成新文件名
        String fileName = UUID.randomUUID() + suffix;
        
        try {
            // 将临时文件转存到指定位置
            file.transferTo(new File(path, fileName));
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败", e);
        }
        
        return Result.success(fileName);
    }

    /**
     * 文件下载
     */
    @GetMapping("/download")
    @Operation(summary = "文件下载")
    public void download(HttpServletResponse response, @RequestParam String name) {
        log.info("文件下载：{}", name);
        
        // 设置响应的MIME类型
        if (name.contains(".jpg") || name.contains(".jpeg")) {
            response.setContentType("image/jpeg");
        } else if (name.contains(".png")) {
            response.setContentType("image/png");
        }
        
        // 通过流将文件返回给前端
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path, name)));
             BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            log.error("文件下载失败：{}", name, e);
            throw new RuntimeException("文件下载失败", e);
        }
    }

    /**
     * 文件删除
     */
    public void delete(String[] names) {
        log.info("删除文件：{}", (Object) names);
        for (String name : names) {
            File file = new File(path, name);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
