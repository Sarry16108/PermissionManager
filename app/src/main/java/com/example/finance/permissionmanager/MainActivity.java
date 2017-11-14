package com.example.finance.permissionmanager;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions(new String[] {Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE});
//        if (checkPermissions(new String[] {Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
//            getContacts();
//        }
    }


    private void getContacts() {
        class PhoneInfo {
            private String name;
            private String number;

            public PhoneInfo(String name, String number) {
                this.name = name;
                this.number = number;
            }
            public String getName() {
                return name;
            }
            public String getNumber() {
                return number;
            }

            @Override
            public String toString() {
                return "PhoneInfo{" +
                        "name='" + name + '\'' +
                        ", number='" + number + '\'' +
                        '}';
            }
        }

        class Info {
            private String func;
            private int    count;
            private List<PhoneInfo> contacts;

            public void setContacts(List<PhoneInfo> contacts) {
                this.contacts = contacts;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setFunc(String func) {
                this.func = func;
            }

            public List<PhoneInfo> getContacts() {
                return contacts;
            }

            public int getCount() {
                return count;
            }

            public String getFunc() {
                return func;
            }
        }


        List<PhoneInfo> list = new ArrayList<PhoneInfo>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        //moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            PhoneInfo phoneInfo = new PhoneInfo(name, number);
            list.add(phoneInfo);
        }

        Info info = new Info();
        info.setContacts(list);
        info.setCount(list.size());
        info.setFunc("callJS");


        String json = /*new Gson().toJson(info);   //*/GsonUtils.castObjectJson(info);
        Log.d("contacts", json);

    }

    @Override
    protected void permissionGranted(String permission) {
        super.permissionGranted(permission);

        switch (permission) {
            case Manifest.permission.READ_CONTACTS:
                getContacts();
                break;
        }
    }

    @Override
    protected void permissionDenied(String permission) {
        super.permissionDenied(permission);
        switch (permission) {
            case Manifest.permission.CAMERA:
                //do some thing
                break;
        }
    }
}
