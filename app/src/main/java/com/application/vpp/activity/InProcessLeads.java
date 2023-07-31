//package com.application.vpp.activity;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.application.vpp.Adapters.InProcessAdapter;
//import com.application.vpp.ClientServer.ConnectTOServer;
//import com.application.vpp.ClientServer.Connectivity;
//import com.application.vpp.ClientServer.SendTOServer;
//import com.application.vpp.Const.Const;
//import com.application.vpp.Datasets.InProcessDataset;
//import com.application.vpp.Datasets.InserSockettLogs;
//import com.application.vpp.Interfaces.ConnectionProcess;
//import com.application.vpp.Interfaces.RequestSent;
//import com.application.vpp.R;
//import com.application.vpp.ReusableLogics.Logics;
//import com.application.vpp.ReusableLogics.Methods;
//import com.application.vpp.SharedPref.SharedPref;
//import com.application.vpp.Utility.AlertDialogClass;
//import com.application.vpp.Views.Views;
//import com.google.firebase.crashlytics.FirebaseCrashlytics;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.sdsmdg.tastytoast.TastyToast;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//public class InProcessLeads extends NavigationDrawer implements RequestSent, ConnectionProcess {
//
//    public static Handler handlerInProcessLeads;
//    static Gson gson;
//    //    ProgressDialog ringProgressDialog;
//    ArrayList<InProcessDataset> inProcessDatasetArrayList;
//    RecyclerView listInProcess;
//    EditText searchView;
//    InProcessAdapter inProcessAdapter;
//    ConnectionProcess connectionProcess;
//    LinearLayout linear_processlead;
//    TextView tv_nodataavail;
//    Handler handler = new Handler();
//    Runnable runnable;
//    int delay = 2000;
//    boolean NOCheckINTERNET = false;
//    RelativeLayout mainLayout;
//    StringBuffer ssb = null;
//    String data = "";
//    int MaxTry = 0;
//
//    ArrayList<InserSockettLogs> inserSockettLogsArrayList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getLayoutInflater().inflate(R.layout.activity_in_process_leads, mDrawerLayout);
////        connectionProcess = (ConnectionProcess) this;
//        handlerInProcessLeads = new ViewHandler();
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
//        gson = gsonBuilder.create();
//        connectionProcess = (ConnectionProcess) this;
//
//        searchView = (EditText) findViewById(R.id.searchView);
//        linear_processlead = (LinearLayout) findViewById(R.id.linear_processlead);
//        tv_nodataavail = (TextView) findViewById(R.id.tv_nodataavail);
//        listInProcess = (RecyclerView) findViewById(R.id.listInProcess);
//        mainLayout = findViewById(R.id.mainLayout);
////        ringProgressDialog = ProgressDialog.show(InProcessLeads.this, "Please wait ...", "Loading Your Data ...", true);
////        ringProgressDialog.setCancelable(true);
////        ringProgressDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));
//
//        inserSockettLogsArrayList = SharedPref.getLogsArrayList(inserSockettLogsArrayList, "SocketLogs", InProcessLeads.this);
//
//        ssb = new StringBuffer();
//
//        if (inProcessDatasetArrayList != null) {
//
//            searchView.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    filter(s.toString());
//
//                }
//            });
////                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////                                @Override
////                                public boolean onQueryTextSubmit(String s) {
////                                    return false;
////                                }
////
////                                @Override
////                                public boolean onQueryTextChange(String s) {
////                                    if (inProcessAdapter!=null)
////                                        inProcessAdapter.getFilter().filter(s);
////                                    return false;
////                                }
////                            });
//
//        }
//
//    }
//
//    private void sendData() {
//
////        ringProgressDialog = new ProgressDialog(this);
////        ringProgressDialog.setMessage("Please wait ..\n Loading Your Data ...");
////        ringProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////        ringProgressDialog.setIndeterminate(true);
////
////        if (!InProcessLeads.this.isFinishing()) {
////            ringProgressDialog.show();
////        }
//
//        AlertDialogClass.PopupWindowShow(InProcessLeads.this, mainLayout);
//
//
////        if(((Activity) this).isFinishing())
////        {
////            ringProgressDialog = ProgressDialog.show(InProcessLeads.this, "Please wait ...", "Loading Your Data ...", true);
////            ringProgressDialog.setCancelable(true);
//////        ringProgressDialog.getWindow().setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.color.white));
////            ringProgressDialog.show();        }
////        ringProgressDialog = Views.showDialog(this);
////        ProgressDialog ringProgressDialog;
////        if(((Activity) getApplicationContext()).isFinishing())
////        {
////            ringProgressDialog = ProgressDialog.show(InProcessLeads.this, "Please wait ...", "Loading Your Data ...", true);
////            ringProgressDialog.setCancelable(true);
//////        ringProgressDialog.getWindow().setBackgroundDrawable(getApplicationContext().getResources().getDrawable(R.color.white));
////            ringProgressDialog.show();        }
//
//        try {
////            String imei;
////            String simID;
////            String osversion="normal";
////            imei = Logics.getDeviceID(this);
////            simID = Logics.getSimId(this);
////            if (android.os.Build.VERSION.SDK_INT>=29){
////                imei=Logics.getTokenID(this);
////                osversion="oxy";
////                simID="12345";
////            }
//            JSONObject jsonObject = new JSONObject();
////            jsonObject.put("imei",imei);
////            jsonObject.put("page",1);
////            jsonObject.put("size",10);
//            String vppid = Logics.getVppId(InProcessLeads.this);
////            jsonObject.put("VPPID", "651607");
////            jsonObject.put("VPPID", "650666");
//            //  jsonObject.put("VPPID","72891");
//            jsonObject.put("VPPID", vppid);
//            jsonObject.put("reportType", "InProcess");
//            byte data[] = jsonObject.toString().getBytes();
//            //   new SendTOServer(ClientList.this,ClientList.this, Const.MSGFETCHCLIENTLIST,data).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            new SendTOServer(InProcessLeads.this, InProcessLeads.this, Const.MSGFETCHLEADINPROCESS, data, connectionProcess).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            FirebaseCrashlytics.getInstance().recordException(e);
//        }
//    }
//
//    @Override
//    public void requestSent(int value) {
//
//    }
//
//    public class ViewHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
////            if (ringProgressDialog != null) {
////                ringProgressDialog.dismiss();
////                ringProgressDialog.cancel();
////            }
//
//            AlertDialogClass.PopupWindowDismiss();
//            switch (msg.arg1) {
//                case Const.MSGSOCKETCONNECTEDINPROCESS: {
//                    sendData();
//                }
//                break;
//
//                case Const.MSGFETCHLEADINPROCESS: {
//
//                    data = "";
//                    data = (String) msg.obj;
//                    data = String.valueOf(ssb.append(data));
//
//                    Log.e("Inprocess", data);
//
//                    if (data.equalsIgnoreCase("[]")) {
//                        linear_processlead.setVisibility(View.GONE);
//                        tv_nodataavail.setVisibility(View.VISIBLE);
//                        ssb.setLength(0);
//
//                    } else {
//                        linear_processlead.setVisibility(View.VISIBLE);
//                        tv_nodataavail.setVisibility(View.GONE);
//
//                        // inProcessDatasetArrayList.clear();
//
//                        if (data.endsWith("]")) {
//
//                            inProcessDatasetArrayList = gson.fromJson(data, new TypeToken<ArrayList<InProcessDataset>>() {
//                            }.getType());
//
//                            Log.e("size", String.valueOf(inProcessDatasetArrayList.size()));
////                        inProcessAdapter = new InProcessAdapter(inProcessDatasetArrayList,InProcessLeads.this);
////                        listInProcess.setAdapter(inProcessAdapter);
////                        listInProcess.setItemAnimator(new DefaultItemAnimator());
//
//                            InProcessAdapter inProcessAdapter = new InProcessAdapter(inProcessDatasetArrayList, InProcessLeads.this, "");
//                            listInProcess.setLayoutManager(new LinearLayoutManager(InProcessLeads.this));
//                            listInProcess.setAdapter(inProcessAdapter);
//                            listInProcess.setItemAnimator(new DefaultItemAnimator());
//                            ssb.setLength(0);
//
//                        }
//
//
//                    }
//                    // ssb.setLength(0);
//
//                }
//                break;
//
//            }
//
//
//        }
//    }
//
//    @Override
//    public void connected() {
////        if (ringProgressDialog != null) {
////            ringProgressDialog.dismiss();
////            ringProgressDialog.cancel();
////        }
//
//        if (inserSockettLogsArrayList != null) {
//            if (inserSockettLogsArrayList.size() > 0) {
//                try {
//                    SharedPref.insertSocketLogs(inserSockettLogsArrayList, Logics.getVppId(InProcessLeads.this),
//                            "1",
//                            Methods.getVersionInfo(InProcessLeads.this),
//                            Methods.getlogsDateTime(), "InProcessLeads",
//                            Connectivity.getNetworkState(getApplicationContext()),
//                            InProcessLeads.this);
//                    ;
//                } catch (Exception e) {
//                    Toast.makeText(InProcessLeads.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//        }
//
//
//        AlertDialogClass.PopupWindowDismiss();
//        //        AlertDailog.ProgressDlgDiss();
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                sendData();
//                //TastyToast.makeText(InProcessLeads.this, "Connected", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
//            }
//        });
//    }
//
//    @Override
//    public void serverNotAvailable() {
////        if (ringProgressDialog != null) {
////            ringProgressDialog.dismiss();
////            ringProgressDialog.cancel();
////        }
//
//        AlertDialogClass.PopupWindowDismiss();
//        Log.e("serverNotAvailable00: ", "serverNotAvailable00");
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (connectionProcess != null) {
//                    ProgressDlgConnectSocket(InProcessLeads.this, connectionProcess, "Server Not Available");
////                    ConnectToserver(connectionProcess);
//                } else {
//                    Toast.makeText(InProcessLeads.this, "null", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
//
//    @Override
//    public void IntenrnetNotAvailable() {
////        if (ringProgressDialog != null) {
////            ringProgressDialog.dismiss();
////            ringProgressDialog.cancel();
////        }
//
//        AlertDialogClass.PopupWindowDismiss();
//
//        Views.SweetAlert_NoDataAvailble(InProcessLeads.this, "Internet Not Available");
//        //        if (pDialog.isShowing()) {
//        //            pDialog.dismiss();
//        //            pDialog.cancel();
//        //        }
//        Log.e("IntenrnetNotAvailable: ", "internet");
//
//        //        AlertDailog.ProgressDlgDiss();
//        //        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void ConnectToserver(final ConnectionProcess connectionProcess) {
////        if (ringProgressDialog != null) {
////            ringProgressDialog.dismiss();
////            ringProgressDialog.cancel();
////        }
//
//        AlertDialogClass.PopupWindowDismiss();
////        connected = false;
//
//        Log.e("ConnectToserver11: ", "ConnectToserver11");
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (Connectivity.getNetworkState(getApplicationContext()))
//                    new ConnectTOServer(InProcessLeads.this, connectionProcess).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (Const.dismiss == true) {
//                            if (Const.isServerConnected == true && Const.isSocketConnected == false) {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        ProgressDlgConnectSocket(InProcessLeads.this, connectionProcess, "Server Not Available");
////                                        ConnectToserver(connectionProcess);
//                                    }
//                                });
//                            }
//                        }
//                    }
//                }, 1000);
//            }
//        });
//    }
//
//    @Override
//    public void SocketDisconnected() {
////        if (ringProgressDialog != null) {
////            ringProgressDialog.dismiss();
////            ringProgressDialog.cancel();
////        }
//
//        AlertDialogClass.PopupWindowDismiss();
//        Log.e("SocketDisconnected11: ", "SocketDisconnected");
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (connectionProcess != null) {
//                    ProgressDlgConnectSocket(InProcessLeads.this, connectionProcess, "Server Not Available");
////                    ConnectToserver(connectionProcess);
//                } else {
//                    Toast.makeText(InProcessLeads.this, "null", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//
//        if (Connectivity.getNetworkState(getApplicationContext())) {
//            if (Const.isServerConnected == true && Const.isSocketConnected == false) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ProgressDlgConnectSocket(InProcessLeads.this, connectionProcess, "Server Not Available");
////                    ConnectToserver(connectionProcess);
//                    }
//                });
//            } else {
//                sendData();
//            }
//        }
//
//        ///added this lines extra by shiva ....
//        handler.postDelayed(runnable = new Runnable() {
//            @Override
//            public void run() {
//                handler.postDelayed(runnable, delay);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (!Connectivity.getNetworkState(getApplicationContext())) {
//                            NOCheckINTERNET = true;   /// no net ....
//                            Log.e("run: ", "no net");
//                            imgConnection.setImageResource((R.drawable.ic_up_and_down_arrows_symbol_red));
////                            lineinternet.setBackgroundColor(getResources().getColor(R.color.red));
////                            txtinternet.setText("Online");
//                            showSnackbar("Internet Not Available");
//
//                        } else {
////                            NOCheckINTERNET = false;   /// no net ....
//                            imgConnection.setImageResource((R.drawable.ic_up_and_down_arrows_symbol));
////                            lineinternet.setBackgroundColor(getResources().getColor(R.color.btn_active));
////                            showSnackbar("Online");
//
////                            imgConnection.setBackground(getResources().getDrawable(R.drawable.ic_up_and_down_arrows_symbol));
//                            Log.e("run: ", " net available");
//
//                        }
//
//                        if (NOCheckINTERNET == true) {
//                            if (Connectivity.getNetworkState(getApplicationContext())) {
//                                NOCheckINTERNET = false;
////                                if (Const.dismiss == true)
//                                new ConnectTOServer(InProcessLeads.this, connectionProcess).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//
//                            }
//                        }
//                    }
//                });
//            }
//        }, delay);
//        super.onResume();
//
//    }
//
//    @SuppressLint("LongLogTag")
//    public void ProgressDlgConnectSocket(Context context, final ConnectionProcess connectionProcess, String msg) {
//        // 2. Confirmation message
//        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
//
//        Log.e( "DlgConnectSocket", "called");
//        MaxTry++;
//        if (MaxTry > 3) {
//            sweetAlertDialog.setTitleText(msg)
////                .setContentText("Socket Not Available")
//                    .setConfirmText("Try Again later!")
//                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
//                            sDialog.dismissWithAnimation();
//                            sDialog.dismiss();
//                            sDialog.cancel();
//
//                            //call here
//                            SharedPref.insertSocketLogs(inserSockettLogsArrayList, Logics.getVppId(InProcessLeads.this),
//                                    "0",
//                                    Methods.getVersionInfo(InProcessLeads.this),
//                                    Methods.getlogsDateTime(), "InProcessLeads",
//                                    Connectivity.getNetworkState(getApplicationContext()),
//                                    InProcessLeads.this);
//
//                            finishAffinity();
//                            finish();
//
//
//                        }
//                    });
//            if (!InProcessLeads.this.isFinishing()) {
//                sweetAlertDialog.show();
//            } else {
//                Toast.makeText(InProcessLeads.this, "ggggggggggg", Toast.LENGTH_SHORT).show();
//            }
//            sweetAlertDialog.setCancelable(false);
//
//        } else {
////            new ConnectTOServer(InProcessLeads.this, connectionProcess).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//
//
//            if (connectionProcess==null){
//                Log.e( "DlgConnectSocket11111_null", "called");
//
//            }else {
//                new ConnectTOServer(InProcessLeads.this, connectionProcess).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                connectionProcess.ConnectToserver(connectionProcess);
//            }
//            Log.e( "DlgConnectSocket11111", "called");
//
//        }
//
//        Log.e("DlgConnectSocketMaxTry", String.valueOf(MaxTry));
//
//
////                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
////                    @Override
////                    public void onClick(SweetAlertDialog sDialog) {
////                        sDialog.dismissWithAnimation();
////                    }
////                })
////                .show();
//    }
//
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        super.onBackPressed();
//    }
//
//    @Override
//    protected void onDestroy() {
//        handler.removeCallbacks(runnable);
//        super.onDestroy();
//    }
//
//    private void filter(String text) {
//        ArrayList<InProcessDataset> filteredList = new ArrayList<>();
//        for (InProcessDataset item : inProcessDatasetArrayList) {
//            if (item.getProspectName() != null) {
//                if (item.getProspectName().toUpperCase().contains(text.toUpperCase())) {
//                    filteredList.add(item);
//                }
//            }
//
//            inProcessAdapter.filterList(filteredList);
//        }
//    }
//
//}
