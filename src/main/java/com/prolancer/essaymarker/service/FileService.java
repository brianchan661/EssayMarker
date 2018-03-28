package com.prolancer.essaymarker.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.prolancer.essaymarker.db.model.FileDetail;
import com.prolancer.essaymarker.db.repository.FileDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class FileService {

    @Autowired
    private AmazonS3 s3client;

    @Autowired
    private FileDetailRepository fileDetailRepository;

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${s3.endpoint}")
    private String endpoint;

    public void uploadFile(MultipartFile multipartFile) throws IllegalArgumentException {

        String fileUrl="";
        try {
            System.out.println("Uploading an object");
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpoint + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
            System.out.println("===================== Upload File - Done! =====================");
            //insert data to db
            FileDetail fileDetail = new FileDetail();
            fileDetail.setDownloadLink(fileUrl);

            fileDetailRepository.save(fileDetail);
        } catch (IOException exception) {
            System.out.println("exception when converting the file");
            throw new IllegalArgumentException();
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            throw new IllegalArgumentException();
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException: ");
            System.out.println("Error Message: " + ace.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    /**
     * prevent files with same name by adding date time and replace the empty space in file
     * @param multipartFile
     * @return
     */
    private String generateFileName(MultipartFile multipartFile) {
        return new Date().getTime() + "-" + multipartFile.getOriginalFilename().replace(" ", "_");
    }
}
