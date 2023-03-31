package com.VTSangaliya.common;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class DownloadFile {

	public ResponseEntity<Resource> DownloadPdf(byte[] fileByte, String fileName) {

		ByteArrayResource resource = new ByteArrayResource(fileByte);
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName);
		return ResponseEntity.ok().headers(header).contentLength(fileByte.length)
				.contentType(MediaType.parseMediaType("application/pdf")).body(resource);
	}

	public ResponseEntity<Resource> DownloadFile(byte[] fileByte, String fileName,String type) {

		ByteArrayResource resource = new ByteArrayResource(fileByte);
		HttpHeaders header = new HttpHeaders();
		//header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=test.zip");
		if(type.equalsIgnoreCase("pdf"))
		{
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf");
		return ResponseEntity.ok().headers(header).contentLength(fileByte.length)
				.contentType(MediaType.parseMediaType("application/pdf")).body(resource);
		}
		else if(type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("jpeg"))
		{
			//parseMediaType("image/jpeg")

			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".jpg");
			return ResponseEntity.ok().headers(header).contentLength(fileByte.length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
			}
		else if(type.equalsIgnoreCase("gz"))
		{
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".gz");
			return ResponseEntity.ok().headers(header).contentLength(fileByte.length)
					.contentType(MediaType.parseMediaType("application/zip")).body(resource);
			}
		else
		{
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".log");
			return ResponseEntity.ok().headers(header).contentLength(fileByte.length)
					.contentType(MediaType.parseMediaType("text/plain")).body(resource);
			}
	}

	public Set<String> readFilesFromFolder(String dir) throws IOException {

	    Set<String> fileList = new HashSet<>();
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
	        for (Path path : stream) {
	            if (!Files.isDirectory(path)) {
	                fileList.add(path.getFileName()
	                    .toString());
	            }
	        }
	    }
	    return fileList;
	}

}

