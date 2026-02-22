package com.dataproject.yorha.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DashboardDTO implements Serializable {
    private int activeAndroidCount;
    private int allAndroidCount;

    public DashboardDTO(int activeAndroidCount, int allAndroidCount) {
        this.activeAndroidCount = activeAndroidCount;
        this.allAndroidCount = allAndroidCount;
    }
}
