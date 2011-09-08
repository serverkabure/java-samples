package jp.canetrash.sample.wicket.dto;

import java.io.Serializable;
import java.util.Date;

public class UploadedFile implements Serializable {
	private static final long serialVersionUID = -8674963716718837356L;
	
	private String name;
	private long size;
	private Date lastModified;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
