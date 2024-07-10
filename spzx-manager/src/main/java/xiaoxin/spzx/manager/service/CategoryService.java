package xiaoxin.spzx.manager.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import xiaoxin.spzx.model.entity.product.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: xiaoxin.spzx.manager.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 9:12
 * @Version 1.0
 */
public interface CategoryService {
    List<Category> findByParentId(Long parentId);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
