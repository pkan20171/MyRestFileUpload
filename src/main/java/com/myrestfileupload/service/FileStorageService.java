package com.myrestfileupload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myrestfileupload.model.FileModel;
import com.myrestfileupload.utils.FileRowMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class FileStorageService.
 */
@Service
public class FileStorageService {

	/** The upload folder. */
	private static String UPLOAD_FOLDER = "C://Temp//upload-dir//";

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Find all files.
	 *
	 * @return the list
	 */
	@Transactional(readOnly = true)
	public List<FileModel> findAllFiles() {
		return jdbcTemplate.query("select * from FILE_UPLOADS",
				new FileRowMapper());
	}

	/**
	 * Find file by id.
	 *
	 * @param id the id
	 * @return the file model
	 */
	@Transactional(readOnly = true)
	public FileModel findFileById(int id) {
		return jdbcTemplate.queryForObject(
				"select * from file_uploads where projectNumber=?",
				new Object[] { id }, new FileRowMapper());
	}

	/**
	 * Creates the.
	 *
	 * @param file the file
	 * @return the file model
	 */
	public FileModel create(final FileModel file) {
		final String sql = "insert into file_uploads(projectNumber, projectName, fileName) values(?,?,?)";

		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, file.getProjectNumber());
				ps.setString(2, file.getProjectName());
				ps.setString(3, file.getFileName());
				return ps;
			}
		}, holder);

		/*
		int newFileId = holder.getKey().intValue();
		file.setProjectNumber(newFileId);
		*/
		
		return file;
	}

	/**
	 * Save uploaded files.
	 *
	 * @param files
	 *            the files
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void saveUploadedFiles(List<MultipartFile> files) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

		}

	}

}
