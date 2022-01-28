package hello.hello.spring.service;

import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean   //스프링 빈을 등록할거다.
    public MemberService memberService(){
        return new MemberService(memberRepository()); //MemberService랑 memberRepository를  빈에 저장.
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
