/**
 * 
 */
package com.myrestfileupload.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.myrestfileupload.model.FileModel;


// TODO: Auto-generated Javadoc
/**
 * The Class FileRowMapper.
 */
public class FileRowMapper implements RowMapper<FileModel>{

	 /* (non-Javadoc)
 	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
 	 */
 	@Override
    public FileModel mapRow(ResultSet rs, int rowNum) throws SQLException {
 		FileModel file = new FileModel();
 		
 		file.setProjectNumber(rs.getInt("projectNumber"));
 		file.setProjectName(rs.getString("projectName"));
 		file.setFileName(rs.getString("fileName"));
 		
        return file;
    }

}
