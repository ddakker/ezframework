package org.ezdevgroup.ezframework.web.support.util.multipart;

import java.io.File;

import org.ezdevgroup.ezframework.web.vo.Vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileItem extends Vo {
	private String pkValue;
	private String originalName;
	private String physicalName;
	private File file;
	
	public FileItem() {
	}
	public FileItem(String pkValue, String originalName, String physicalName, File file) {
		this.pkValue = pkValue;
		this.originalName = originalName;
		this.physicalName = physicalName;
		this.file = file;
	}
}
