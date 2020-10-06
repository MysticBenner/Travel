package com.cb.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Place {
    private String id;
    private String name;
    private String picpath;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date hottime;
    private Double hottickets;
    private Double dimtickets;
    private String placedes;
    private String provinceid;
}
