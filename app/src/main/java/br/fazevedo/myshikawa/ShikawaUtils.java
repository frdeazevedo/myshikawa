package br.fazevedo.myshikawa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ShikawaUtils {

    public enum DialogForType {
        SHIKAWA,
    }

    static void showCreateDialog(final Context context, DialogForType type,
                                        final OnCreateDialogListener listener) {
        String dialog_title = "No title";
        switch (type) {
            case SHIKAWA:
                dialog_title = context.getString(R.string.create_shikawa);
                break;
        }

        View view = LayoutInflater.from(context).inflate(R.layout.layout_create, null);
        final EditText et_title = view.findViewById(R.id.et_title);
        final EditText et_description = view.findViewById(R.id.et_description);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setTitle(dialog_title)
                .setCancelable(true)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = et_title.getText().toString();
                        String descr = et_description.getText().toString();

                        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(descr)) {
                            Toast.makeText(context, context.getString(R.string.cant_be_empty),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            dialog.dismiss();
                            listener.onPositive(et_title.getText().toString(),
                                    et_description.getText().toString());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    public static void showRemoveDialog(Context context,
                                        DialogForType type,
                                        String type_title,
                                        final OnRemoveDialogListener listener) {
        String title = "No title";
        switch (type) {
            case SHIKAWA:
                title = context.getString(R.string.dialog_remove_title,
                        context.getString(R.string.shikawa));
                break;
        }
        String body = context.getString(R.string.dialog_remove_body, type_title);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(body)
                .setCancelable(true)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onPositive();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onNegative();
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    public interface OnCreateDialogListener {
        void onPositive(String title, String description);
    }

    public interface OnRemoveDialogListener {
        void onPositive();
        void onNegative();
    }
}
