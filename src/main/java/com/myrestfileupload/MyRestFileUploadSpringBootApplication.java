package com.myrestfileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Auto-generated Javadoc
/**
 * The Class MyRestFileUploadSpringBootApplication.
 */
@SpringBootApplication
public class MyRestFileUploadSpringBootApplication {

	/** The max upload size in mb. */
	private int maxUploadSizeInMb = 1 * 1024 * 1024; 

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MyRestFileUploadSpringBootApplication.class, args);
	}
}
