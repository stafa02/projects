package genericSerDeser.driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import genericSerDeser.fileOperations.FileProcessor;
import genericSerDeser.strategy.SerStrategy;
import genericSerDeser.util.DPML;
import genericSerDeser.util.Logger;
import genericSerDeser.util.PopulateObjects;

public class Driver {

	public static void main(String[] args) {
		try {
			validateArgs(args);
			FileProcessor fp = new FileProcessor(new BufferedReader(new FileReader(args[0])));
			PopulateObjects pop = new PopulateObjects();
			String line;
			while ((line = fp.readLineFromFile()) != null) {
				if (line.length() == 0 || "\n".equals(line)) {
					continue;
				}
				List<String> lines = new ArrayList<String>();
				line = line.substring(1, line.length() - 1);
				line.trim();
				lines.add(line);
				while (!(line = fp.readLineFromFile()).contains("/fqn")) {
					if (line.length() == 0 || "\n".equals(line)) {
						continue;
					}
					line = line.substring(1, line.length() - 1);
					line.trim();
					lines.add(line);
				}
				pop.populate(lines);
			}
			fp.close();
			fp = new FileProcessor(args[1]);
			SerStrategy strategy = new DPML();
			for (Object obj : pop.getobjects()) {
				String serializedObject = strategy.serialize(obj);
				fp.writeLineToFile(serializedObject);
				Logger.writeMessage(serializedObject, Logger.DebugLevel.RESULT);
			}
			fp.close();
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
		if (args.length == 3) {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			if (br.readLine() == null) {
				System.err.println("Input file is empty\n");
				System.exit(1);
			}
			br.close();
			int debugLevel = Integer.parseInt(args[2]);
			Logger.setDebugValue(debugLevel);
			if (debugLevel < 0 || debugLevel > 4) {
				System.err.println("Invalid value for debug level, expected range between 0-4");
				System.exit(1);
			}
		} else {
			System.err.println("Invalid number of arguments. Expected 3 arguments");
			System.exit(1);
		}
	}
}
