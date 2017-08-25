package org.code5.code5push.push;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by sony on 8/25/2017.
 */
@Database(name = PushDataBase.NAME, version = PushDataBase.VERSION)

public class PushDataBase
{

    public static final String NAME = "notifications";

    public static final int VERSION = 100;
}
