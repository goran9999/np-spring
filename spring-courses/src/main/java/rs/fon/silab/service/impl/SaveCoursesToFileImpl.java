package rs.fon.silab.service.impl;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import rs.fon.silab.dto.CourseDto;

@Component
public class SaveCoursesToFileImpl {

	
	void saveCoursesToFile(List<CourseDto> courseDtos) {

			Gson gson=new GsonBuilder().setPrettyPrinting().create();
			JsonArray jarr=new JsonArray();
			courseDtos.forEach(course->{
				String jsonObject=gson.toJson(course);
				jarr.add(jsonObject);
				});
			
			try(PrintWriter pw=new PrintWriter(new FileWriter("courses.json"))) {
				pw.write(gson.toJson(jarr));
			} catch (Exception e) {
				System.out.println(e);
			}
		
	}
	
}