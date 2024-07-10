package xiaoxin.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileUploadService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 19:30
 * @Version 1.0
 */
public interface FileUploadService {
    String fileUpload(MultipartFile file);
}
