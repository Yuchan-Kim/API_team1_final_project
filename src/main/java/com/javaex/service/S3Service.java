package com.javaex.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

    private final AmazonS3 s3Client;

    @Autowired
    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("파일이 없습니다.");
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try {
            // S3에 파일 업로드
            s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);

            // 업로드된 파일의 S3 URL 반환
            return s3Client.getUrl(bucketName, fileName).toString();

        } catch (AmazonServiceException e) {
            // AWS 서비스에서 발생한 예외 처리
            throw new IllegalStateException("AWS 서비스에서 문제가 발생했습니다: " + e.getMessage(), e);
        } catch (SdkClientException e) {
            // SDK에서 발생한 클라이언트 예외 처리
            throw new IllegalStateException("AWS 클라이언트에서 문제가 발생했습니다: " + e.getMessage(), e);
        }
    }
}