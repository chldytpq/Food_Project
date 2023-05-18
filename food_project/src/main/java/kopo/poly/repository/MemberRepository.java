package kopo.poly.repository;

import kopo.poly.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);

    Member findByEmailAndPassword(String email, String password);


}
