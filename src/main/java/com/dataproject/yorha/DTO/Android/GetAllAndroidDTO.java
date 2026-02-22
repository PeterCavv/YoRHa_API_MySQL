package com.dataproject.yorha.DTO.Android;

import com.dataproject.yorha.model.Android;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetAllAndroidDTO implements Serializable {
    private Long id;
    private String name;
    private String status;
    private String type;

    public GetAllAndroidDTO(Android android) {
        this.id = android.getId();
        this.name = android.getName();
        this.status = android.getStatus().getName();
        this.type = android.getType().getName();
    }

}
