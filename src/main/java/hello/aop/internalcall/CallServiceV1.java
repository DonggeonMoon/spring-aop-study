package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CallServiceV1 {
    private CallServiceV1 callServiceV1;

    //@Autowired 순환 참조 문제 발생함 - 스프링 버전에 따른 문제인 듯
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter{}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        //callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
