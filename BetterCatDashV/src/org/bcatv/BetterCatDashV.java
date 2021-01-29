package org.bcatv;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BetterCatDashV {
	public static void main(String[] args) throws IOException {
		FileInputStream file = new FileInputStream(new File(args[0]));
		BufferedInputStream buf = new BufferedInputStream(file);
		int data;
		while ((data = buf.read()) != -1) {
			if (data >= 0 && data <= 31) {
				printHex((char) data);
			} else if (data >= 20 && data <= 126) {
				if (data == '\\')
					printAscii('\\');
				printAscii((char) data);
			} else {
				printHex((char) data);
			}
			printAscii(' '); // separator
		}
		buf.close();
	}

	private static final char[] hexdecimals = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	private static void printHex(char data) {
		if (data == '\n') {
			//printAscii('\\');
			printAscii('\n');
			return;
		} else if (data == 0x07) {  // bell
			printAscii('\\');
			printAscii('a');
			return;
		} else if (data == 0x08) {  // backspace
			printAscii('\\');
			printAscii('b');
			return;
		} else if (data == 0x1B) {  // escape
			printAscii('\\');
			printAscii('e');
			return;
		} else if (data == 0x0C) {  // formfeed
			printAscii('\\');
			printAscii('f');
			return;
		} else if (data == 0x0D) {  // carriage return
			printAscii('\\');
			printAscii('a');
			return;
		} else if (data == 0x09) {  // horizontal tab
			printAscii('\\');
			printAscii('t');
			return;
		} else if (data == 0x0B) {  // vertical tab
			printAscii('\\');
			printAscii('v');
			return;
		}
		printAscii('\\');
		printAscii(hexdecimals[data >> 4]);
		printAscii(hexdecimals[data & 0x0F]);
	}

	private static void printAscii(char data) {
		System.out.print(data);
	}
}
