package com.st.signservice.service;

import java.util.List;
import com.st.signservice.entity.FileType;

public interface FileTypeService {
	
	List<FileType> getFileType();
	
	FileType findById(Integer fileTypeId);
	
	FileType findByCode(String code);

}
