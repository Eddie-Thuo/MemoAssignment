package com.example.eddiethuo.memoassignment;

/**
 * Created by eddiethuo on 16/11/2016.
 */

public class MemoDbSchema {
    public static final class MemoTable {

        /*Name of table*/
        public static final String NAME = "memos";

        /**
         * All Schema (columns) used to construct the table
         * for holding memo information.
         */
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String BODY = "body";
            public static final String DATE = "date";
            public static final String MODE = "mode";
        }
    }


}

