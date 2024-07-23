package org.example.project.user;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile multipartFile, String fileType, String userId);
    void retrieveFile(String path);
}
