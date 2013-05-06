/**
 * 
 */
package com.cybozu.labs.langdetect;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * @author Nakatani Shuyo
 *
 */
public class LanguageTest {

    /**
     * Test method for {@link com.cybozu.labs.langdetect.Language#Language(java.lang.String, double)}.
     */
    @Test
    public final void testLanguage() {
        Language lang = new Language(null, 0);
        assertEquals(lang.lang, null);
        assertEquals(lang.prob, 0.0, 0.0001);
        assertEquals(lang.toString(), "");
        
        Language lang2 = new Language("en", 1.0);
        assertEquals(lang2.lang, "en");
        assertEquals(lang2.prob, 1.0, 0.0001);
        assertEquals(lang2.toString(), "en:1.0");
        
    }

}
