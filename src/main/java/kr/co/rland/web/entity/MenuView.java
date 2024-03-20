package kr.co.rland.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuView {

    private long id;
    private String korName;
    private String engName;
    private int price;
    private String img;
    private int likeCount;
    private Date regDate;

}