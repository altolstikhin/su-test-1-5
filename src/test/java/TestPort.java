import org.fbnv.testport.Port;
import org.junit.Assert;
import org.junit.Test;

public class TestPort {
    private static final Integer[][] EXPECTED_ARRAY_METHOD1 = {{1, 3, 4, 5}, {2}, {6, 7, 8, 9}};
    private static final Integer[][] EXPECTED_ARRAY_METHOD2 = {
            {1, 2, 0}, {1, 2, 9}, {1, 2, 10}, {1, 2, 11}
            , {1, 3, 0}, {1, 3, 9}, {1, 3, 10}, {1, 3, 11}
            , {1, 4, 0}, {1, 4, 9}, {1, 4, 10}, {1, 4, 11}

            , {5, 2, 0}, {5, 2, 9}, {5, 2, 10}, {5, 2, 11}
            , {5, 3, 0}, {5, 3, 9}, {5, 3, 10}, {5, 3, 11}
            , {5, 4, 0}, {5, 4, 9}, {5, 4, 10}, {5, 4, 11}

            , {6, 2, 0}, {6, 2, 9}, {6, 2, 10}, {6, 2, 11}
            , {6, 3, 0}, {6, 3, 9}, {6, 3, 10}, {6, 3, 11}
            , {6, 4, 0}, {6, 4, 9}, {6, 4, 10}, {6, 4, 11}

            , {7, 2, 0}, {7, 2, 9}, {7, 2, 10}, {7, 2, 11}
            , {7, 3, 0}, {7, 3, 9}, {7, 3, 10}, {7, 3, 11}
            , {7, 4, 0}, {7, 4, 9}, {7, 4, 10}, {7, 4, 11}};

    @Test
    public void testMethod1() {
        var port = new Port("1,3-5", "2", "6-9");
        var resultMethod1 = port.method1();
        Assert.assertArrayEquals(EXPECTED_ARRAY_METHOD1, resultMethod1);
    }

    @Test
    public void testMethod2() {
        var port = new Port("1,5-7", "2-4", "0,9-11");
        var resultMethod2 = port.method2();
        Assert.assertEquals(4 * 3 * 4, resultMethod2.length);
        Assert.assertArrayEquals(EXPECTED_ARRAY_METHOD2, resultMethod2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumberInput() {
        new Port("-1").method1();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongRangeInput() {
        new Port("1-2-3").method1();
    }
}
