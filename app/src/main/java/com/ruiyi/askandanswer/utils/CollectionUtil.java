package com.ruiyi.askandanswer.utils;

import java.util.Collection;

/**
 * Created by Administrator on 2018/3/25.
 */

public class CollectionUtil {

        /**
         * 判断集合是否为空
         *
         * @param collection
         * @return
         */
        public static boolean isEmpty(Collection<? extends Object> collection) {
            boolean isEmpty = true;
            if (collection != null && !collection.isEmpty()) {
                isEmpty = false;
            }
            return isEmpty;
        }
}
