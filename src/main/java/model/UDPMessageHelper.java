package model;

import static java.lang.Integer.parseInt;

public class UDPMessageHelper {



    public UDPMessageHelper() {

    }

    public String addTheNumberToBinString(String binString, long number, long maxValue) {

        long numberFromBinString = convertBinStringToDecNumber(binString);
        if ((numberFromBinString + number) > maxValue) {
            return convertDecStringNumberToBinStringNumber(0, binString.length());
        } else {
            return convertDecStringNumberToBinStringNumber(numberFromBinString + number, binString.length());
        }

    }

    protected String addTheNumberToBinStringForSSI(String binString, long number, long maxValue) {

        long numberFromBinString = convertBinStringToDecNumber(binString);
        if ((numberFromBinString + number) >= maxValue) {
            return convertDecStringNumberToBinStringNumber(7000, binString.length());
        } else {
            return convertDecStringNumberToBinStringNumber(numberFromBinString + number, binString.length());
        }

    }

    private String convertDecStringNumberToBinStringNumber(long number, int lengthOfBinString) {

        StringBuilder binString = new StringBuilder(Long.toBinaryString(number));
        while (binString.length() < lengthOfBinString) {
            binString.insert(0, "0");
        }
        return binString.toString();
    }

    private int convertBinStringToDecNumber(String binString) {
        return Integer.parseInt(binString, 2);
    }

    protected byte[] convertBinStringToByteArray(String binString) {

        float numberOfDecNumbersFromBinString = binString.length() / 8;
        int lengthOfByteArrayFromBinString = (int) Math.ceil(numberOfDecNumbersFromBinString);
        byte[] byteArray = new byte[lengthOfByteArrayFromBinString];
        for (int i = 0; i < lengthOfByteArrayFromBinString; ++i) {
            int numberFromBinString = Integer.parseInt(binString.substring(0, 8), 2);
            if (numberFromBinString < 128) {
                byteArray[i] = (byte) numberFromBinString;
            } else {
                byteArray[i] = (byte) (numberFromBinString - 256);
            }
            binString = binString.substring(8);
        }
        return byteArray;
    }

    protected String convertDecStringNumberToBinStringNumber(String decNumber, int bitInBinNumber) {

        String x = Integer.toBinaryString(Integer.parseInt(decNumber));
        StringBuilder binString = new StringBuilder(x);
        while (binString.length() < bitInBinNumber) {
            binString.insert(0, "0");
        }
        return binString.toString();
    }

    private String convertBinStringToHexString(String binString) {


        StringBuilder hexString = new StringBuilder();

        while (binString.length() > 3) {
            hexString.append(Integer.toHexString(parseInt(binString.substring(0, 4), 2)));
            binString = binString.substring(4);
        }
        return hexString.toString();
    }

    protected String calculateLipLatitudeFromDecLatitude(String latitude) {
        double decLatitude = Double.parseDouble(latitude);
        //TODO fix this, not right maths
        double x = 180.000000000 / 16777216.000000000;
        long decLipLatitude = Math.round(decLatitude / x);
        String f = Long.toString(decLipLatitude);
        return convertDecStringNumberToBinStringNumber(Long.toString(decLipLatitude), 24);
    }

    protected String calculateLipLongitudeFromDecLongitude(String longitude) {
        //TODO negative value do not calculate
        double decLongitude = Double.parseDouble(longitude);
        double x = 180.000000000 / 16777216.000000000;
        long decLipLongitude = Math.round(decLongitude / x);
        String f = Long.toString(decLipLongitude);
        return convertDecStringNumberToBinStringNumber(Long.toString(decLipLongitude), 25);
    }

}
