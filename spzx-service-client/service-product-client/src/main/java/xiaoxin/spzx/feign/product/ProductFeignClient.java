package xiaoxin.spzx.feign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xiaoxin.spzx.model.dto.product.SkuSaleDto;
import xiaoxin.spzx.model.entity.product.ProductSku;

import java.util.List;

/**
 * ClassName: ProductFeignClient
 * Package: xiaoxin.spzx.feign.product
 * Description:     定义针对service-product微服务的远程调用接口
 *
 * @Author xiaoxin
 * @Create 2024/7/11 8:53
 * @Version 1.0
 */
@FeignClient(value = "service-product")
public interface ProductFeignClient {
    @GetMapping("/api/product/getBySkuId/{skuId}")
    public abstract ProductSku getBySkuId(@PathVariable("skuId") Long skuId);
    @PostMapping("/api/product/updateSkuSaleNum")
    public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList);
}
