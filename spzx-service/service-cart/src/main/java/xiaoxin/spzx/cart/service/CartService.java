package xiaoxin.spzx.cart.service;

import xiaoxin.spzx.model.entity.h5.CartInfo;

import java.util.List;

/**
 * ClassName: CartService
 * Package: xiaoxin.spzx.cart.service
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 9:17
 * @Version 1.0
 */
public interface CartService {
    void addToCart(Long skuId, Integer skuNum);

    List<CartInfo> getCartList();

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isChecked);

    void clearCart();

    List<CartInfo> getAllChecked();

    void deleteChecked();

}
