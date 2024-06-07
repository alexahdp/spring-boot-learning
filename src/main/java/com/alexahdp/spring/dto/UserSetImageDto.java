package com.alexahdp.spring.dto;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
public class UserSetImageDto {
    MultipartFile image;
}
