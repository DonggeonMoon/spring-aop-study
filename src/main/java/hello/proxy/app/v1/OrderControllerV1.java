package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
// 스프링은 @Controller 또는 @RequestMapping이 있어야 스프링 컨트롤러로 인식
// 이 애너테이션은 인터페이스에 사용해도된다.
@ResponseBody
// HTTP 메시지컨버터를 사용해서 응답한다.
// 이 애너테이션은 인터페이스에 사용해도된다.
public interface OrderControllerV1 {
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
