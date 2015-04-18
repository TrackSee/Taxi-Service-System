package util;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * @author Ruslan Gunavardana.
 */
public class IdGeneratorTest {
    @Test
    public void testGenerateId() throws Exception {
        final int ID_COUNT = 4000;
        String[] bigList = new String[ID_COUNT];
        for (int i = 0; i < ID_COUNT; i++) {
            bigList[i] = IdGenerator.generateId();
        }
        for (String st1 : bigList) {
            for (String st2 : bigList) {
                assertNotEquals(st1, st2);
            }
        }

    }
}
