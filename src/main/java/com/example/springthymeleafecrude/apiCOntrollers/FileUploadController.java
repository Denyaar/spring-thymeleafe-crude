/**
 * Created by tendaimupezeni for fileUploadanddownload
 * User: tendaimupezeni
 * Date: 14/7/2023
 * Time: 11:40
 */

package com.example.springthymeleafecrude.apiCOntrollers;


import com.example.springthymeleafecrude.Utils.FileDownloadUtil;
import com.example.springthymeleafecrude.Utils.FileUploadUtil;
import com.example.springthymeleafecrude.model.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse>  uploadFile(
            @RequestParam("file") MultipartFile multipartFile
    ) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        Long size  = multipartFile.getSize();
        String fileCode =  FileUploadUtil.saveFIle(fileName,multipartFile);
        FileUploadResponse response  = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/"+ fileCode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode){
        FileDownloadUtil downloadUtil = new FileDownloadUtil();

        Resource resource  = null;
        try{
            resource = (Resource) downloadUtil.getFileResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        if(resource == null){
            return new ResponseEntity<>("File Not Found", HttpStatus.NO_CONTENT);
        }
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

}
