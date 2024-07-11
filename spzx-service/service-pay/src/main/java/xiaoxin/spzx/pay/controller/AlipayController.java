package xiaoxin.spzx.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;
import xiaoxin.spzx.pay.properties.AlipayProperties;
import xiaoxin.spzx.pay.service.AlipayService;
import xiaoxin.spzx.pay.service.PaymentInfoService;

import java.util.Map;

/**
 * ClassName: AlipayController
 * Package: xiaoxin.spzx.pay.controller
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/11 14:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/order/alipay")
public class AlipayController {
    private static final Logger log = LoggerFactory.getLogger(AlipayController.class);
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private AlipayProperties alipayProperties;
    @Autowired
    private PaymentInfoService paymentInfoService;
    @Operation(summary = "支付宝下单")
    @GetMapping("submitAlipay/{orderNo}")
    public Result<String> submitAlipay(@Parameter(name = "orderNo",description = "订单号",required = true) @PathVariable("orderNo") String orderNo) {
        String form = alipayService.submitAlipay(orderNo);
        return Result.build(form, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "支付宝异步回调")
    @PostMapping("callback/notify")
    public String alipayNotify(@RequestParam Map<String,String> paramMap, HttpServletRequest request) {
        log.info("AlipayController .... alipayNotify 执行了");
        //调用sdk验证签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(paramMap,alipayProperties.getAlipayPublicKey(),AlipayProperties.charset,AlipayProperties.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //交易状态
        String tradeStatus = paramMap.get("trade_status");
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            if("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)){
                //正常支付成功,更新交易记录状态
                paymentInfoService.updatePaymentStatus(paramMap,2);
                return "success";
            }
        }else {
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            return "failure";
        }
        return "failure";
    }

}
