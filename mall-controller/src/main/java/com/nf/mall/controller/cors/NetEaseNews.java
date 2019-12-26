package com.nf.mall.controller.cors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data

@JsonIgnoreProperties({"specialID"})
public class NetEaseNews {
    private List<ImgextraEntity> imgextra;
    private String liveInfo;
    private String docid;
    private String source;
    private String title;
    private int priority;
    private String url;
    private String skipURL;
    private int commentCount;
    private String imgsrc3gtype;
    private String stitle;
    private String digest;
    private String skipType;
    private String photosetID;
    private String imgsrc;
    private String ptime;
    private String modelmode;
    private Integer hasImg;
}
