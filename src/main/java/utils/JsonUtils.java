package utils;

import javax.enterprise.inject.Default;

import com.google.gson.Gson;

@Default
public class JsonUtils {

	private Gson gson = new Gson();
	
	public String toJson(Object obj) {
		return gson.toJson(obj);
	}
	
	public <T> T toObj(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
}
