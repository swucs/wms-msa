package com.sycoldstorage.adminservice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Session에 담길 admin정보 이므로 Serializble을 구현해야 한다. (Session clustering)
 */
@Data
public class LoginAdmin implements Serializable {
    private String id;
    private String name;
}
