package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ArgTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void args() {
        assertThat(
                pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();
        assertThat(
                pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();
        assertThat(
                pointcut("args()").matches(helloMethod, MemberServiceImpl.class)
        ).isFalse();
        assertThat(
                pointcut("args(..)").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();
        assertThat(
                pointcut("args(*)").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();
        assertThat(
                pointcut("args(String, ..)").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();
    }

    // execution는 파라미터 타입이 정확하게 매칭돼야 함. 부모 타입 불가.
    @Test
    @DisplayName("execution은 메서드의 시그니처로 판단(정적), args는 런타임에 전달된 인수를 판단(동적)")
    void argsVsExecution() {
        //args
        assertThat(
                pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();
        assertThat(
                pointcut("args(java.io.Serializable)").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();
        assertThat(
                pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();

        //execution
        assertThat(
                pointcut("execution(* *(String))").matches(helloMethod, MemberServiceImpl.class)
        ).isTrue();
        assertThat(
                pointcut("execution(* *(java.io.Serializable))").matches(helloMethod, MemberServiceImpl.class)
        ).isFalse();
        assertThat(
                pointcut("execution(* *(Object))").matches(helloMethod, MemberServiceImpl.class)
        ).isFalse();
    }
}
