package com.kipruto.SphinxSecondSample;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class TranscriberDemo {

	public static void main(String[] args) throws Exception {

		Configuration configuration = new Configuration();

		configuration.setAcousticModelPath("src\\model\\other\\en_in.cd_cont_5000");
		configuration.setDictionaryPath("src\\model\\other\\en_in.dic");
		configuration.setLanguageModelPath("src\\model\\other\\en-us.lm.bin");

		StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
		InputStream stream = new FileInputStream(new File("audio\\test\\1.wav"));
		recognizer.startRecognition(stream);
		SpeechResult result;
		StringBuffer sb = new StringBuffer("");
		while ((result = recognizer.getResult()) != null) {
			sb.append(result.getHypothesis()+" ");
		//	System.out.format("Hypothesis: %s\n", result.getHypothesis());
		}
		System.out.println("Text: "+ sb.toString());
		recognizer.stopRecognition();
			LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
		// Start recognition process pruning previously cached data.
		recognizer.startRecognition(true);
		SpeechResult result = recognizer.getResult();
		// Pause recognition process. It can be resumed then with startRecognition(false).
		while ((result = recognizer.getResult()) != null) {
			System.out.format("Hypothesis: %s\n", result.getHypothesis());
		}
		recognizer.stopRecognition();
	}
}