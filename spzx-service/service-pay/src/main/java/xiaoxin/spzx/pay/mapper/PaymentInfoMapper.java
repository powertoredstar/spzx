package xiaoxin.spzx.pay.mapper;

import org.apache.ibatis.annotations.Mapper;
import xiaoxin.spzx.model.entity.pay.PaymentInfo;

/**
 * ClassName: PaymentInfoMapper
 * Package: xiaoxin.spzx.pay.mapper
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 13:56
 * @Version 1.0
 */
@Mapper
public interface PaymentInfoMapper {
    void save(PaymentInfo paymentInfo);

    PaymentInfo getByOrderNo(String orderNo);

    void updateById(PaymentInfo paymentInfo);

}
