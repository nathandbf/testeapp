package nathandbf.pokemondb.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;

import nathandbf.pokemondb.R;

public class Dialog {
    public static ProgressDialog loadingDialog;
    private static boolean dialogAberto;

    public static void dialogOkSimples(Activity tela, String titulo, String mensagem){
        if (Looper.myLooper() == null)
            Looper.prepare();

        tela.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                new AlertDialog.Builder(tela)
                        .setTitle(titulo)
                        .setMessage(mensagem)
                        .setPositiveButton(tela.getResources().getString(R.string.ok), new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                            }
                        })
                        .show();
            }
        });

    }

    public static void dialogCarregandoSimples(Context context) {
        try{
            if(dialogAberto) return;
            dialogAberto = true;
            loadingDialog = new ProgressDialog(context);
            loadingDialog.setTitle(context.getResources().getString(R.string.Aguarde));
            loadingDialog.setMessage(context.getResources().getString(R.string.Carregando));
            loadingDialog.setCancelable(true);
            loadingDialog.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void fecharDialogCarregando() {
        dialogAberto = false;
        loadingDialog.dismiss();
    }
}
