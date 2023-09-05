package hello.aop.member;

public class MemberServiceImpl implements MemberService {
    @Override
    public String hello(String string) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
