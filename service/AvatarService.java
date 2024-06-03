package pro.sku.SQL.service;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sku.SQL.model.Avatar;
import pro.sku.SQL.model.Student;
import pro.sku.SQL.repository.AvatarRepository;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    Logger logger = LoggerFactory.getLogger(AvatarService.class);


    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        logger.info("был вызван метод, чтобы загрузить аватар");
        Student student = studentService.findStudent(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);

        ) {
            bis.transferTo(bos);

        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(long id) {
        logger.info("был вызван метод, чтобы найти аватар");
        if (avatarRepository.findByStudentId(id) != null) {
            return avatarRepository.findByStudentId(id);
        }
        return new Avatar();
    }

    private String getExtensions(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public Collection<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("был вызван метод, чтобы получить все аватары из БД");
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        return avatarRepository.findAll( pageRequest).getContent();
    }
}