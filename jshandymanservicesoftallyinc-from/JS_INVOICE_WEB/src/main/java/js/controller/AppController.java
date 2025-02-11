package js.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/data")
public class AppController {

	@GetMapping("/infos")
	public String infos() {
		return AppController.class.getPackage().getImplementationVersion();
	} 

	@GetMapping("/info")
	public AppPojo info() {
		
		AppPojo appPojo = new AppPojo();
		appPojo.setVersion(AppController.class.getPackage().getImplementationVersion());
		// appPojo.setVersion(this.pomRead().getVersion());
		return appPojo;
	}
	
	public Model pomRead() {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = null;
        try {
			// META-INF\maven\com.jshandyman.service\handyman

			// String direction = "/META-INF/maven/com.jshandyman.service/handyman/pom.xml";
			// model = reader.read(new FileReader("pom.xml"));

			if ((new File("pom.xml")).exists())
			model = reader.read(new FileReader("pom.xml"));
		  else
			model = reader.read(new InputStreamReader(
				AppController.class.getResourceAsStream("/META-INF/maven/com.jshandyman.service/handyman/pom.xml")));

        	// InputStream schemaIS = this.getClass().getResourceAsStream("/META-INF/maven/com.jshandyman.service/handyman/pom.xml");
			// model = reader.read(schemaIS);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return model;
	}
}
