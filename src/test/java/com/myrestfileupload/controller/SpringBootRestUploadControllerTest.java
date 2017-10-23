package com.myrestfileupload.controller;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.myrestfileupload.model.FileModel;
import com.myrestfileupload.service.FileStorageService;

// TODO: Auto-generated Javadoc
/**
 * The Class SpringBootRestUploadControllerTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = SpringBootRestUploadController.class, secure = false)
public class SpringBootRestUploadControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The file storage service. */
	@MockBean
	private FileStorageService fileStorageService;

	/** The mock file. */
	// FileModel mockFile = new FileModel("1020", "Aenta Platinum XXX",
	// "xxx.doc", new MultipartFile);

	/*
	 * MockMultipartFile firstFile = new MockMultipartFile("data",
	 * "filename.txt", "text/plain", "some xml".getBytes()); MockMultipartFile
	 * secondFile = new MockMultipartFile("data", "other-file-name.data",
	 * "text/plain", "some other type".getBytes()); MockMultipartFile jsonFile =
	 * new MockMultipartFile("json", "", "application/json",
	 * "{\"json\": \"someValue\"}".getBytes());
	 */

	/**
	 * Find all files.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void findAllFiles() throws Exception {

		/*
		 * insert into file_uploads(projectNumber, projectName, fileName) values
		 * (1001, 'Blue Cross Gold', 'Policy1.doc'); insert into
		 * file_uploads(projectNumber, projectName, fileName) values (1002,
		 * 'Blue Cross Silver', 'Policy2.doc'); insert into
		 * file_uploads(projectNumber, projectName, fileName) values (1003,
		 * 'Blue Cross Bronz', 'Policy3.doc');
		 */
		List<FileModel> files = Arrays.asList(new FileModel(1001,
				"Blue Cross Gold", "Policy1.doc"), new FileModel(1002,
				"Blue Cross Silver", "Policy2.doc"), new FileModel(1002,
				"Blue Cross Bronz", "Policy3.doc"));

		when(fileStorageService.findAllFiles()).thenReturn(files);

		mockMvc.perform(get("/files"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].projectNumber", is(1001)))
				.andExpect(jsonPath("$[0].projectName", is("Blue Cross Gold")))
				.andExpect(jsonPath("$[0].fileName", is("Policy1.doc")));
	}

}
