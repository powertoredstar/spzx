package xiaoxin.spzx.manager.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoxin.spzx.manager.mapper.ProductDetailsMapper;
import xiaoxin.spzx.manager.mapper.ProductMapper;
import xiaoxin.spzx.manager.mapper.ProductSkuMapper;
import xiaoxin.spzx.manager.service.ProductService;
import xiaoxin.spzx.model.dto.product.ProductDto;
import xiaoxin.spzx.model.entity.product.Product;
import xiaoxin.spzx.model.entity.product.ProductDetails;
import xiaoxin.spzx.model.entity.product.ProductSku;

import java.util.List;

/**
 * ClassName: ProductServiceImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/10 11:43
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page, limit);
        List<Product> productList = productMapper.findByPage(productDto);
        return new PageInfo<>(productList);

    }

    @Override
    public void save(Product product) {
        //保存商品数据
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);
        //保存商品sku数据
        List<ProductSku> productSkusList =product.getProductSkuList();
        for(int i = 0,size = productSkusList.size();i < size;i++){
            //获取ProductSku对象
            ProductSku productSku = productSkusList.get(i);
            productSku.setSkuCode(product.getId()+"_"+i);
            //设置商品id
            productSku.setProductId(product.getId());
            productSku.setSaleNum(0);
            productSku.setStatus(0);
            productSkuMapper.save(productSku);
        }
        //保存商品详情数据
        ProductDetails productDetails =new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }

    @Override
    public Product getById(Long id) {
        //1.根据id查询商品数据
        Product product = productMapper.selectById(id);
        //2.根据商品id查询sku数据
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(id);
        product.setProductSkuList(productSkuList);
        //3.根据商品id查询商品详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        product.setDetailsImageUrls(productDetails.getImageUrls());
        //返回数据
        return product;
    }

    @Override
    public void updateById(Product product) {
        //修改基本商品数据
        productMapper.updateById(product);
        //修改商品的sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });
        //修改商品的详情数据
        ProductDetails productDetails = productDetailsMapper.selectByProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);
    }

    @Override
    public void deleteById(Long id) {
        //1.根据id删除商品基本信息
        productMapper.deleteById(id);
        //2.根据id删除商品的sku数据
        productSkuMapper.deleteByProductId(id);
        //3.根据商品id删除商品的详情数据
        productDetailsMapper.deleteByProductId(id);
    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus == 1){
            product.setAuditStatus(1);
            product.setAuditMessage("审核通过");
        }else{
            product.setAuditStatus(-1);
            product.setAuditMessage("不通过");
        }
        productMapper.updateById(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status == 1){
            product.setStatus(1);
        }else{
            product.setStatus(-1);
        }
        productMapper.updateById(product);
    }
}
