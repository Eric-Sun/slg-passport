package com.h13.slg.passport.core;

/**
 * Created by sunbo on 14-10-24.
 */
public class PassportConstants {

    public static class Session {
        public final static String ACCOUNT_ID_KEY = "accountId";
    }


    public static class GameServerStatus {
        public final static int PREPARED_FOR_OPEN = 0;
        public final static int OPENING = 1;
        public final static int MAINTAIN = 2;
        public final static int CLOSED = -1;
    }
}
