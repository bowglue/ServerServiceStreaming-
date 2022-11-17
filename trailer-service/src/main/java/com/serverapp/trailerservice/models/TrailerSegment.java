package com.serverapp.trailerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "trailer_segment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TrailerSegment {
    @Id
    private String segment_name;
    private String segment_type;
    private Long segment_size;
    private byte[] segment_data;

    public String getSegment_name() {
        return segment_name;
    }

    public void setSegment_name(String segment_name) {
        this.segment_name = segment_name;
    }

    public String getSegment_type() {
        return segment_type;
    }

    public void setSegment_type(String segment_type) {
        this.segment_type = segment_type;
    }

    public Long getSegment_size() {
        return segment_size;
    }

    public void setSegment_size(Long segment_size) {
        this.segment_size = segment_size;
    }

    public byte[] getSegment_data() {
        return segment_data;
    }

    public void setSegment_data(byte[] segment_data) {
        this.segment_data = segment_data;
    }
}
