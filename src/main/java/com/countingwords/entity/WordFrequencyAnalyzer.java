package com.countingwords.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.countingwords.repository.IWordFrequencyAnalyzer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class WordFrequencyAnalyzer implements IWordFrequencyAnalyzer {

	WordFrequency wordFrequency = new WordFrequency();
	
	//function to prepare the incoming string for the other functions
	private String preProcText(String text) {
		String tmpText = text.toLowerCase(); //Switch the letter in the sentence to lowercase
		tmpText = tmpText.replaceAll("[^a-zA-Z\\s]", ""); //Exclude spaces and letters a-z and A-Z to be replaced
		
		return tmpText;
	}
	
	public int calculateHighestFrequency(String text) {
		int tmpWordCount = 0;
		int wordCount = 0;
		String tmpText = preProcText(text);
		
		String[] arrOfWords = tmpText.split(" "); //Put sentence into an array
		Arrays.sort(arrOfWords); //Sort array
		
		for (String a : arrOfWords) {
			tmpWordCount = calculateFrequencyForWord(text, a); //Calculate frequency in the array for each word
			if(tmpWordCount > wordCount) { //Save the highest word count
				this.wordFrequency.setWord(a); //Save the highest word
				wordCount = tmpWordCount;
			}
		}
		
		this.wordFrequency.setFrequency(wordCount); //Save the highest frequency
		
		return wordCount;
	}

	public int calculateFrequencyForWord(String text, String word) {
		int wordCount = 0;
		String tmpText = preProcText(text);
		
		String[] arrOfWords = tmpText.split(" "); //Split sentence into an array
		
		for (String a : arrOfWords) {
			if(a.equals(word)) { //Count each word in the array
				wordCount++;
			}
		}
		
		return wordCount;
	}

	public WordFrequency[] calculateMostFrequentNWords(String text, int n) {
		String[] arrOfWords = text.split(" "); //Split sentence into an array
		WordFrequency[] arr = new WordFrequency[n]; //Set up an array of object WordFrequency
		String tmpText = preProcText(text);
		
		for(int i=0; i<arr.length; i++) {
			this.wordFrequency = new WordFrequency(); //Renew local object
			
			calculateHighestFrequency(tmpText.trim());
			arr[i] = this.wordFrequency; //Save object in an array
						
			if (tmpText.indexOf(this.wordFrequency.getWord())+this.wordFrequency.getWord().length()+1 < tmpText.length()) {
				//Find extraChar that is behind a word in the sentence (is now usually a space)
				String extraChar = tmpText.substring(tmpText.indexOf(this.wordFrequency.getWord())+this.wordFrequency.getWord().length(),
													 tmpText.indexOf(this.wordFrequency.getWord())+this.wordFrequency.getWord().length()+1);
				tmpText = tmpText.replace(this.wordFrequency.getWord()+extraChar, ""); //Remove found word from sentence
				
			} else {
				tmpText = tmpText.replaceAll(this.wordFrequency.getWord(), ""); //Remove found word from the sentence
			}
		}
		
		return arr;
	}
}
