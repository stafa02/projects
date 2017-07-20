
package genericSerDeser.fileOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

	private BufferedReader in;
	private BufferedWriter out;

	/**
	 * 
	 * @param bufReaderIn
	 *            reader instance for file
	 */
	public FileProcessor(BufferedReader bufReaderIn) {
		//Logger.writeMessage("In FileProcessor, BufferedReader constructor\n", Logger.DebugLevel.CONSTRUCTOR);
		in = bufReaderIn;
	}

	/**
	 * 
	 * @param outFileNameIn
	 *            output file name
	 * @throws IOException
	 */
	public FileProcessor(String outFileNameIn) throws IOException {
		//Logger.writeMessage("In FileProcessor, String Parameter constructor\n", Logger.DebugLevel.CONSTRUCTOR);
		File file = new File(outFileNameIn);
		file.createNewFile();
		out = new BufferedWriter(new FileWriter(file));
	}

	/**
	 * @return String line read from file
	 */
	public String readLineFromFile() throws IOException {
		return in.readLine();
	}

	/**
	 * 
	 * @param line
	 *            to be written to file
	 * @throws IOException
	 */
	public void writeLineToFile(String line) throws IOException {
		out.write(line);
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		if (in != null) {
			in.close();
		}
		if (out != null) {
			out.close();
		}
	}

	@Override
	public String toString() {
		return "utility to handle files";
	}
}