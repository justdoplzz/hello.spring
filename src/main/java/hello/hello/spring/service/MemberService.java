package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //Service에서 직접 new 하지 않고 외부에서 MemberRepository를 넣어줌 => Dependency Injection 이라고 함.
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //회원가입
    public Long join(Member member) {

        validateDuplicateMember(member);   //중복회원 검증
        memberRepository.save(member);  //회원 저장
        return member.getId();
    }

    //중복회원 검증
    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원X
//         Optional<Member> result = memberRepository.findByName(member.getName());
//         result.ifPresent(m -> {
//             throw new IllegalStateException("이미 존재하는 회원입니다");
//         });
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {         //m에 어떤 값이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    //전체 회원 조회
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    //회원 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
