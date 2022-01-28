package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //sequence : 0,1,2 이런 키 값을 생성해줌.
    @Override
    //저장
    public Member save(Member member) {
        member.setId(++sequence);   //sequence를 하나씩 증가하면서 id 생성
        store.put(member.getId(), member);  //store에 id 저장.->map에 저장됨
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //Optional.ofNullable()로 id가 null이어도 감쌀 수 있음 -> client에서 무언가 할 수 있음.
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream().filter(member -> member.getName().equals(name)).findAny(); //member.getName이 파라미터로 넘어온 name과 같은지 확인-> 같은 경우에만 filtering 됨. 그리고 찾고 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());    //store에 있는 values는 = 위에 있는 멤버들.
    }

    //test할 때 저장된 repository를 지워줌.
    public void clearStore(){
        store.clear();
    }
}
