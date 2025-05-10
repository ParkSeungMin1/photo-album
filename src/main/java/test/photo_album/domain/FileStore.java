package test.photo_album.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files; // Files.createDirectories 사용을 위해 필요
import java.nio.file.Path;   // Path 사용을 위해 필요
import java.nio.file.Paths; // Paths.get 사용을 위해 필요
import java.util.UUID;

@Component
public class FileStore {

    private static final String BASE_UPLOAD_PATH = "/tmp/uploads/"; // 예시 경로 (리눅스/macOS) 또는 "C:/temp/uploads/" (Windows)

    public String storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null) {
            originalFilename = "untitled"; // 원본 파일 이름이 없는 경우 기본값 설정
        }

        String ext = "";
        int pos = originalFilename.lastIndexOf(".");
        if (pos != -1) {
            ext = originalFilename.substring(pos + 1);
        }

        String storedFilename = UUID.randomUUID().toString();
        if (!ext.isEmpty()) {
            storedFilename = storedFilename + "." + ext;
        }

        Path filePath = Paths.get(BASE_UPLOAD_PATH, storedFilename);
        Files.createDirectories(filePath.getParent());
        multipartFile.transferTo(filePath.toFile());

        // 저장된 파일 이름 반환
        return storedFilename;
    }

    public Path getFilePath(String storedFilename) {
        // 하드코딩된 기본 경로를 사용하여 Path를 구성합니다.
        return Paths.get(BASE_UPLOAD_PATH, storedFilename);
    }

    // Note: 이 버전은 로직을 간소화했지만, 실제 환경에서는
    // 파일 경로를 설정 파일에서 관리하고, 예외 처리를 더 상세하게 하는 것이 좋습니다.
}
