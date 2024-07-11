package xiaoxin.spzx.feign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xiaoxin.spzx.model.entity.product.ProductSku;

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
}
