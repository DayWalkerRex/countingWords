package com.countingwords.repository;

import com.countingwords.entity.WordFrequency;

public interface IWordFrequencyAnalyzer {
		int calculateHighestFrequency(String text);
		int calculateFrequencyForWord (String text, String word);
		WordFrequency[] calculateMostFrequentNWords (String text, int n);
}
