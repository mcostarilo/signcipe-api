package com.st.signservice.service;

import java.util.List;
import com.st.signservice.entity.Document;

public interface DocumentService {
	
	List<Document> getDocument();
	
	Document findById(Integer documentId);
	
	void deleteById(Integer documentId);
	
	public void saveDocument(Document document);

	public Document getDocumentByPathName(String pathName);
	
}
