package kopo.poly.service;


import kopo.poly.config.EncryptUtil;
import kopo.poly.dto.MemberDto;
import kopo.poly.entity.Member;
import kopo.poly.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional // 트랜젝션설정 : 성공을하면 그대로 적용 실패하면 롤백
@RequiredArgsConstructor // final 또는 @NonNull 명령어가 붙으면 객체를 자동 붙혀줍니다.

public class MemberService  {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {

        log.info(this.getClass().getName() + ".saveMember Start!");

        validateDuplicateMember(member);

        log.info(this.getClass().getName() + ".saveMember End!");

        return memberRepository.save(member); // 데이터베이스에 저장을 하라는 명령

    }
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public int LoginCheck(Member member) {

        log.info(this.getClass().getName() + ".LoginCheck Start!");

        int res = 0;

        Member findMember = memberRepository.findByEmailAndPassword(member.getEmail(), member.getPassword());

        if(findMember != null) {
            res = 1;
        } else {
            throw new IllegalStateException("아이디 또는 비밀번호가 틀렸습니다.");
        }

        log.info(this.getClass().getName() + ".LoginCheck End!");

        return res;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(email);
//
//        if(member == null){
//            throw new UsernameNotFoundException(email);
//        }
//
//        return User.builder().username(member.getEmail())
//                .password(member.getPassword())
//                .build();
//    }


}
