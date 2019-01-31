package nathandbf.pokemondb.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetValidator{

    public static boolean verifica3G(Context context) {
        try{
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo m3g = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return m3g.isConnected();
        }catch (Exception e){
            return false;
        }
    }

    public static boolean verificaWifi(Context context) {
        try{
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return mWifi.isConnected();
        }catch (Exception e){
            return false;
        }
    }

    public static boolean verificaInternet(Context context) {
        return verificaWifi(context) || verifica3G(context);
    }
}
