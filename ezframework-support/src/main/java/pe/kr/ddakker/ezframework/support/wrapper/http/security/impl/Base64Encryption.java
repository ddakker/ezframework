package pe.kr.ddakker.ezframework.support.wrapper.http.security.impl;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import pe.kr.ddakker.ezframework.support.wrapper.http.security.Encryption;

public class Base64Encryption extends Encryption {
	private String charsetName = "UTF-8";

	public Base64Encryption(boolean isInputEncode, boolean isOutputDecode) {
		super(isInputEncode, isOutputDecode);
	}
	
	public Base64Encryption(boolean isInputEncode, boolean isOutputDecode, String charsetName) {
		super(isInputEncode, isOutputDecode);
		this.charsetName = charsetName;
	}

	@Override
	public String encode(String value) {
		if( StringUtils.isNotEmpty(value) ){
			return Base64.encode(value.getBytes());
		}
		else{
			return "";
		}
	}

	@Override
	public String decode(String value) {
		if (StringUtils.isNotEmpty(value)) {
			String strResult = null;
			try {
				byte decoded[] = Base64.decode(value.trim());
				strResult = new String(decoded, charsetName);
			} catch (UnsupportedEncodingException e) {
				strResult = "";
			}

			return strResult;

		} else {
			return "";
		}
	}
}
