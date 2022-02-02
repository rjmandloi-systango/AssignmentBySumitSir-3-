package modification;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ValidationOfData {

	public void validateDataSet(String[] dataSet, int numberOfEntryInInputDataSet) throws IOException {
		if (dataSet.length < 4 && dataSet.length > 4 || numberOfEntryInInputDataSet == 0) {
			System.out.println("numberOfEntryInInputDataSet="+numberOfEntryInInputDataSet);
			System.out.println("inside validateDataSet() in <4 >4 && 0 condition");
			writeFunction(dataSet, numberOfEntryInInputDataSet);
		} else
		{
			
			writeFunction(dataSet, numberOfEntryInInputDataSet);

		}
	}

	public void writeFunction(String[] dataSet, int numberOfEntryInInputDataSet) throws IOException {

		PrintWriter writer = new PrintWriter(new FileWriter("solutionModify.csv"));
		if (numberOfEntryInInputDataSet == 0) {
			writer.print("Product-Name" + "," + "Product-CostPrice" + "," + "Product-SalesTax" + ","
					+ "Product-SalesTaxAmount" + "," + "Product-FinalPrice" + "," + "Countries");
			writer.println();
			writer.flush();
		}
		for (int count = 0; count < dataSet.length; count++) // checking for null entries in input
		// dataset and replace it with 0
		{
			if (dataSet[count].isEmpty() || dataSet[count] == null) {
				dataSet[count] = "0";
			}

		}
		float productCostPrice = Float.parseFloat(dataSet[1].trim());
		float salesTax = Float.parseFloat(dataSet[2].trim());
		float productSalesTaxAmount = ((productCostPrice * salesTax) / 100);
		if (productCostPrice <= 0) // validation for blank entry and negative entries
		{
			productCostPrice = 0;
			productSalesTaxAmount = 0;
		}

		try {

			writer.print(dataSet[0] + "," + dataSet[1] + "," + dataSet[2] + "," + productSalesTaxAmount + ","
					+ (productCostPrice + productSalesTaxAmount) + ",");
			writer.print(dataSet[3]);
		} catch (Exception e) {
			writer.print("undefined");
		}
		writer.println();
		writer.flush();
	}

}
