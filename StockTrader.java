import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("sbux_2007_2016.txt"));
        String symbol = in.next();               
        int n = in.nextInt();

        // TODO: create the three arrays, each with n elements
		int[] years = new int[n];
		int[] months = new int[n];
		double[] prices = new double[n];
        // TODO: call loadData, then printData
        loadData(in, years, months, prices);
        printData(years, months, prices);
        int lowest = indexOfLowest(prices);
        int highest = indexOfHighest(prices);
		System.out.println(symbol + " lowest: $" + prices[lowest] + " on " + monthName(months[lowest]) + " " + years[lowest]);
		System.out.println(symbol + " highest: $" + prices[highest] + " on " + monthName(months[highest]) + " " + years[highest]);
		double result = simulate(years, months, prices);
		System.out.printf("Final value: $%,.2f%n", result);
    }
    
	public static void loadData(Scanner in, int[] years, int[] months, double[] prices) {
		 for (int i = 0; i < years.length; i++) {
			years[i] = in.nextInt();
			months[i] = in.nextInt();
			prices[i] = in.nextDouble();
		 }
	}
	public static String monthName(int month) {
		String[] monthsNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		return monthsNames[month-1];
	}
	public static void printData(int[] years, int[] months, double[] prices) {
		for (int i = 0; i < months.length; i++) {
			System.out.println(" " + monthName(months[i]) + "  " + years[i] + " : " + "$" + prices[i]);
			monthName(months[i]);
		}
	}
	public static int indexOfLowest(double[] prices) {
    int min = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < prices[min]) {
				min = i;
			}
		}
		return min;
	}

	public static int indexOfHighest(double[] prices) {
    int max = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[max]) {
				max = i;
			}
		}
		return max;
	}
	
	public static final double START_CASH = 10000.0;
	public static final double FEE = 10.0;

	public static double simulate(int[] years, int[] months, double[] prices) {
    double cash = START_CASH;
    int shares = 0;
    double boughtAt = 0.0;

    for (int i = 0; i < prices.length; i++) {
        double[] history = Arrays.copyOf(prices, i + 1);
        String action = decide(history, shares > 0, boughtAt);

        if (action.equals("BUY") && shares == 0) {
            int canBuy = (int) ((cash - FEE) / prices[i]);
            if (canBuy > 0) {
                shares = canBuy;
                cash = cash - shares * prices[i] - FEE;
                boughtAt = prices[i];
                System.out.printf("%s %d: BUY %d shares at $%.2f%n",
                        monthName(months[i]), years[i], shares, prices[i]);
            }
        } else if (action.equals("SELL") && shares > 0) {
            cash = cash + shares * prices[i] - FEE;
            System.out.printf("%s %d: SELL %d shares at $%.2f%n",
                    monthName(months[i]), years[i], shares, prices[i]);
            shares = 0;
            boughtAt = 0.0;
        }
    }
    return cash + shares * prices[prices.length - 1];
}
	public static String decide(double[] history, boolean holding, double boughtAt) {
		return buyAndHold(history, holding, boughtAt);   // change this line to switch strategies
		
	}
	public static String buyAndHold(double[] history, boolean holding, double boughtAt) {
		return "BUY";
	}
	public static String dipBuyer(double[] history, boolean holding, double boughtAt) {
		if (holding == false && history.length >= 3 && history[history.length-1] < history[history.length-2] && history[history.length-2] < history[history.length-3]) {
			return "BUY";
		} else if (holding == true && history[history.length-1] >= boughtAt * 1.1) {
			return "SELL";
		} else {
			return "HOLD";
		}
	}
	public static String movingAverage(double[] history, boolean holding, double boughtAt) {
		if (history.length < 12) {
			return "HOLD";
		} 
		double average = 0;
		for (int i = history.length - 12; i < 12; i++) {
			average += history[i];
		}
		average /= 12;
		if (holding == false && history[history.length - 1] > average) {
			return "BUY";
		} else if (holding == true && history[history.length - 1] < average) {
			return "SELL";
		} else {
			return "HOLD";
		}
	}
	
}

