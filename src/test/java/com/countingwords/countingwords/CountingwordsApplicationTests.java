package com.countingwords.countingwords;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.countingwords.entity.WordFrequency;
import com.countingwords.entity.WordFrequencyAnalyzer;

@SpringBootTest
class CountingwordsApplicationTests {

	WordFrequencyAnalyzer wfa=new WordFrequencyAnalyzer();
	@Test
	public void testHighestFrequency() {
		assertEquals(4,wfa.calculateHighestFrequency("Peter Piper picked a peck of pickled peppers "
				+ "A peck of pickled peppers Peter Piper picked "
				+ "If Peter Piper picked a peck of pickled peppers "
				+ "Where’s the peck of pickled peppers Peter Piper picked?"));
	}
	@Test
	public void testHighestFrequencyWord() {
		assertEquals(4,wfa.calculateFrequencyForWord("Peter Piper picked a peck of pickled peppers "
				+ "A peck of pickled peppers Peter Piper picked "
				+ "If Peter Piper picked a peck of pickled peppers "
				+ "Where’s the peck of pickled peppers Peter Piper picked?", "peter"));
	}
	@Test
	public void testHighestNFrequencyWord() {
		WordFrequency[] arr = new WordFrequency[3]; 
		WordFrequency wordFreq = new WordFrequency();
		wordFreq.setWord("of");
		wordFreq.setFrequency(4);
		arr[0] = wordFreq;
		wordFreq = new WordFrequency();
		wordFreq.setWord("peck");
		wordFreq.setFrequency(4);
		arr[1] = wordFreq;
		wordFreq = new WordFrequency();
		wordFreq.setFrequency(4);
		wordFreq.setWord("peppers");
		arr[2] = wordFreq;
		
		WordFrequency[] arr_func = wfa.calculateMostFrequentNWords("Peter Piper picked a peck of pickled peppers. "
																 + "A peck of pickled peppers Peter Piper picked. "
																 + "If Peter Piper picked a peck of pickled peppers. "
																 + "Where’s the peck of pickled peppers Peter Piper picked?", 3);
		
		for(int i=0; i<arr.length;i++)
			assertEquals(arr[i].toString(), arr_func[i].toString());
	}
	

}
