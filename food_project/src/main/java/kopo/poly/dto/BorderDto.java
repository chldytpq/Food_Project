package kopo.poly.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class BorderDto {

    private Long id;

    private String memberEmail; //회원 이메일

    private String title;

    private String content;

    BorderDto(Long id, @NotNull String memberEmail, @NotNull String title, @NotNull String content) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.title = title;
        this.content = content;
    }

}
