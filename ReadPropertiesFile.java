package modification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	File file = null;

	public Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream file = null;
		Properties propertiesFileObject = null;
		try {
			file = new FileInputStream(fileName);
			propertiesFileObject = new Properties();
			propertiesFileObject.load(file);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			file.close();
		}
		return propertiesFileObject;

	}

	public boolean checkExistenceOfCsv() throws IOException {
		Properties propertiesFileObject = readPropertiesFile(
				"/home/ubox83/Desktop/java_programs/programs/src/modification/credentials.properties");
		file = new File(propertiesFileObject.getProperty("filePath"));
		if (file.exists())
			return true;
		else {
			System.out.println("please change the path of your existing csv file in properties file  ");
			System.out.println("OR");
			System.out.println("create file with dataset and place it in the properties file ");
			return false;
		}
	}

}
