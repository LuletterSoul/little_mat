package njust.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;


@ComponentScan(basePackages = {"njust"}) // 扫描该包路径下的所有spring组件
@EntityScan({"njust.domain"})
@SpringBootApplication
@RestController
public class LittleMatApplication
{
//    @RequestMapping("/")
//    public String home() {
//        return "Hello Docker World";
//    }
    public static void main(String[] args)
    {
        SpringApplication.run(LittleMatApplication.class, args);
    }
}
