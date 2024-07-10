package xiaoxin.spzx.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xiaoxin.spzx.manager.service.FileUploadService;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

/**
 * ClassName: FileUploadController
 * Package: xiaoxin.spzx.manager.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 19:30
 * @Version 1.0
 */
@Tag(name = "上传文件接口")
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    @Operation(summary = "上传文件接口")
    @PostMapping(value = "/fileUpload")
    public Result<String> fileUpload(@RequestParam(value = "file") MultipartFile file) {
       String fileUrl =  fileUploadService.fileUpload(file);
       return Result.build(fileUrl, ResultCodeEnum.SUCCESS);
    }
}
