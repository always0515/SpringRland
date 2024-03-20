package kr.co.rland.web.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long id;
    private String username;
    private String pwd;
    private String email;
    private Instant regDate;

}