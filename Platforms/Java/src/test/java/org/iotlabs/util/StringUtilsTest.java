package org.iotlabs.util;

import org.junit.Test;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

public class StringUtilsTest {

    @Test
    public void getString() throws Exception {
        // generate string with IoTLab StringUtils.
        String val = StringUtils.getString("안녕하세요!".getBytes(Charset.forName("UTF-8")));
        byte[] valByte = val.getBytes(Charset.forName("UTF-8"));

        // generate string with default string object
        String origin = new String("안녕하세요!".getBytes(Charset.forName("UTF-8")), Charset.forName("UTF-8"));
        byte[] originByte = origin.getBytes(Charset.forName("UTF-8"));

        // generate string with euc-kr encoding
        String eucKrString = new String("안녕하세요!".getBytes(Charset.forName("UTF-8")), Charset.forName("euc-kr"));
        byte[] eucKrByte = eucKrString.getBytes(Charset.forName("euc-kr"));

        // same encoding, same length
        assertEquals(valByte.length, originByte.length);
        // different encoding, different length
        assertNotEquals(valByte.length, eucKrByte.length);

        // same encoding, same byte at same position
        for (int i = 0; i < valByte.length; i++) {
            assertEquals(originByte[i], valByte[i]);
        }
    }

    @Test
    public void isEmpty() throws Exception {
        assertEquals(true, StringUtils.isEmpty(""));
        assertEquals(true, StringUtils.isEmpty(null));
        assertEquals(false, StringUtils.isEmpty("not_empty_string"));
    }

}