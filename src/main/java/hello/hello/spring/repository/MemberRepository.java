package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //null 값을 반환할 때 optional로 감쌈.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
