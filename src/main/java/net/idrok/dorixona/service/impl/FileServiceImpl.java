package net.idrok.dorixona.service.impl;

import net.idrok.dorixona.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class FileServiceImpl implements FileService {

    @Value("${app.file.root-folder}")
    private String rootFolder;

    @Override
    public String saveFile(MultipartFile multipartFile)  {
        try {
            File file = new File(rootFolder + File.separator + Instant.now().getEpochSecond()+ "_" + multipartFile.getOriginalFilename());
            InputStream in = multipartFile.getInputStream();
            FileOutputStream fos = new FileOutputStream(file);
            while(in.available()>0){
                fos.write(in.read());
            }
            fos.close();
            in.close();

            return file.getName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public File getFile(String rasm) {
        return new File(rootFolder+File.separator+rasm);
    }

    @PostConstruct
    public void createRootFolder(){
        File folder = new File(rootFolder);
        if(!folder.isDirectory()) folder.mkdirs();
    }

}
