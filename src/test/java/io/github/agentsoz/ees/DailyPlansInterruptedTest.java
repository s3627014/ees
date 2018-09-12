/**
 *
 */
package io.github.agentsoz.ees;

import io.github.agentsoz.util.TestUtils;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.matsim.core.utils.misc.CRCChecksum;
import org.matsim.testcases.MatsimTestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dsingh, Joel Robertson
 *
 */
public class DailyPlansInterruptedTest {

    // have tests in separate classes so that they run, at least under maven, in separate JVMs.  kai, nov'17

    private static final Logger log = LoggerFactory.getLogger(DailyPlansInterruptedTest.class);

    @Rule
    public MatsimTestUtils utils = new MatsimTestUtils();

    @Test
    public void testDailyPlansInterruptedV2() {

        utils.getOutputDirectory(); // creates a clean one so need to call this first
        String[] args = {
                "--config", "scenarios/surf-coast-shire/plan-interrupted/ees.xml",
        };
        Run.main(args);

        final String actualEventsFilename = utils.getOutputDirectory() + "/output_events.xml.gz";
        final String primaryExpectedEventsFilename = utils.getInputDirectory() + "/output_events.xml.gz";
        TestUtils.comparingDepartures(primaryExpectedEventsFilename,actualEventsFilename,5.);
        TestUtils.comparingArrivals(primaryExpectedEventsFilename,actualEventsFilename,5.);
        TestUtils.comparingActivityStarts(primaryExpectedEventsFilename,actualEventsFilename, 5.);
        TestUtils.compareFullEvents(primaryExpectedEventsFilename,actualEventsFilename, false);
    }

}

