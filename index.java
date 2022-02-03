package modification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class index {
	public static void main(String[] args) throws IOException {
		ReadPropertiesFile read = new ReadPropertiesFile();
		System.out.println(read.checkExistenceOfCsv());
		boolean checkBlankEntry=false;

		if (read.checkExistenceOfCsv()) {
			System.out.println("csv file is correct");

			if (CheckForFileFormat.checkColumnsFormatOfInputFile())
			{
				
				BufferedReader reader = new BufferedReader(new FileReader(read.file));
				String oneEntryFromInput = reader.readLine(); // read line by line data from csv file in string format
				PrintWriter writer = new PrintWriter(new FileWriter("solutionModify.csv")); // print writer object is used to
																						// write in destination file

				int numberOfEntryInInputDataSet = 0;
				//oneEntryFromInput = reader.readLine();
				while (oneEntryFromInput != null) {

					if (numberOfEntryInInputDataSet == 0) {
						writer.print("Product-Name" + "," + "Product-CostPrice" + "," + "Product-SalesTax" + ","
								+ "Product-SalesTaxAmount" + "," + "Product-FinalPrice" + "," + "Countries");
						writer.println();
					} else {
						String productDetails[] = oneEntryFromInput.split(",");
						//System.out.println(productDetails.length);

						
						if (productDetails.length <4) {
							oneEntryFromInput = reader.readLine();
							continue;

						}
						
						for (int count = 0; count < productDetails.length; count++) // checking for null entries in input
																					// dataset and replace it with 0
						{
							if (productDetails[count].isEmpty() || productDetails[count] == null) {
								productDetails[count] = "0";
								checkBlankEntry=true;
                                 																
							}

						}
						if(checkBlankEntry)
						{
							checkBlankEntry=false;
							oneEntryFromInput = reader.readLine();
							continue;
						}
						float productCostPrice = Float.parseFloat(productDetails[1].trim());
						float salesTax = Float.parseFloat(productDetails[2].trim());
						float productSalesTaxAmount = ((productCostPrice * salesTax) / 100);
						if (productCostPrice <= 0) // validation for blank entry and negative entries
						{
							productCostPrice = 0;
							productSalesTaxAmount = 0;
						}
						
							

								writer.print(productDetails[0] + "," + productDetails[1] + "," + productDetails[2] + ","
										+ productSalesTaxAmount + "," + (productCostPrice + productSalesTaxAmount) + ",");
								writer.print(productDetails[3]);
							
								writer.print("undefined");
						
							writer.println();
						}
						oneEntryFromInput = reader.readLine();
						numberOfEntryInInputDataSet++;
					
				}
				reader.close();
				writer.flush();
				writer.close();
				System.out.println("numberOfEntryInOutputDataSet= "+ (numberOfEntryInInputDataSet - 1));
			
			
				
				
				
			} // column format
		} // existence

	} // main method
} // class
