package com.projek.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Messagemt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countid;
    @Column(length = 50, nullable = false)
    private String messageid;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer campaignid;
    @Column(length = 200, nullable = false, columnDefinition = "varchar(200) default ' ' COMMENT 'application_keys.app_key'" )
    private String userid;
    @Column(length = 20, nullable = false)
    private String original;
    @Column(length = 20, nullable = false)
    private String sendto;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0 COMMENT '0/1-7bit, 2-flash, 4-wap, 8-8bit, 16-UCS2'")
    private Integer messagetype;
    @Lob
    @Column(name = "message", length = 512, nullable = false)
    private String message;
    @Lob
    @Column(name = "mediaurl", length = 512)
    private String mediaurl;

    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer ston;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer snpi;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer dton;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer dnpi;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer dcs;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer udhl;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer destport;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer origport;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer refnum;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer segseq;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer totseg;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer ispush;

    @Lob
    @Column(name = "replyto", length = 512)
    private String replyto;
    @Column(length = 20, nullable = false)
    private String keyword;

    @Column(name = "receivedate", nullable = false)
    private LocalDateTime receivedate;
    @Column(name = "sentdate")
    private LocalDateTime sentdate;
    @Column(length = 50, nullable = false)
    private String sentstatus;
    @Column(name = "delivereddate")
    private LocalDateTime delivereddate;
    @Column(length = 50, nullable = false)
    private String deliveredstatus;

    @Column(length = 11, nullable = false, columnDefinition = "integer default 0 COMMENT '00 = new , 10 = call ,12 = result'")
    private Integer status;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 5")
    private Integer priority;
    @Column(name = "startdate")
    private LocalDateTime startdate;
    @Column(name = "enddate")
    private LocalDateTime enddate;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer oprid;
    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
    private Boolean fromcp;
    @Column(length = 30, nullable = false, columnDefinition = "varchar(30) default '*'")
    private String fromchannel;
    @Column(length = 30, nullable = false, columnDefinition = "varchar(30) default '*'")
    private String tochannel;
    @Column(length = 50)
    private String thirdid;
    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0 COMMENT 'jenis sms. 0 regular, 1 premium'")
    private Boolean jenis;
    @Column(length = 11, nullable = false, columnDefinition = "integer default 0")
    private Integer smsprice;

    @Column(length = 20)
    private String prefix_code;
    @Column(name = "date_10")
    private LocalDateTime date_10;
    @Column(name = "date_20")
    private LocalDateTime date_20;
    @Column(name = "date_30")
    private LocalDateTime date_30;
    @Column(name = "date_90")
    private LocalDateTime date_90;
}
