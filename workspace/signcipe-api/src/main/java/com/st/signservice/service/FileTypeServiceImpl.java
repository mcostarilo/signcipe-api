package com.st.signservice.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.st.signservice.entity.FileType;
import com.st.signservice.repository.DocumentRepository;
import com.st.signservice.repository.FileTypeRepository;

@Service
public class FileTypeServiceImpl  implements FileTypeService {

	@Resource 
	FileTypeRepository fileTypeRepository;

	@Resource 
	DocumentRepository documentRepository;
	
	@Override
	public List<FileType> getFileType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileType findById(Integer fileTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileType findByCode(String code) {
		return fileTypeRepository.findByCode(code);
	}
}