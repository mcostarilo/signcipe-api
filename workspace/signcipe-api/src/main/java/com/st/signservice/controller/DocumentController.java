package com.st.signservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.st.signservice.config.ConstantConfig;
import com.st.signservice.entity.Document;
import com.st.signservice.service.DocumentService;

@RestController
@RequestMapping("/secure")
public class DocumentController {

	@Resource
	private DocumentService documentService;
	
//	@RequestMapping(value = "/document/proc/{procedureId}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
//	public List<Document>  getByProcId(@PathVariable Integer procedureId) {
//		return documentService.findByProcId(procedureId) ;
//	}

	@RequestMapping(value = "/document", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
	public List<Document> getDocument() {
		return documentService.getDocument();
	}

	@RequestMapping(value = "/document/{documentId}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
	public Document getById(@PathVariable Integer documentId) {
		return documentService.findById(documentId) ;
	}

	@RequestMapping(value = "/document/{documentId}", method = RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE })
	public Map<String, String> deleteById(@PathVariable Integer documentId) {
		documentService.deleteById(documentId);
		Map<String, String> response = new HashMap<String, String>();
		response.put("result", "OK");
		return response;
	}

	@RequestMapping(value = "/document/upload", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Document> handleFileUpload(@RequestPart(value = "uploadFile") MultipartFile file, @RequestPart(value = "document") Document document) {
		UUID uuid = UUID.randomUUID();
		String uuidFileName = uuid.toString();		
		document.setPathName(uuidFileName);		  
		document.setStatus(ConstantConfig.DOCUMENT_STATUS_ACT);
        return new ResponseEntity<Document>(document, HttpStatus.OK);
	}
}