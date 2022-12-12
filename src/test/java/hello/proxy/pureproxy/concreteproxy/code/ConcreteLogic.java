package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteLogic {
    public String opeartion() {
        log.info("ConcreteLogic 실행");
        return "data";
    }
}
