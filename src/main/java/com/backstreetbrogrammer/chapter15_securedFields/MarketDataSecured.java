package com.backstreetbrogrammer.chapter15_securedFields;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class MarketDataSecured implements Serializable {

    private static final long serialVersionUID = 8850232432976998069L;

    private static final String SECRET_KEY = "$ecretKey";
    private static final String ALGORITHM = "AES";

    private static SecretKeySpec secretKey;

    private String securityId;
    private long time;
    private double open;
    private double high;
    private double low;
    private double close;
    private double last;
    private boolean isLevelOne;

    private String loginPassword;

    public MarketDataSecured() {
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(final String securityId) {
        this.securityId = securityId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(final double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(final double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(final double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(final double close) {
        this.close = close;
    }

    public double getLast() {
        return last;
    }

    public void setLast(final double last) {
        this.last = last;
    }

    public boolean isLevelOne() {
        return isLevelOne;
    }

    public void setLevelOne(final boolean levelOne) {
        isLevelOne = levelOne;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(final String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "MarketDataSecured{" +
                "securityId='" + securityId + '\'' +
                ", time=" + time +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", last=" + last +
                ", isLevelOne=" + isLevelOne +
                '}';
    }


    private void prepareSecreteKey() {
        final MessageDigest sha;
        try {
            byte[] key = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-512");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String encrypt(final String strToEncrypt) {
        String encoded = null;
        try {
            prepareSecreteKey();
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encoded = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return encoded;
    }

    private String decrypt(final String strToDecrypt) {
        String decoded = null;
        try {
            prepareSecreteKey();
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decoded = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return decoded;
    }

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("securityId", String.class),
            new ObjectStreamField("time", Long.TYPE),
            new ObjectStreamField("last", Double.TYPE),
            new ObjectStreamField("loginPassword", String.class)
    };

    private void writeObject(final ObjectOutputStream os) throws IOException {
        try {
            final ObjectOutputStream.PutField fields = os.putFields();
            fields.put("securityId", securityId);
            fields.put("time", time);
            fields.put("last", last);
            fields.put("loginPassword", encrypt(loginPassword));
            os.writeFields();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        try {
            final ObjectInputStream.GetField fields = is.readFields();
            securityId = (String) fields.get("securityId", null);
            time = fields.get("time", 0L);
            last = fields.get("last", 0D);
            loginPassword = decrypt((String) fields.get("loginPassword", null));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
