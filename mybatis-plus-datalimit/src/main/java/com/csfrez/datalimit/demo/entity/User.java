package com.csfrez.datalimit.demo.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private Long departmentId;
    private String username;
    private String mobile;

}
