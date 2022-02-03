package modification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class index {
	public static void main(String[] args) throws IOException {
		ReadPropertiesFile read = new ReadPropertiesFile();
	//	System.out.println(read.checkExistenceOfCsv());
		boolean checkBlankEntry = false;

		if (read.checkExistenceOfCsv()) {
			System.out.println("csv file is correct");

			if (CheckForFileFormat.checkColumnsFormatOfInputFile()) {

				BufferedReader reader = new BufferedReader(new FileReader(read.file));
				String oneEntryFromInput = reader.readLine();
				PrintWriter writer = new PrintWriter(new FileWriter("solutionModify.csv"));
				int numberOfEntryInInputDataSet = 0;
				int numberOfColumnInInputCSVFile = 4;
				int invalidEntry=0;
				while (oneEntryFromInput != null) {

					if (numberOfEntryInInputDataSet == 0) {
						writer.print("Product-Name" + "," + "Product-CostPrice" + "," + "Product-SalesTax" + ","
								+ "Product-SalesTaxAmount" + "," + "Product-FinalPrice" + "," + "Countries");
						writer.println();
					} else {

						String productDetails[] = oneEntryFromInput.split(",");
						for (int count = 0; count < productDetails.length; count++) {
							if (productDetails[count].isEmpty() || productDetails[count] == null) {

								checkBlankEntry = true;

							}

						}

						if (productDetails.length < numberOfColumnInInputCSVFile || checkBlankEntry) {
							checkBlankEntry = false;
							numberOfEntryInInputDataSet++;
							invalidEntry++;
							oneEntryFromInput = reader.readLine();
							continue;

						}
						try {
							
							float productCostPrice = Float.parseFloat(productDetails[1].trim());
							float salesTax = Float.parseFloat(productDetails[2].trim());
							float productSalesTaxAmount = ((productCostPrice * salesTax) / 100);
							if (productCostPrice <= 0) // validation negative entries
							{
								productCostPrice = 0;
								productSalesTaxAmount = 0;
							}

							writer.print(productDetails[0] + "," + productDetails[1] + "," + productDetails[2] + ","
									+ productSalesTaxAmount + "," + (productCostPrice + productSalesTaxAmount) + ",");
							writer.print(productDetails[3]);
							writer.println();
						} catch (NumberFormatException e) {
							invalidEntry++;
						}
					}

					oneEntryFromInput = reader.readLine();
					numberOfEntryInInputDataSet++;

				}
				reader.close();
				writer.flush();
				writer.close();
				System.out.println("total entries in input csv file=" + (numberOfEntryInInputDataSet-1));
				System.out.println("total invalid entries="+ invalidEntry);
				System.out.println("total VALID entries="+((numberOfEntryInInputDataSet-1)- invalidEntry));

			}
		}

	}
}
