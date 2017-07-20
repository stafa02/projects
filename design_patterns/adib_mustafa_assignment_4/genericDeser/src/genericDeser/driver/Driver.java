package genericDeser.driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import genericDeser.fileOperations.FileProcessor;
import genericDeser.util.Logger;
import genericDeser.util.PopulateObjects;

public class Driver {

	public static void main(String[] args) {
		try {
			validateArgs(args);
			FileProcessor fp = new FileProcessor(new BufferedReader(new FileReader(args[0])));
			PopulateObjects pop = new PopulateObjects();
			String line;
			while ((line = fp.readLineFromFile()) != null) {
				if (line.length()==0 || "\n".equals(line)) {
					continue;
				}
				List<String> lines = new ArrayList<String>();
				line = line.substring(1, line.length() - 1);
				line.trim();
				lines.add(line);
				while (!(line = fp.readLineFromFile()).contains("/fqn")) {
					if (line.length()==0 || "\n".equals(line)) {
						continue;
					}
					line = line.substring(1, line.length() - 1);
					line.trim();
					lines.add(line);
				}
				pop.populate(lines);
			}
			fp.close();
			pop.printResults();
		} catch (IOException e) {
			System.err.println("erron in reading file");
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * 
	 * @param args
	 *            command line arguments
	 */
	private static void validateArgs(String args[]) throws IOException {
		if (args.length == 2) {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			if (br.readLine() == null) {
				System.err.println("Input file is empty\n");
				System.exit(1);
			}
			br.close();
			int debugLevel = Integer.parseInt(args[1]);
			Logger.setDebugValue(debugLevel);
			if (debugLevel < 0 || debugLevel > 4) {
				System.err.println("Invalid value for debug level, expected range between 0-4");
				System.exit(1);
			}
		} else {
			System.err.println("Invalid number of arguments. Expected 2 arguments");
			System.exit(1);
		}
	}
}
