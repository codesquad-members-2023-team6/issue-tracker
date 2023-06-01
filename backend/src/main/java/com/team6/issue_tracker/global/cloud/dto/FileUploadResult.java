package com.team6.issue_tracker.global.cloud.dto;

import com.team6.issue_tracker.global.cloud.domain.Result;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResult {
    private String originKey;
    private String uploadKey;
    private String path;
    private Result result;

    public static FileUploadResult success(String originKey, String uploadKey, String path) {
        return new FileUploadResult(originKey, uploadKey, path, Result.SUCCESS);
    }

    public static FileUploadResult fail(String originKey) {
        return new FileUploadResult(originKey, null , null, Result.FAIL);
    }
}
