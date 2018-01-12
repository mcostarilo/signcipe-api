package com.st.signservice.entity;

import java.io.Serializable;

public class CipePerson implements Serializable
{
	private static final long serialVersionUID = 3989861955256432981L;

	private String name;

	private String lastName;

	private String sex;

	private String docType;

	private String docNumber;

	private String base64Picture;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getDocType()
	{
		return docType;
	}

	public void setDocType(String docType)
	{
		this.docType = docType;
	}

	public String getDocNumber()
	{
		return docNumber;
	}

	public void setDocNumber(String docNumber)
	{
		this.docNumber = docNumber;
	}

	public String getBase64Picture()
	{
		return base64Picture;
	}

	public void setBase64Picture(String base64Picture)
	{
		this.base64Picture = base64Picture;
	}
}