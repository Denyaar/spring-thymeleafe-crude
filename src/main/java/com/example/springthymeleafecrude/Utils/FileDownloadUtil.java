/**
 * Created by tendaimupezeni for fileUploadanddownload
 * User: tendaimupezeni
 * Date: 14/7/2023
 * Time: 12:13
 */

package com.example.springthymeleafecrude.Utils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadUtil {
    private Path foundFile;

    public  Resource getFileResource(String fileCode) throws IOException {


        Path  uploadDirect = Paths.get("Files-Upload");
        Files.list(uploadDirect).forEach(file ->{
            if(file.getFileName().toString().startsWith(fileCode)){
                foundFile = file;
                return;
            }
        });
        if(foundFile != null){
            return  new UrlResource(foundFile.toUri());
        }
        return null;
    }
}
