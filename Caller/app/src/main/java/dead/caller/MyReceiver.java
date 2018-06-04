package dead.caller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        TelephonyManager manager= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
       IncomingCallListener listener=new IncomingCallListener(context);
        manager.listen(listener,PhoneStateListener.LISTEN_CALL_STATE);

    }

    public class IncomingCallListener extends PhoneStateListener{
        Context context;
        public IncomingCallListener(Context context) {
            this.context=context;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            if (TelephonyManager.CALL_STATE_RINGING==state)
            Toast.makeText(context,"Call Incoming from "+incomingNumber,Toast.LENGTH_LONG).show();;

            if (TelephonyManager.CALL_STATE_IDLE==state)
                Toast.makeText(context,"Call Disconnected ",Toast.LENGTH_SHORT).show();

        }
    }
}
