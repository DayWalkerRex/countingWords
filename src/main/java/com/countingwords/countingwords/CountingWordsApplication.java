package com.countingwords.countingwords;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.countingwords.entity.WordFrequency;
import com.countingwords.entity.WordFrequencyAnalyzer;

@SpringBootApplication
public class CountingWordsApplication {

	public static void main(String[] args) {		
		Scanner input = new Scanner(System.in); //Use console as input for sentence
		boolean continueLoop = true;
		int amountOfMostFreqWords = 0;
		
			SpringApplication.run(CountingWordsApplication.class, args);
		do	{
			
			System.out.println("Enter the sentence you like to use for the program:");
		
			
			String sentence = input.nextLine();
		
			try {
				System.out.print("Enter the amount of most frequent words you would like to see:");
		
				amountOfMostFreqWords = input.nextInt();
				continueLoop = false;
			}
			//Error handling when the input isn't a number
			catch (InputMismatchException inputMismatchException) {
				System.err.printf("%nException: %s%n", inputMismatchException);
				input.nextLine();
				System.out.println("You must enter numbers");
			}
			
		WordFrequencyAnalyzer wfa = new WordFrequencyAnalyzer();
		
		int highestFreq = wfa.calculateHighestFrequency(sentence);
		if(amountOfMostFreqWords > 0) {
			System.out.println("The highest frequency of a word is " + highestFreq + " in this sentence");
		
			WordFrequency[] arr = wfa.calculateMostFrequentNWords(sentence, amountOfMostFreqWords);
		
			String output = "{";
			for(int i = 0; i<arr.length;i++) {
				output = output.concat(arr[i].toString());
				if (i == arr.length-1) {
					output = output.concat("}");
				} else {
					output = output.concat(",");
				}
			} 
			System.out.printf("Here is the output of the " + 
							   amountOfMostFreqWords + 
							   " most frequent words in this sentence.%n" +
							   "output: " +
							   output);
			}
		} while (continueLoop);
	}
}
