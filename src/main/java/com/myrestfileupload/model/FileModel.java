package com.myrestfileupload.model;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

// TODO: Auto-generated Javadoc
/**
 * The Class FileModel.
 */
public class FileModel {

	/** The project number. */
	private Integer projectNumber;

	/** The project name. */
	private String projectName;

	/** The file name. */
	private String fileName;

	/** The files. */
	private MultipartFile[] files;
	

	/**
	 * Instantiates a new file model.
	 */
	public FileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new file model.
	 *
	 * @param projectNumber the project number
	 * @param projectName the project name
	 * @param fileName the file name
	 */
	public FileModel(Integer projectNumber, String projectName,
			String fileName) {
		super();
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.fileName = fileName;
	}

	/**
	 * Instantiates a new file model.
	 *
	 * @param projectNumber the project number
	 * @param projectName the project name
	 * @param fileName the file name
	 * @param files the files
	 */
	public FileModel(Integer projectNumber, String projectName,
			String fileName, MultipartFile[] files) {
		super();
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.fileName = fileName;
		this.files = files;
	}

	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the project name.
	 *
	 * @param projectName
	 *            the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gets the project number.
	 *
	 * @return the project number
	 */
	public Integer getProjectNumber() {
		return projectNumber;
	}

	/**
	 * Sets the project number.
	 *
	 * @param projectNumber the new project number
	 */
	public void setProjectNumber(Integer projectNumber) {
		this.projectNumber = projectNumber;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	public MultipartFile[] getFiles() {
		return files;
	}

	/**
	 * Sets the files.
	 *
	 * @param files
	 *            the new files
	 */
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "FileModel{" + "projectName='" + projectName + '\''
				+ ", projectNumber='" + projectNumber + '\'' + ", files="
				+ Arrays.toString(files) + '}';
	}

}
