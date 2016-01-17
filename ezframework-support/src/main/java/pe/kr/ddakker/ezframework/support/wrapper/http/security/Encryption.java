package pe.kr.ddakker.ezframework.support.wrapper.http.security;

public abstract class Encryption {

	private boolean isInputEncode = false;
	private boolean isOutputDecode = false;

	private Encryption() {}
	public Encryption(boolean isInputEncode, boolean isOutputDecode) {
		this.isInputEncode = isInputEncode;
		this.isOutputDecode = isOutputDecode;
	}

	public boolean isInputEncode() {
		return isInputEncode;
	}
	public boolean isOutputDecode() {
		return isOutputDecode;
	}
	public abstract String encode(String value);
	public abstract String decode(String value);
}
