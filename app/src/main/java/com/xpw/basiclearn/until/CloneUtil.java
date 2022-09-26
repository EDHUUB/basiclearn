package com.xpw.basiclearn.until;

import android.util.Log;

import com.xpw.basiclearn.pojo.Pointer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * @author sunkai
 * @date 2022/9/26 11:02
 * @read happy
 */
public class CloneUtil {
    private static final String TAG = "CloneUtil";

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(Map<Integer, Pointer> obj) {
        T cloneObj = null;
        try {
            Log.d(TAG, "clone: "+obj);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }
}
