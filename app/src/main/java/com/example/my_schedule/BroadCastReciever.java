package com.example.my_schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.Toast;

public class BroadCastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DataBase database = new DataBase(context);
        Cursor cursorl =
                database.readall();
        cursorl.moveToLast();
        String phone = cursorl.getString(0);
        String message = cursorl.getString(6);
        Toast.makeText(context, cursorl.getString(0), Toast.LENGTH_SHORT).show();
       if (Boolean.parseBoolean(cursorl.getString(7)) ) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phone +
                    "&text=" + message));
            context.startActivity(i);

        }

        else if (Boolean.parseBoolean(cursorl.getString(8))) {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
        }

        else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
        }


    }
}
