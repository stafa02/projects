package registrationScheduler.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to read/write a specific file
 *
 */
public class FileProcessor {
	private BufferedReader br;
	private BufferedWriter bw;

	/**
	 * @param file
	 * @throws IOException
	 */
	public FileProcessor(File file, boolean write) throws IOException {
		if (!write) {
			br = new BufferedReader(new FileReader(file));
		}
		if (write) {
			bw = new BufferedWriter(new FileWriter(file));
		}
	}

	/**
	 * @return String line read from file
	 * @throws IOException
	 */
	public String readLine() throws IOException {
		return br.readLine();
	}

	/**
	 * 
	 * @param line
	 *            to be written to file
	 * @throws IOException
	 */
	public void write(String line) throws IOException {
		bw.write(line);
	}

	/**
	 * Close stream
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		if (br != null) {
			br.close();
		}
		if (bw != null) {
			bw.close();
		}
	}
	
	@Override
	public String toString(){
		return "Used for file handling";
	}
}