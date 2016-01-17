package pe.kr.ddakker.ezframework.web.support.util.multipart;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import pe.kr.ddakker.ezframework.web.vo.Vo;

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
