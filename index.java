package modification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class index {
	public static void main(String[] args) throws IOException {
		ReadPropertiesFile read = new ReadPropertiesFile();
		ValidationOfData validation = new ValidationOfData();
//		System.out.println(read.checkExistenceOfCsv());

		if (read.checkExistenceOfCsv()) {
//			System.out.println("csv file is correct");

			if (CheckForFileFormat.checkColumnsFormatOfInputFile()) {
//				System.out.println("csv format is correct");
				BufferedReader reader = new BufferedReader(new FileReader(read.file));
				String oneEntryFromInput = reader.readLine();
				// PrintWriter writer = new PrintWriter(new FileWriter("solutionModify.csv"));
				int numberOfEntryInInputDataSet = 0;
				oneEntryFromInput = reader.readLine();
				System.out.println(oneEntryFromInput);
				while (oneEntryFromInput != null) {

//					if (numberOfEntryInInputDataSet == 0) {
//						writer.print("Product-Name" + "," + "Product-CostPrice" + "," + "Product-SalesTax" + ","
//								+ "Product-SalesTaxAmount" + "," + "Product-FinalPrice" + "," + "Countries");
//						writer.println();
//
//					}
					String productDetails[] = oneEntryFromInput.split(",");
					validation.validateDataSet(productDetails, numberOfEntryInInputDataSet);
					// writer.flush();
					numberOfEntryInInputDataSet++;
					oneEntryFromInput = reader.readLine();

				} // entry not null
			} // column format
		} // existence

	} // main method
} // class
