package kopo.poly.entity;

import kopo.poly.config.CmmUtil;
import kopo.poly.dto.BorderDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "border")
@Builder
@NoArgsConstructor
@DynamicUpdate
public class Border {

    @Id
    @Column(name = "border_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String memberEmail;  //회원이메일

    @NotNull
    private String title;

    @NotNull
    private String content;

    Border(Long id, @NotNull String memberEmail, @NotNull String title, @NotNull String content) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.title = title;
        this.content = content;
    }

    public static Border toEntity (BorderDto borderdto){

        String memberEmail = CmmUtil.nvl(borderdto.getMemberEmail());
        String title = CmmUtil.nvl(borderdto.getTitle());
        String content = CmmUtil.nvl(borderdto.getContent());
        Border border = Border.builder().
                memberEmail(memberEmail).title(title).content(content)
                .build();

        return border;
    }

}
