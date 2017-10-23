package com.myrestfileupload.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myrestfileupload.model.FileModel;
import com.myrestfileupload.service.FileStorageService;

// TODO: Auto-generated Javadoc
/**
 * The Class SpringBootRestUploadController.
 */
@RestController
public class SpringBootRestUploadController {

	/** The logger. */
	private final Logger logger = LoggerFactory
			.getLogger(SpringBootRestUploadController.class);

	/** The file storage service. */
	@Autowired
	FileStorageService fileStorageService;

	/**
	 * Upload file.
	 *
	 * @param uploadfile the uploadfile
	 * @return the response entity
	 */
	// Single file upload
	@PostMapping("/api/upload")
	public ResponseEntity<?> uploadFile(
			@RequestParam("file") MultipartFile uploadfile) {

		logger.debug("Single file upload!");

		if (uploadfile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {
			fileStorageService.saveUploadedFiles(Arrays.asList(uploadfile));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - "
				+ uploadfile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);

	}

	/**
	 * Upload file multi.
	 *
	 * @param projectName the project name
	 * @param projectNumber the project number
	 * @param uploadfiles the uploadfiles
	 * @return the response entity
	 */
	// Multiple file upload
	@PostMapping("/api/upload/multi")
	public ResponseEntity<?> uploadFileMulti(
			@RequestParam("projectName") String projectName,
			@RequestParam("projectNumber") Integer projectNumber,
			@RequestParam("fileName") String fileName,
			@RequestParam("files") MultipartFile[] uploadfiles) {

		logger.debug("Multiple file upload!");

		String uploadedFileName = Arrays.stream(uploadfiles)
				.map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x))
				.collect(Collectors.joining(" , "));
		
		FileModel file = new FileModel();
		file.setFileName(fileName);
		file.setProjectName(projectName);
		file.setProjectNumber(projectNumber);

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {
			fileStorageService.create(file);
			fileStorageService.saveUploadedFiles(Arrays.asList(uploadfiles));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(
				"Successfully uploaded - " + uploadedFileName, HttpStatus.OK);

	}

	/**
	 * Multi upload file model.
	 *
	 * @param model the model
	 * @return the response entity
	 */
	// maps html form to a Model
	@PostMapping("/api/upload/multi/model")
	public ResponseEntity<?> multiUploadFileModel(
			@ModelAttribute FileModel model) {

		logger.debug("Multiple file upload! With UploadModel");

		try {

			fileStorageService
					.saveUploadedFiles(Arrays.asList(model.getFiles()));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	}
	
	
	/**
	 * Find all files.
	 *
	 * @return the list
	 */
	@GetMapping("/files")
    List<FileModel> findAllFiles() {
		List<FileModel> files = fileStorageService.findAllFiles();
        return files;
    }
	

}
