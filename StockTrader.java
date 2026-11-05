import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("zigzag.txt"));
        String symbol = in.next();               
        int n = in.nextInt();

        // TODO: create the three arrays, each with n elements
		int[] years = new int[n];
		int[] months = new int[n];
		double[] prices = new double[n];
        // TODO: call loadData, then printData
        loadData(in, years, months, prices);
        printData(years, months, prices);

    }
    
	public static void loadData(Scanner in, int[] years, int[] months, double[] prices) {
		 for (int i = 0; i < years.length; i++) {
			years[i] = in.nextInt();
			months[i] = in.nextInt();
			prices[i] = in.nextDouble();
		 }
	}
	public static String monthName(int month) {
		String[] monthsNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
		return monthsNames[month-1];
	}
	public static void printData(int[] years, int[] months, double[] prices) {
		for (int i = 0; i < months.length; i++) {
			System.out.println(" " + monthName(months[i]) + "  " + years[i] + " : " + "$" + prices[i]);
			monthName(months[i]);
		}
	}
	public static int indexOfLowest(double[] prices) {
		
	}
	public static int indexOfHighest(double[] prices) {
		
	}

}
