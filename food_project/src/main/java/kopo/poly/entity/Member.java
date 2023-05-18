package kopo.poly.entity;

import kopo.poly.config.CmmUtil;
import kopo.poly.dto.MemberDto;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@DynamicUpdate
@Builder

public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    Member(Long id, @NotNull String email, @NotNull String password, @NotNull String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static Member joinToEntity(MemberDto memberdto){

        String email = CmmUtil.nvl(memberdto.getEmail());
        String password = CmmUtil.nvl(memberdto.getPassword());
        String name = CmmUtil.nvl(memberdto.getName());
        Member member = Member.builder()
                .email(email).password(password).name(name)
                .build();

        return member;
    }

    public static Member loginToEntity(MemberDto memberdto){

        String email = CmmUtil.nvl(memberdto.getEmail());
        String password = CmmUtil.nvl(memberdto.getPassword());
        Member member = Member.builder()
                .email(email).password(password)
                .build();

        return member;
    }


}
