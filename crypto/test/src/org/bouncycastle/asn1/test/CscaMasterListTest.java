package org.bouncycastle.asn1.test;

import java.io.IOException;
import java.util.Arrays;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.icao.CscaMasterList;
import org.bouncycastle.util.io.Streams;
import org.bouncycastle.util.test.SimpleTest;

public class CscaMasterListTest 
    extends SimpleTest
{
    public String getName()
    {
        return "CscaMasterList";
    }
    
    public void performTest() 
        throws Exception
    {
        byte[] input = getInput("australia-ml-content.data");
        CscaMasterList parsedList
            = CscaMasterList.getInstance(ASN1Object.fromByteArray(input));

        if (parsedList.getCertStructs().length != 5) {
            fail("Cert structure parsing failed: incorrect length");
        }

        byte[] output = parsedList.getEncoded();
        if (!Arrays.equals(input, output)) {
            fail("Encoding failed after parse");
        }
    }

    private byte[] getInput(String name)
        throws IOException
    {
        return Streams.readAll(getClass().getResourceAsStream(name));
    }

    public static void main(
        String[]    args)
    {
        runTest(new CscaMasterListTest());
    }
}
