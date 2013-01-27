package com.cybozu.labs.langdetect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.cybozu.labs.langdetect.util.LangProfile;
import com.rmtheis.langdetect.profile.*;

/**
 * Language Detector Factory Class
 * 
 * This class manages an initialization and constructions of {@link Detector}. 
 * 
 * Before using language detection library, 
 * load profiles with {@link DetectorFactory#loadProfile(String)} method
 * and set initialization parameters.
 * 
 * When the language detection,
 * construct Detector instance via {@link DetectorFactory#create()}.
 * See also {@link Detector}'s sample code.
 * 
 * <ul>
 * <li>4x faster improvement based on Elmer Garduno's code. Thanks!</li>
 * </ul>
 * 
 * @see Detector
 * @author Nakatani Shuyo
 */
public class DetectorFactory {
    public HashMap<String, double[]> wordLangProbMap;
    public ArrayList<String> langlist;
    private static List<LangProfile> profilelist;
    private static boolean isProfileListLoaded = false;
    public Long seed = null;
    
    private DetectorFactory() {
        wordLangProbMap = new HashMap<String, double[]>();        
        langlist = new ArrayList<String>();
        profilelist = Arrays.asList(
            (new AF()).getLangProfile(), // Afrikaans
            (new EU()).getLangProfile(), // Basque
            (new BG()).getLangProfile(), // Bulgarian
            (new CA()).getLangProfile(), // Catalan
            (new DA()).getLangProfile(), // Danish
            (new NL()).getLangProfile(), // Dutch
            (new EN()).getLangProfile(), // English
            (new FR()).getLangProfile(), // French
            (new GL()).getLangProfile(), // Galician
            (new HT()).getLangProfile(), // Haitian Creole
            (new IS()).getLangProfile(), // Icelandic
            (new IT()).getLangProfile(), // Italian
            (new MK()).getLangProfile(), // Macedonian
            (new NO()).getLangProfile(), // Norwegian
            (new OC()).getLangProfile(), // Occitan
            (new PT()).getLangProfile(), // Portuguese
            (new RO()).getLangProfile(), // Romanian
            (new ES()).getLangProfile(), // Spanish
            (new SV()).getLangProfile(), // Swedish
            (new CY()).getLangProfile()  // Welsh
        ); 
    }
    static private DetectorFactory instance_ = new DetectorFactory();

    public static void loadProfile(List<LangProfile> profiles) {
      int index = 0;
      int langsize = profiles.size();
      for (LangProfile profile: profiles) {
        addProfile(profile, index, langsize);
        ++index;
      }

    }

    /**
     * @param profile
     * @param langsize 
     * @param index 
     * @throws LangDetectException 
     */
    static /* package scope */ void addProfile(LangProfile profile, int index, int langsize) {
        String lang = profile.name;
        instance_.langlist.add(lang);
        for (String word: profile.freq.keySet()) {
            if (!instance_.wordLangProbMap.containsKey(word)) {
                instance_.wordLangProbMap.put(word, new double[langsize]);
            }
            int length = word.length();
            if (length >= 1 && length <= 3) {
                double prob = profile.freq.get(word).doubleValue() / profile.n_words[length - 1];
                instance_.wordLangProbMap.get(word)[index] = prob;
            }
        }
    }

    /**
     * Clear loaded language profiles (reinitialization to be available)
     */
    static public void clear() {
        instance_.langlist.clear();
        instance_.wordLangProbMap.clear();
    }

    /**
     * Construct Detector instance
     * 
     * @return Detector instance
     * @throws LangDetectException 
     */
    static public Detector create() {
        return createDetector();
    }

    /**
     * Construct Detector instance with smoothing parameter 
     * 
     * @param alpha smoothing parameter (default value = 0.5)
     * @return Detector instance
     * @throws LangDetectException 
     */
    public static Detector create(double alpha) throws LangDetectException {
        Detector detector = createDetector();
        detector.setAlpha(alpha);
        return detector;
    }

    static private Detector createDetector() {
        Detector detector = new Detector(instance_);
        // Check whether probabilities have already been loaded
        if (!isProfileListLoaded) {
            DetectorFactory.loadProfile(profilelist);
            isProfileListLoaded = true;
        }
        return detector;
    }
    
    public static void setSeed(long seed) {
        instance_.seed = seed;
    }
    
    public static final List<String> getLangList() {
        return Collections.unmodifiableList(instance_.langlist);
    }
}
