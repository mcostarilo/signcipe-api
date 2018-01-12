package com.st.signservice.service;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.st.signservice.config.ConstantConfig;
import com.st.signservice.entity.Document;
import com.st.signservice.entity.FileType;
import com.st.signservice.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Resource 
	DocumentRepository documentRepository;

	@Resource
	private ConfigService configService;

	@Resource
	private FileTypeService fileTypeService;
	
	@Override
	public List<Document> getDocument() {
		return documentRepository.findAll();
	}

	@Override
	public Document findById(Integer documentId) {
		return documentRepository.findById(documentId);
	}

	@Override
	public void deleteById(Integer documentId) {
		documentRepository.delete(documentId);
	}
	
	public void saveDocument(Document document){
		documentRepository.save(document);
	}
	
	@Override
	public Document getDocumentByPathName(String pathName) {
		return documentRepository.findByPathName(pathName);
	}
}