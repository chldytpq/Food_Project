package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberDto {

    private String name;

    private String email;

    private String password;

}
