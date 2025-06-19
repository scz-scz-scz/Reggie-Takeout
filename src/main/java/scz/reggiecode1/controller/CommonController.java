package scz.reggiecode1.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import scz.reggiecode1.common.Result;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@ConfigurationProperties(prefix = "reggie.file")
@Data
public class CommonController {
    private String path;

    //文件上传
    @PostMapping("/upload")
    public Result<String> upload(@RequestBody MultipartFile file){  //形参名要和前端的文件变量名一致才能成功接收
        File filePath=new File(path);
        //如果路径不存在，就会自动创建
        if (!filePath.exists())
            filePath.mkdirs();
        //获取原始文件后缀
        String originalFilename=file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName=UUID.randomUUID()+suffix;
        try {
            //MultipartFile类型数据是临时数据，在请求完成后就会删除，所以需要转存到指定位置
            file.transferTo(new File(path,fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success(fileName);
    }

    //文件下载
    @GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam String name){
        //设置响应的MIME类型
        if (name.contains(".jpg")||name.contains(".jpeg"))
            response.setContentType("image/jpeg");
        else if (name.contains(".png"))
            response.setContentType("image/png");
        //通过流将文件返回给前端
        try {
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(new File(path,name)));
            BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
            byte[] bytes=new byte[1024];
            int len;
            while ((len=bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
                bos.flush();   //写完要刷新
            }
            bos.close();
            bis.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //文件删除
    public void delete(String[] names){
        for (String name : names) {
            new File(path,name).delete();
        }
    }
}
