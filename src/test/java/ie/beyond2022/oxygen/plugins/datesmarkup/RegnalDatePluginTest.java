package ie.beyond2022.oxygen.plugins.datesmarkup;

import ie.beyond2022.utils.regnaldate.RegnalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ro.sync.exml.plugin.selection.SelectionPluginContext;
import ro.sync.exml.plugin.selection.SelectionPluginResult;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

public class RegnalDatePluginTest {

    RegnalDatePluginExtension descriptor;

    SelectionPluginContext mockedContext;

    @BeforeEach
    void setUp() {

        mockedContext = mock(SelectionPluginContext.class, RETURNS_DEEP_STUBS);
        descriptor = new RegnalDatePluginExtension();
    }

    // Regnal year with month and date

    @Test
    public void test_Henry_III_1216_10_30() {

        Mockito.when(mockedContext.getSelection()).thenReturn("30 October 1 Henry III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1216-10-30\">30 October <supplied>1216</supplied> 1 Henry III</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_Henry_III_1216_10_30_whitespace() {

        Mockito.when(mockedContext.getSelection()).thenReturn("30  October 1   Henry III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1216-10-30\">30 October <supplied>1216</supplied> 1 Henry III</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_Edward_I_1300_07_07()  {

        Mockito.when(mockedContext.getSelection()).thenReturn("7 Jul. 28 Edw. I");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1300-07-07\">7 Jul. <supplied>1300</supplied> 28 Edw. I</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_Edward_I_1300_07_07_whitespace()  {

        Mockito.when(mockedContext.getSelection()).thenReturn(" 7 Jul. 28  Edw.  I");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1300-07-07\">7 Jul. <supplied>1300</supplied> 28 Edw. I</date>",
                result.getProcessedSelection());
    }

    // Regnal year only

    @Test
    public void test_Henry_1_III() {

        Mockito.when(mockedContext.getSelection()).thenReturn("1 Henry III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" from=\"1216-10-28\" to=\"1217-10-27\">1 Henry III <supplied>1216–1217</supplied></date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_Henry_1_III_whitespace() {

        Mockito.when(mockedContext.getSelection()).thenReturn("1  Henry III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" from=\"1216-10-28\" to=\"1217-10-27\">1 Henry III <supplied>1216–1217</supplied></date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_Edward_I() {

        Mockito.when(mockedContext.getSelection()).thenReturn("1 Edward I");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" from=\"1272-11-20\" to=\"1273-11-19\">1 Edward I <supplied>1272–1273</supplied></date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_Edward_I_whitespace() {

        Mockito.when(mockedContext.getSelection()).thenReturn(" 1 Edward I ");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" from=\"1272-11-20\" to=\"1273-11-19\">1 Edward I <supplied>1272–1273</supplied></date>",
                result.getProcessedSelection());
    }

    // Regnal year with Easter

    @Test
    public void test_3_Edward_III_easter() {

        Mockito.when(mockedContext.getSelection()).thenReturn("Easter 3 Edward III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1329-04-23\">Easter <supplied>1329</supplied> 3 Edward III</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_3_Edward_III_easter_octave() {

        Mockito.when(mockedContext.getSelection()).thenReturn("octave of Easter 3 Edward III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1329-04-30\">octave of Easter <supplied>1329</supplied> 3 Edward III</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_3_Edward_III_easter_quindene() {

        Mockito.when(mockedContext.getSelection()).thenReturn("quindene of Easter 3 Edward III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1329-05-07\">quindene of Easter <supplied>1329</supplied> 3 Edward III</date>",
                result.getProcessedSelection());
    }


    // --- Dates used in demo
    @Test
    public void test_demo_example_1() {

        Mockito.when(mockedContext.getSelection()).thenReturn("25 November 1 Edward I");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1272-11-25\">25 November <supplied>1272</supplied> 1 Edward I</date>",
                result.getProcessedSelection());
    }


    @Test
    public void test_researcher_example_1() {

        Mockito.when(mockedContext.getSelection()).thenReturn("Ascension 18 Henry VI");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1440-05-05\">Ascension <supplied>1440</supplied> 18 Henry VI</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_2() {

        Mockito.when(mockedContext.getSelection()).thenReturn("Easter Sunday 6 Richard II");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1383-03-22\">Easter Sunday <supplied>1383</supplied> 6 Richard II</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_3() {

        Mockito.when(mockedContext.getSelection()).thenReturn("Ash Wednesday 31 Edward I");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1303-02-20\">Ash Wednesday <supplied>1303</supplied> 31 Edward I</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_4() {

        Mockito.when(mockedContext.getSelection()).thenReturn("Good Friday 39 Edward III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1365-04-11\">Good Friday <supplied>1365</supplied> 39 Edward III</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_5() {

        Mockito.when(mockedContext.getSelection()).thenReturn("Corpus Christi 4 Edward IV");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1464-05-31\">Corpus Christi <supplied>1464</supplied> 4 Edward IV</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_6() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the morrow of Trinity 5 Richard II");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1382-06-02\">the morrow of Trinity <supplied>1382</supplied> 5 Richard II</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_7() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the octave of the nativity of St John the Baptist 7 Henry IV");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1406-07-01\">the octave of the nativity of St John the Baptist <supplied>1406</supplied> 7 Henry IV</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_8() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the eve of St Agnes 3 Edward II");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1310-01-20\">the eve of St Agnes <supplied>1310</supplied> 3 Edward II</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_9() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the quindene of Lammas 10 Henry V");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1422-08-15\">the quindene of Lammas <supplied>1422</supplied> 10 Henry V</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_10() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the morrow of Martinmas 15 Edward III");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1341-11-12\">the morrow of Martinmas <supplied>1341</supplied> 15 Edward III</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_11() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the eve of Corpus Christi 38 Henry VI");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1460-06-11\">the eve of Corpus Christi <supplied>1460</supplied> 38 Henry VI</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_12() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the octave of St Swithin 23 Richard II");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1399-07-09\">the octave of St Swithin <supplied>1399</supplied> 23 Richard II</date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_13() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the octave of St Swithin [23 Richard II]");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1399-07-09\">the octave of St Swithin <supplied>1399</supplied> <supplied>23 Richard II</supplied></date>",
                result.getProcessedSelection());
    }

    @Test
    public void test_researcher_example_14() {

        Mockito.when(mockedContext.getSelection()).thenReturn("the eve of Corpus Christi [38 Henry VI]");
        SelectionPluginResult result = descriptor.process(mockedContext);

        assertEquals("<date type=\"regnal\" when=\"1460-06-11\">the eve of Corpus Christi <supplied>1460</supplied> <supplied>38 Henry VI</supplied></date>",
                result.getProcessedSelection());
    }
}
