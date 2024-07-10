package xiaoxin.spzx.manager.service.Impl;

import cn.hutool.core.date.DateUtil;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xiaoxin.spzx.manager.config.MinioProperties;
import xiaoxin.spzx.manager.service.FileUploadService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName: FileUploadServiceImpl
 * Package: xiaoxin.spzx.manager.service.Impl
 * Description:
 *
 * @Author xiaoxin
 * @Create 2024/7/9 19:31
 * @Version 1.0
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private MinioProperties minioProperties;
    @Override
    public String fileUpload(MultipartFile file) {
        try {
            //创建一个Minio的客户端对象
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpoint())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                    .build();
            //判断桶是否存在
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build());
            if(!found){
                //如果不存在，创建一个新的桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build());
            }else{
                //如果存在打印信息
                System.out.println("Bucket "+minioProperties.getBucket()+" already exists");
            }

            //设置存储对象名称
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = dateDir+"/"+uuid+file.getOriginalFilename();

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .stream(file.getInputStream(),file.getSize(),-1)
                    .object(fileName)
                    .build();
            minioClient.putObject(putObjectArgs);
            return minioProperties.getEndpoint()+"/"+minioProperties.getBucket()+"/"+fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
