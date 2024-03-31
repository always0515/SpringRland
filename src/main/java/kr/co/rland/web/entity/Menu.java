package kr.co.rland.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private long id;
    private String korName;
    private String engName;
    private int price;
    private String img;
    // private boolean like;
    // private Date regDate;
}