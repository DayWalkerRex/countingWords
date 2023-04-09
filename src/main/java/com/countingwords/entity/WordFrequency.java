package com.countingwords.entity;

import com.countingwords.repository.IWordFrequency;

public class WordFrequency implements IWordFrequency {
	private String word;
	
	private int frequency;

	public String getWord() {
		return word;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	 @Override
	 public String toString() {
	   return "(\""+word+"\","+frequency+")";
	}
}
