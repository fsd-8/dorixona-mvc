package net.idrok.dorixona.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {

    public String saveFile(MultipartFile multipartFile);

    File getFile(String rasm);
}
