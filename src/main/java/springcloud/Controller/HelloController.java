package springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 10326 on 2019/4/24.
 * Config Client向Config Server提交REST请求后，Config Server将访问GIT服务器，并将取得的配置项hello的值返回给client.
 */
@RefreshScope
@RestController
public class HelloController {

    @Value("${hello}")
    String hello;

    @RequestMapping(value = "/hello")
    public String hello(){
        return hello;
    }
}
