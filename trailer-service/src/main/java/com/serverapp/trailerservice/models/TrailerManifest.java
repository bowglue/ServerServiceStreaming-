package com.serverapp.trailerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "trailer_manifest")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TrailerManifest {
    @Id
    private String video_name;
    private String manifest_name;
    private String manifest_type;
    private byte[] manifest_data;

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getManifest_name() {
        return manifest_name;
    }

    public void setManifest_name(String manifest_name) {
        this.manifest_name = manifest_name;
    }

    public String getManifest_type() {
        return manifest_type;
    }

    public void setManifest_type(String manifest_type) {
        this.manifest_type = manifest_type;
    }

    public byte[] getManifest_data() {
        return manifest_data;
    }

    public void setManifest_data(byte[] manifest_data) {
        this.manifest_data = manifest_data;
    }
}
