package kr.co.rland.web.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Menu {
    private long id;
    private String korName;
    private String engName;
    private int price;
    private String img;
    // private boolean like;
    // private Date regDate;
}