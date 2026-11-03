import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();               
        int n = in.nextInt();

        // TODO: create the three arrays, each with n elements
		int[] years = new int[n];
		int[] months = new int[n];
		double[] prices = new double[n];
        // TODO: call loadData, then printData
        loadData(in, years, months, prices);

    }
    
	public static void loadData(Scanner in, int[] years, int[] months, double[] prices) {
		 
	}
	public static String monthName(int month) {
		
	}
	public static void printData(int[] years, int[] months, double[] prices) {
		
	}
}
