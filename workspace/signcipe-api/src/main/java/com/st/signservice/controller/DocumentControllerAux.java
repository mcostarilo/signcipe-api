package com.st.signservice.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.st.signservice.config.ConstantConfig;
import com.st.signservice.entity.Config;
import com.st.signservice.entity.Document;
import com.st.signservice.security.jwt.entity.Operator;
import com.st.signservice.service.ConfigService;
import com.st.signservice.service.DocumentService;
import com.st.signservice.service.FileTypeService;

@Service
public class DocumentControllerAux {
	
    @Resource
	DocumentService documentService;
    
    @Resource
    FileTypeService fileTypeService;
    
	@Resource
	ConfigService configService;
	
	public Document saveDocument(Integer id, String originalName, String pathName, String mimeType, String fileCode, Operator createdBy) throws IOException {

		Document document = null;
		
		List<Document> documentList = null;
		
		if (documentList == null || documentList.isEmpty()) {
			/*Grabamos la referencia del archivo subido en la tabla documentos */
		    document = new Document();
		    
		    document.setName(originalName);
		    document.setStatus(ConstantConfig.DOCUMENT_STATUS_ACT);
		    document.setMimeType(mimeType);
			document.setPathName(pathName);
			document.setFileTypeId(fileTypeService.findByCode(fileCode).getId());
			documentService.saveDocument(document);
		} 		
		return document;
	}
	
	public String getPathFile(String path) {
		Config config = configService.findByCode(path); // .findByCode("pathFile");
		return config.getValue(); 
	}
	
	public Document getDocument(String pathName) {
		
	    Document document = new Document();
		document = documentService.getDocumentByPathName(pathName);
		
		return document;
	}
}