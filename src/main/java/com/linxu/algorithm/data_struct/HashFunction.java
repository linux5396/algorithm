package com.linxu.algorithm.data_struct;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author linxu
 * @date 2020/2/23
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
public class HashFunction {
    private MessageDigest messageDigest;
    private volatile boolean started = false;

    public HashFunction() {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            started = true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public long hash(String object) {
        if (started) {
            messageDigest.reset();
            messageDigest.update(object.getBytes());
            byte[] bKey = messageDigest.digest();
            long result = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16
                    | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF));
            return result & 0xffffffffL;
        }
        return -1;
    }
}
