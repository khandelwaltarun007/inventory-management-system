package io.javalabs.ims.util;
public class Utility {

	public static boolean isEmpty(String str) {
		if(str==null || str.isEmpty())
			return true;
		return false;
	}
	
	public static boolean isNotEmpty(String str) {
		if(str!=null && !str.isEmpty())
			return true;
		return false;
	}
	
	public static boolean isEmpty(Object obj) {
		if(obj==null)
			return true;
		return false;
	}
	
}