package org.example.project.user;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final List<String> ALLOWED_IMAGE_TYPES = new ArrayList<>(Arrays.asList(
            "image/jpeg", "image/jpg"
    ));
    private final String ALLOWED_PDF_TYPE = "application/pdf";



    @Override
    public String storeFile(MultipartFile multipartFile, String fileType, String userId) {
        if (fileType.equals("photo"))
            return handlePhoto(multipartFile, userId);
        if (fileType.equals("pdf"))
            return handlePdf(multipartFile, userId);
        return "";
    }

    @Override
    public void retrieveFile(String path) {

    }

    private String handlePhoto(MultipartFile multipartFile, String userId){
        if (!checkFileValidation(multipartFile.getContentType(), "photo"))
            return "";

        return saveFile(multipartFile, "images", userId);

    }

    private String handlePdf(MultipartFile multipartFile, String userId) {
        if (!checkFileValidation(multipartFile.getContentType(), "pdf"))
            return "";
        return saveFile(multipartFile, "pdfs", userId);
    }

    private boolean checkFileValidation(String contentType, String fileType) {
        if (fileType.equals("photo"))
            return ALLOWED_IMAGE_TYPES.contains(contentType);
        else
            return ALLOWED_PDF_TYPE.equals(contentType);
    }


    private String saveFile(MultipartFile file, String directoryName, String userId) {
        String dir = "uploads/" + directoryName + "/" + userId + "/";
        Path path = Paths.get(dir).normalize();
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Something went wrong", e);
            }
        }
        String extension = file.getContentType().
                substring(file.getContentType().indexOf("/") + 1);
        String fileName = UUID.randomUUID().toString() + "." + extension;
        Path targetLocation = path.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong", e);
        }
        return fileName;
    }

}
