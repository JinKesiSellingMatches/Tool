package core.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 产生32位的uuid.
     * 
     * @return
     */
    public static String generateUuid() {

        String uuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(uuid.substring(0, 8));
        sb.append(uuid.substring(9, 13));
        sb.append(uuid.substring(14, 18));
        sb.append(uuid.substring(19, 23));
        sb.append(uuid.substring(24));
        return sb.toString();
    }

}
