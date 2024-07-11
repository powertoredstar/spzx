package xiaoxin.spzx.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import xiaoxin.spzx.model.entity.user.UserInfo;
import xiaoxin.spzx.model.vo.common.Result;
import xiaoxin.spzx.model.vo.common.ResultCodeEnum;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ClassName: AuthGlobalFilter
 * Package: xiaoxin.spzx.gateway.filter
 * Description:
 *              全局Filter 统一处理会员登录
 * @Author xiaoxin
 * @Create 2024/7/11 7:56
 * @Version 1.0
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter , Ordered {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        log.info("path:{}",path);
        UserInfo userInfo = this.getUserInfo(request);

        //api接口,异步请求，校验用户必须登录
        if(antPathMatcher.match("/api/**/auth/**", path)) {
            if(userInfo == null) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response, ResultCodeEnum.LOGIN_AUTH);
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        Result result = Result.build(null,resultCodeEnum);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = response.bufferFactory().wrap(bits);
        //指定编码,否则浏览器中会出现中文乱码
        response.getHeaders().add("Content-Type", "application/json; charset=utf-8");
        return response.writeWith(Mono.just(wrap));
    }

    private UserInfo getUserInfo(ServerHttpRequest request) {
        String token = "";
        List<String> tokenList = request.getHeaders().get("token");
        if(tokenList != null && tokenList.size() > 0) {
            token = tokenList.get(0);
        }
        if(!StringUtils.isEmpty(token)){
            String userInfoJson = redisTemplate.opsForValue().get("user:spzx:" + token);
            if(StringUtils.isEmpty(userInfoJson)){
               return null;
            }else {
                return JSON.parseObject(userInfoJson,UserInfo.class);
            }
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
