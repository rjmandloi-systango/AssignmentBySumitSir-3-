package modification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckForFileFormat {
	public static boolean checkColumnsFormatOfInputFile() throws IOException {
		ReadPropertiesFile read = new ReadPropertiesFile();
		read.checkExistenceOfCsv();
		
		
		BufferedReader reader = new BufferedReader(new FileReader(read.file));
		String oneEntryFromInput = reader.readLine();
		String productDetails[] = oneEntryFromInput.split(",");
		if (productDetails[0].contains("ProductName") && productDetails[1].contains("ProductCostPrice") && productDetails[2].contains("ProductSalesTaxPersentage") && productDetails[3].contains("Country")) {

			System.out.println("given format is correct");
			
			reader.close();
			return true;
			
		}
		else {
		System.out.println("file columns are not as expected");
			System.out.println("please format the columns as follows");
			System.out.println("ProductName  ,ProductCostPrice,ProductSalesTax-Persentage,Country");
			reader.close();
			return false;
		}
		
		
	}
	

}
