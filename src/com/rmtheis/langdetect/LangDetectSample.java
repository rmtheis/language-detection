package com.rmtheis.langdetect;

import java.util.ArrayList;

import com.cybozu.labs.langdetect.*;

public class LangDetectSample {

  public static void main(String[] args) {

    long startTime;
    String lang = "none";
    ArrayList<Language> langlist = null;

    try {

      // Initialize
      startTime = System.currentTimeMillis();  
      DetectorFactory.create();
      System.out.println("Initialization finished in " + (System.currentTimeMillis() - startTime) + " ms");

      // Detect
      startTime = System.currentTimeMillis();          
      Detector detector = DetectorFactory.create();
      detector.append("The quick brown fox jumps over the lazy dog.");
      lang = detector.detect();
      System.out.println("Detection finished in " + (System.currentTimeMillis() - startTime) + " ms");
      
      // Get probabilities
      langlist = detector.getProbabilities();

    } catch (LangDetectException e) {
      System.err.println("Detection failed");
      e.printStackTrace();
    }

    System.out.println("Detected language: " + lang);
    for (Language s : langlist) {
      System.out.println(s);
    }

  }

}
