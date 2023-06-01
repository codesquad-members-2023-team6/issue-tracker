package com.team6.issue_tracker.global.cloud.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.team6.issue_tracker.global.cloud.domain.Directory;
import com.team6.issue_tracker.global.cloud.dto.FileUploadResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageHostService {

    @Value("${aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    public ImageHostService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public FileUploadResult upload(MultipartFile file, Directory directory) {

        String originFileName = file.getOriginalFilename();
        String uploadFileName = generateFileName(file, directory.getPrefix());
        String url ="";
        try {
            url = putObjectToS3(file, uploadFileName);
        } catch (IOException e) {
            return FileUploadResult.fail(originFileName);
        }

        return FileUploadResult.success(originFileName, uploadFileName, url);
    }

    private String putObjectToS3(MultipartFile file, String fileName) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(),objectMetadata));
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private String generateFileName(MultipartFile file, String prefix) {
        return String.format("%s%s-%s", prefix, UUID.randomUUID(), file.getName());
    }

}
