package br.com.alelo.conf;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.io.Files;

import br.com.alelo.utils.FileKey;

@Component
public class FileSaver {

	@Autowired
	private AmazonS3 amazonS3;

	private static final String BUCKET_NAME = "bookwebangular-feliperoque";
	private String URL = "https://%s.s3.us-east-2.amazonaws.com/%s.%s";
	

	public String write(MultipartFile file, String name) {
		try {
			
			String type = Files.getFileExtension(file.getOriginalFilename());
			
			String key = FileKey.builder()
			.withFileExtension( type )
			.withFileName( name )
			.build();
			
			amazonS3.putObject(new PutObjectRequest(BUCKET_NAME, key, file.getInputStream(), null)
					.withCannedAcl(CannedAccessControlList.PublicReadWrite));
			
			return String.format(URL, BUCKET_NAME, name, type);
			
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
